package com.edu.miu.client;

import com.edu.miu.dto.MetaDataDto;
import com.edu.miu.dto.criteria.MetaDataCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "METADATA-SERVICE", url = "http://localhost:5558", path = "/metadata")
public interface MetaDataClient {

//    @SpringQueryMap
//    @GetMapping
//    Object getAll(@RequestParam List<Integer> genreIds,
//                                    @RequestParam List<Integer> directorIds,
//                                    @RequestParam List<Integer> actorIds);


    @GetMapping
    Object getAll(@SpringQueryMap MetaDataCriteria metaDataCriteria);
}
