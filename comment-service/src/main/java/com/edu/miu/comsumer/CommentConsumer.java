package com.edu.miu.comsumer;

import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;

public interface CommentConsumer {
    void receiveMessageFromMovie(MediaDto mediaDto);

//    void receiveRabbitMessageFromMovie(MediaDto mediaDto);
    void receiveMessageFromUser(UserDto userDto);

}
