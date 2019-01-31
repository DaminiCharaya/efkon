package com.example.efkon.service;

import com.example.efkon.repository.TagsDaoImpl;
import com.example.efkon.view.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TagsService")
public class TagsService {


        @Autowired
        TagsDaoImpl tagsDao;

        public List<TagResponse> getNoOfTags() {
            return tagsDao.getNoOfTags();
        }


    public List<?> getNoOfTagsByDate() {
        return tagsDao.getNoOfTagsByDate();
    }
    }


