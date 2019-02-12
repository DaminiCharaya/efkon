package com.example.efkon.repository;

import com.example.efkon.view.TxnResponse;

import java.text.ParseException;
import java.util.List;

public interface TxnDao {
    public List<TxnResponse> fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(String date) throws ParseException;

    public Integer fetchAllTxnForDistinctTagsAndByCreatedDate(String date) throws ParseException;

    public Integer fetchAllTxnForDistinctTags();
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByMonth();

    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(Integer customerType, String date) throws ParseException;

    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(String date) throws ParseException;

    public List<?> fetchAllDistinctTxnInSmCard();

    public List<?> fetchAllDistinctTxnInSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException;

    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException;

    public List<?> fetchAllDistinctTxnInSmCardByCustomerType(Integer customerType);

    public List<?> fetchAllDistinctTxnInSmCardGroupByCustomerType();

    public List<?> fetchTagCount();

    public List<?> fetchTagCountWithRevenue();

    List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException;
}
