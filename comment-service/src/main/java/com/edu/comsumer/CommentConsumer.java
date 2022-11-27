package com.edu.comsumer;

import com.edu.dto.MediaDto;
import com.edu.dto.UserDto;

public interface CommentConsumer {
    void receiveMessageFromMovie(MediaDto mediaDto);

//    void receiveRabbitMessageFromMovie(MediaDto mediaDto);
    void receiveMessageFromUser(UserDto userDto);

}
