package com.edu.miu.service.impl;

import com.edu.miu.client.MediaClient;
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

    private final MediaClient mediaClient;

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
    @Transactional
    public RatingDto addRating(RatingDto ratingDto) {
        Utils.validateRatingDto(ratingDto);
        int mediaId = ratingDto.getMediaId();
        MediaType mediaType = ratingDto.getMediaType();
        boolean isValidMedia = mediaClient.isValidMedia(mediaId, mediaType);
        if (isValidMedia) {
            Rating rating = ratingRepository.findByUserIdAndMediaIdAndMediaType(ratingDto.getUserId(), mediaId, mediaType);
            RatingDto result = null;
            if (rating == null) {
                result = this.convertTo(ratingRepository.save(this.convertTo((ratingDto))));
            } else if (rating.getRating() != ratingDto.getRating()) {
                rating.setRating(ratingDto.getRating());
                result = this.convertTo(ratingRepository.save(rating));
            }
            if (result != null) {
                this.sendMessage(mediaId, mediaType);
            }
            return result;
        }
        return null;
    }

    @Override
    @Transactional
    public RatingDto addRatingByUser(String userId, RatingDto ratingDto) {
        ratingDto.setUserId(userId);
        return this.addRating(ratingDto);
    }

    @Override
    @Transactional
    public RatingDto updateRating(int id, RatingDto ratingDto) {
        Rating rating = this.getRatingById(id);
        return this.updateRating(rating, ratingDto);
    }

    @Override
    @Transactional
    public RatingDto updateRatingByUser(String userId, RatingDto ratingDto) {
        Rating rating = ratingRepository.findByUserIdAndMediaIdAndMediaType(userId, ratingDto.getMediaId(), ratingDto.getMediaType());
        return this.updateRating(rating, ratingDto);
    }

    private RatingDto updateRating(Rating rating, RatingDto ratingDto) {
        boolean isUpdateRating = false;
        if (rating != null) {
            Utils.validateRatingDto(ratingDto);
            if (ratingDto.getRating() > 0 && ratingDto.getRating() != rating.getRating()) {
                rating.setRating(ratingDto.getRating());
                isUpdateRating = true;
            }
//            if (ratingDto.getUserId() != null && ratingDto.getUserId().equals(rating.getUserId())) {
//                rating.setUserId(ratingDto.getUserId());
//            }
//            if (ratingDto.getMediaId() > 0 && ratingDto.getMediaId() != rating.getMediaId()) {
//                rating.setMediaId(ratingDto.getMediaId());
//            }
//            if (ratingDto.getMediaType() != null && ratingDto.getMediaType() != rating.getMediaType()) {
//                rating.setMediaType(ratingDto.getMediaType());
//            }
        }
        RatingDto result = this.convertTo(ratingRepository.save(rating));
        if (isUpdateRating) {
            this.sendMessage(result.getMediaId(), result.getMediaType());
        }
        return result;
    }

    @Override
    @Transactional
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
    public void deleteRatingByMedia(int mediaId, MediaType mediaType) {
        ratingRepository.deleteAllByMediaIdAndMediaType(mediaId, mediaType);
    }

    @Override
    @Transactional
    public RatingDto deleteRatingByUser(String userId, int mediaId, MediaType mediaType) {
        Rating rating = ratingRepository.findByUserIdAndMediaIdAndMediaType(userId, mediaId, mediaType);
        if (rating!= null) {
            ratingRepository.deleteById(rating.getId());
        }
        return this.convertTo(rating);
    }

    @Override
    @Transactional
    public void deleteAllRatingByUserId(String id) {
        ratingRepository.deleteByUserId(id);
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
