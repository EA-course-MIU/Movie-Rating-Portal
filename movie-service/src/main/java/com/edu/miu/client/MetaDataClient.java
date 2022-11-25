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

@FeignClient(name = "METADATA-SERVICE")
public interface MetaDataClient {

//    @SpringQueryMap
//    @GetMapping
//    Object getAll(@RequestParam List<Integer> genreIds,
//                                    @RequestParam List<Integer> directorIds,
//                                    @RequestParam List<Integer> actorIds);


    @GetMapping("/metadata")
    Object getAll(@SpringQueryMap MetaDataCriteria metaDataCriteria);

    @GetMapping("/genres")
    List<Object> getGenres(@RequestParam(value = "ids", required = false) List<Integer> ids);

    @GetMapping("/persons")
    List<Object> getPersons(@RequestParam(value="position", required = false) String position,
                      @RequestParam(value = "ids", required = false) List<Integer> ids);

}
