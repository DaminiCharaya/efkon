package com.example.efkon.controller;
import com.example.efkon.service.TagsService;
import com.example.efkon.view.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagsController {

    @Autowired
    TagsService tagsService;

    @GetMapping()
    public ResponseEntity<List<TagResponse>> getTags() {
        List<TagResponse> list = tagsService.getNoOfTags();
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<?> getTagsByDate()
    {
      List<?> list= tagsService.getNoOfTagsByDate();
        return new ResponseEntity<List<?>>(list,HttpStatus.OK);
    }

}
