package com.miu.mapper;

import com.miu.dto.FavoriteListDto;
import com.miu.entity.FavoriteList;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper extends Mapper<FavoriteList, FavoriteListDto> {
    public FavoriteMapper() {
        super(FavoriteList.class, FavoriteListDto.class);
    }
}
