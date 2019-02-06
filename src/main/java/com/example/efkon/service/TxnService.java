package com.example.efkon.service;

import com.example.efkon.repository.TagsDaoImpl;
import com.example.efkon.repository.TxnDaoImpl;
import com.example.efkon.view.TagResponse;
import com.example.efkon.view.TxnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.util.List;

@Service("TxnService")
public class TxnService {

    @Autowired
    TxnDaoImpl txnDao;

    public List<TxnResponse> fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(String date) throws ParseException {
        return txnDao.fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(date);
    }


    public Integer fetchAllTxnForDistinctTagsAndByCreatedDate(String date) throws ParseException {
        return txnDao.fetchAllTxnForDistinctTagsAndByCreatedDate(date);
    }

    public Integer fetchAllTxnForDistinctTags() {
        return txnDao.fetchAllTxnForDistinctTags();
    }

    public List<?> fetchAllDistinctTxnInSmCard() {
        return txnDao.fetchAllDistinctTxnInSmCard();
    }

    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException {
        return txnDao.fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(firstdate, seconddate, thirddate);
    }

    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException {
        return txnDao.fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(firstdate, seconddate, thirddate, customerType);
    }

    public List<?> fetchAllDistinctTxnInSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException {
        return txnDao.fetchAllDistinctTxnInSmCardForDifferentDates(firstdate, seconddate, thirddate);
    }

    public List<?> fetchAllDistinctTxnInSmCardByCustomerType(Integer customerType) {
        return txnDao.fetchAllDistinctTxnInSmCardByCustomerType(customerType);
    }

    public List<?> fetchAllDistinctTxnInSmCardGroupByCustomerType() {
        return txnDao.fetchAllDistinctTxnInSmCardGroupByCustomerType();
    }

    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(Integer customerType, String date) throws ParseException {
        return txnDao.fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(customerType, date);
    }

    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(String date) throws ParseException {
        return txnDao.fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(date);
    }

    public List<?> fetchTagCount() {
        return txnDao.fetchTagCount();
    }

    public List<?> fetchTagCountWithRevenue() {
        return txnDao.fetchTagCountWithRevenue();
    }

}
