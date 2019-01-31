package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;

import java.util.List;

public interface TagsDao {

    public List<TagResponse> getNoOfTags();
    public List<?> getNoOfTagsByDate();

}
