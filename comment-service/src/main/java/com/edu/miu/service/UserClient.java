package com.edu.miu.service;

import com.edu.miu.dto.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("USER-SERVICE")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}")
    List <CommentDto> findByUserId(@PathVariable("userId") int userId);

}
