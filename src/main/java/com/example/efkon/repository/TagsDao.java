package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TagsDao {

    public List<TagResponse> fetchTagCountGroupByCustomerType();

    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException;

    public List<?> fetchTagCountBySameDateGroupByCustomerType(String date) throws ParseException;

    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException;

    public List<TagResponse> fetchTagCountByCustomerTypeAndByMonth(Integer customerType, Integer month) throws ParseException;

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatus(Integer customerType);

    public List<TagResponse> noOfTagsIssuedInGivenDates(String firstdate, String seconddate, String thirddate) throws ParseException;

    public List<TagResponse> noOfTagsIssuedInGivenDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException;

    public List<TagResponse> fetchTagCountGroupByCustomerTypeAndByMonth(Integer month) throws ParseException;

    public List<TagResponse> fetchTagCountGroupByCustomerTypeAndSortByMonth();

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(Integer customerType);

    public List<TagResponse> fetchTagCountByCustomerTypeAndSortByMonth(Integer customerType);

    public List<?> noOfTagsByStatus(Integer status);
    public List<?> noOfNewTagsIssuedInOctNovDecByStatusForRetailer(Integer status,Integer year);
    public List<?> noOfNewTagsIssuedInOctNovDecByStatusForCorporate(Integer status,Integer year);

    public List<?> noOfTagsOfRetailerByStatus(Integer status);
 public List<?> newTagActivationRateForRetail(Integer noOfTags,Integer status,Integer year);
    public List<?> newTagActivationRateForCorporate(Integer noOfTags,Integer status,Integer year);
    public List<?> noOfTagsOfCorporateByStatus(Integer status);
    public List<?> frequencyOfUsageForRetail(String date) throws ParseException;
    public List<?> frequencyOfUsageForCorporate(String date) throws ParseException;
    public List<?> active30forretail(Integer noOfTags, String date) throws ParseException;

    public List<?> activationrateforretail(Integer noOfTags, String date) throws ParseException;

    public List<?> activationrate(Integer noOfTags, String date) throws ParseException;

    public List<?> activationrateforcorporate(Integer noOfTags, String date) throws ParseException;

    public List<TagResponse> active30(Integer noOfTags, String date) throws ParseException;

    public List<?> active30forcorporate(Integer noOfTags, String date) throws ParseException;
}
