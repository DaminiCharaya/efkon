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

    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException {
        return tagsDao.fetchTagCountByDateGroupByCustomerType(date);
    }

    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType,String date) throws ParseException {
        return tagsDao.fetchTagCountByDateAndByCustomerType(customerType,date);
    }
    public List<?> noOfTagsIssuedInGivenDates()
    {
        return tagsDao.noOfTagsIssuedInGivenDates();
    }
    }


