package com.example.comsumer;

import com.example.dto.MediaDto;
import com.example.dto.UserDto;

public interface CommentConsumer {
    void receiveMessageFromMovie(MediaDto mediaDto);

    void receiveRabbitMessageFromMovie(MediaDto mediaDto);
    void receiveMessageFromUser(UserDto userDto);

}
