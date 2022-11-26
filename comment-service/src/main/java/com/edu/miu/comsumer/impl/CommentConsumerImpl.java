package com.edu.miu.comsumer.impl;

import com.edu.miu.comsumer.CommentConsumer;
import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;
import com.edu.miu.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentConsumerImpl implements CommentConsumer {
    private final CommentService commentService;

    @KafkaListener(
            topics = {"remove-media-topic", "series-delete-topic" },
            containerFactory = "removeMediaKafkaListenerContainerFactory",
            groupId = "remove-media-topic")
    @Override
    public void receiveMessageFromMovie (MediaDto mediaDto) {
        if (mediaDto.getId() > 0 && mediaDto.getMediaType() != null) {
            commentService.deleteCommentByMediaId(mediaDto);
            System.out.println(mediaDto);
        }
    }

    @Override
    @RabbitListener(queues = {"remove-media-queue"})
    public void receiveRabbitMessageFromMovie (MediaDto mediaDto)
    {
        if (mediaDto.getId () > 0 && mediaDto.getMediaType() != null) {
            commentService.deleteCommentByMediaId(mediaDto);
            System.out.println(mediaDto);
        }
    }

    @KafkaListener(
            topics = "remove-user-topic",
            containerFactory = "removeUserKafkaListenerContainerFactory",
            groupId = "user-comment-topic")
    @Override
    public void receiveMessageFromUser(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            UserDto userDto = new UserDto(userId.replaceAll("^\"|\"$", ""));
            commentService.deleteCommentByUserId(userDto);
            System.out.println(userDto);
        }

    }

}
