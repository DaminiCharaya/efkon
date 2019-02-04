package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TagsDao {

    public List<TagResponse> fetchTagCountGroupByCustomerType();
    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException;

    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType,String date) throws ParseException;
    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatus(Integer customerType);
    public List<?> noOfTagsIssuedInGivenDates();
}
