package com.edu.miu.service.impl;

import com.edu.miu.dto.MediaRatingDto;
import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.RatingReportDto;
import com.edu.miu.entity.Rating;
import com.edu.miu.enums.MediaType;
import com.edu.miu.repository.RatingRepository;
import com.edu.miu.publisher.PublisherService;
import com.edu.miu.publisher.RabbitMQService;
import com.edu.miu.service.RatingService;
import com.edu.miu.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final ModelMapper modelMapper;

    private final PublisherService publisherService;

    private final RabbitMQService rabbitMQService;

    private Rating getRatingById(int id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public RatingDto getById(int id) {
        return this.convertTo(this.getRatingById(id));
    }

    @Override
    public List<RatingDto> getAll() {
        return ratingRepository.findAll().stream()
                .map(o -> this.convertTo((o)))
                .toList();
    }
    @Override
    public List<RatingReportDto> getRatingReportForMedia(int id) {
        List<Rating> ratingList = ratingRepository.findByMediaId(id);
        List<RatingReportDto> ratingReports = new ArrayList<>();

        if (!ratingList.isEmpty()) {
            ratingList.stream()
                .collect(Collectors.groupingBy(Rating::getRating))
                .entrySet()
                .forEach(entry -> {
                    RatingReportDto ratingReport = new RatingReportDto();
                    ratingReport.setRating(entry.getKey());

                    int votes = entry.getValue().size();
                    ratingReport.setVotes(votes);
                    ratingReport.setPercent(Utils.round2((double) (votes * 100) / ratingList.size()));
                    ratingReports.add(ratingReport);
                });
        }

        return ratingReports;
    }

    @Override
    public double getAvgRatingToMedia(int mediaId, MediaType mediaType) {
        return Utils.round2(ratingRepository.getAvgRatingToMedia(mediaId, mediaType));
    }

    @Override
    public RatingDto addRating(RatingDto ratingDto) {
        RatingDto result = this.convertTo(ratingRepository.save(this.convertTo((ratingDto))));
        this.sendMessage(result.getMediaId(), result.getMediaType());
        return result;
    }
    @Override
    public RatingDto updateRating(int id, RatingDto ratingDto) {
        Rating rating = this.getRatingById(id);
        boolean isUpdateRating = false;
        if (rating != null) {
            if (ratingDto.getRating() > 0 && ratingDto.getRating() != rating.getRating()) {
                rating.setRating(ratingDto.getRating());
                isUpdateRating = true;
            }
            if (ratingDto.getUserId() > 0 && ratingDto.getUserId() != rating.getUserId()) {
                rating.setUserId(ratingDto.getUserId());
            }
            if (ratingDto.getMediaId() > 0 && ratingDto.getMediaId() != rating.getMediaId()) {
                rating.setMediaId(ratingDto.getMediaId());
            }
            if (ratingDto.getMediaType() != null && ratingDto.getMediaType() != rating.getMediaType()) {
                rating.setMediaType(ratingDto.getMediaType());
            }
        }
        RatingDto result = this.convertTo(ratingRepository.save(rating));
        if (isUpdateRating) {
            this.sendMessage(result.getMediaId(), result.getMediaType());
        }
        return result;
    }

    @Override
    public RatingDto deleteRating(int id) {
        Rating rating = this.getRatingById(id);

        if (rating != null) {
            ratingRepository.deleteById(id);
            this.sendMessage(rating.getMediaId(), rating.getMediaType());
        }

        return this.convertTo(rating);
    }

    @Override
    @Transactional
    public void deleteRatingByMediaId(RatingDto ratingDto) {
        ratingRepository.deleteAllByMediaIdAndMediaType(ratingDto.getMediaId(), ratingDto.getMediaType());
    }

    private RatingDto convertTo(Rating rating) {
        if (rating == null) {
            return null;
        }
        return modelMapper.map(rating, RatingDto.class);
    }

    private Rating convertTo(RatingDto ratingDto) {
        if (ratingDto == null) {
            return null;
        }
        Utils.validateRatingDto(ratingDto);
        return modelMapper.map(ratingDto, Rating.class);
    }

    private void sendMessage(int mediaId, MediaType mediaType) {
        double avgRating = this.getAvgRatingToMedia(mediaId, mediaType);
        MediaRatingDto mediaRatingDto = new MediaRatingDto(mediaId, avgRating);
        if (mediaType == MediaType.MOVIE) {
            publisherService.sendMessageToMovieService(mediaRatingDto);
//            rabbitMQService.sendExchange("topic-exchange", "rating-movie-queue", mediaRatingDto);
        } else {
//            publisherService.sendMessageToTvSeriesService(mediaRatingDto);
            rabbitMQService.sendExchange("topic-exchange", "rating-tv-series-queue", mediaRatingDto);
        }
    }

}
