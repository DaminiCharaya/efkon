package com.example.efkon.service;

import com.example.efkon.repository.TagsDaoImpl;
import com.example.efkon.view.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.HTML;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service("TagsService")
public class TagsService {


    @Autowired
    TagsDaoImpl tagsDao;

    public List<TagResponse> fetchTagCountGroupByCustomerType() {
        return tagsDao.fetchTagCountGroupByCustomerType();
    }

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatus(Integer customerType) {
        return tagsDao.fetchTagCountByCustomerTypeAndGroupByStatus(customerType);
    }
    public List<TagResponse> fetchTagCountByCustomerTypeAndByMonth(Integer customerType,Integer month) throws ParseException {
        return tagsDao.fetchTagCountByCustomerTypeAndByMonth(customerType,month);
    }
    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException {
        return tagsDao.fetchTagCountByDateGroupByCustomerType(date);
    }
    public List<?> fetchTagCountBySameDateGroupByCustomerType(String date) throws ParseException {
        return tagsDao.fetchTagCountBySameDateGroupByCustomerType(date);
    }
    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException {
        return tagsDao.fetchTagCountByDateAndByCustomerType(customerType, date);
    }

    public List<TagResponse> noOfTagsIssuedInGivenDates(String firstdate, String seconddate, String thirddate) throws ParseException {
        return tagsDao.noOfTagsIssuedInGivenDates(firstdate, seconddate, thirddate);
    }

    public List<TagResponse> noOfTagsIssuedInGivenDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException {
        return tagsDao.noOfTagsIssuedInGivenDatesByCustomerType(firstdate, seconddate, thirddate, customerType);
    }

    public List<TagResponse> fetchTagCountGroupByCustomerTypeAndByMonth(Integer month) throws ParseException {
        return tagsDao.fetchTagCountGroupByCustomerTypeAndByMonth(month);
    }

   public List<TagResponse> fetchTagCountGroupByCustomerTypeAndSortByMonth()
    {
        return tagsDao.fetchTagCountGroupByCustomerTypeAndSortByMonth();
    }

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(Integer customerType)
    {
        return tagsDao.fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(customerType);
    }
    public List<TagResponse> fetchTagCountByCustomerTypeAndSortByMonth(Integer customerType)
    {
        return tagsDao.fetchTagCountByCustomerTypeAndSortByMonth(customerType);
    }
    public List<?> noOfTagsOfRetailerByStatus(Integer status)
    {
        return tagsDao.noOfTagsOfRetailerByStatus(status);
    }

    public List<?> noOfTagsOfCorporateByStatus(Integer status)
    {
        return tagsDao.noOfTagsOfCorporateByStatus(status);
    }

    public List<?> active30forretail(Integer noOfTags,String date) throws ParseException {
        return tagsDao.active30forretail(noOfTags,date);
    }

    public List<?> active30forcorporate(Integer noOfTags,String date) throws ParseException {
        return tagsDao.active30forcorporate(noOfTags,date);
    }
}


