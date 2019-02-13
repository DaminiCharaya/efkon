package com.example.efkon.repository;

import com.example.efkon.view.WalletResponse;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.util.List;

public interface WalletDao {


    public List<WalletResponse> fetchWalletCountGroupByCustomerType();

    public List<WalletResponse> fetchWalletCountGroupByCustomerTypeAndSortByMonth();
    public List<?> fetchWalletOfRetailerByBalanceAndByDateInTxn_Media_Type_Id(String date) throws ParseException;
    public List<?> fetchWalletOfCorporateByBalanceAndByDateInTxn_Media_Type_Id(String date) throws ParseException;
    public List<?> fetchWalletByBalanceAndByDateInTxn_Media_Type_Id(String date) throws ParseException;
    public Integer fetchNoOfWalletByBalance();

    public Integer fetchNoOfWalletByBalanceAndByCustomerType(Integer customerType);

    public List<WalletResponse> fetchNoOfWalletByBalanceAndGroupByCustomerType();

    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(Integer customerType, String date) throws ParseException;

    public List<WalletResponse> fetchNoOfWalletByBalanceAndGroupByCustomerTypeAndByDate(String date) throws ParseException;

    public Integer fetchNoOfWalletByCustomerTypeAndByDate(Integer customerType, String date) throws ParseException;

    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(Integer customerType, String date) throws ParseException;

    public List<WalletResponse> fetchWalletCountByCustomerType(Integer customerType);

    public List<WalletResponse> fetchWalletCountByCustomerTypeAndSortByMonth(Integer customerType);

    public List<WalletResponse> fetchWalletCountByDateAndGroupByCustomerType(String date) throws ParseException;

    public List<WalletResponse> fetchWalletCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException;


}
