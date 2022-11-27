package com.edu.miu.comsumer.impl;

import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;
import com.edu.miu.comsumer.CommentConsumer;
import com.edu.miu.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentConsumerImpl implements CommentConsumer {
    private final CommentService commentService;

    @KafkaListener(
            topics = "remove-comment-topic",
            containerFactory = "removeMediaKafkaListenerContainerFactory",
            groupId = "movie-comment-topic")

    @KafkaListener(
            topics = "remove-user-topic",
            containerFactory = "removeUserKafkaListenerContainerFactory",
            groupId = "user-comment-topic")
    @Override
    public void receiveMessageFromMovie (MediaDto mediaDto) {
        if (mediaDto.getMediaId () > 0 && mediaDto.getMediaType() != null) {
            commentService.deleteCommentByMediaId(mediaDto);
            System.out.println(mediaDto);
        }
    }

//    @Override
//    public void receiveRabbitMessageFromMovie (MediaDto mediaDto)
//    {
//        if (mediaDto.getMediaId () > 0 && mediaDto.getMediaType() != null) {
//            commentService.deleteCommentByMediaId(mediaDto);
//            System.out.println(mediaDto);
//        }
//    }

    @Override
    public void receiveMessageFromUser (UserDto userDto) {
        if (userDto.getUserId () > 0) {
            commentService.deleteCommentByUserId(userDto);
            System.out.println(userDto);
        }

    }

}
