package com.edu.miu.service;

import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.attribute.standard.Media;

@FeignClient("MEDIA-SERVICE")
public interface MediaClient {
    @GetMapping("/medias")
    Iterable<MediaDto> getAllMedia();
}
