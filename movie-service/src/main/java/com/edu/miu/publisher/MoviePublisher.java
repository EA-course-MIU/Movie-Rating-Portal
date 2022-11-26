package com.edu.miu.publisher;

import com.edu.miu.dto.message.MediaDto;

public interface MoviePublisher {

    void sendRemovedMovieMessage(MediaDto mediaDto);

}
