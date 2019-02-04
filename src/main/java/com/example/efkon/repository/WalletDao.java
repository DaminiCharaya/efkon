package com.example.efkon.repository;

import com.example.efkon.view.WalletResponse;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.util.List;

public interface WalletDao {


    public List<WalletResponse> fetchWalletCountGroupByCustomerType();

    public List<WalletResponse> fetchWalletCountByCustomerType(Integer customerType);

    public List<WalletResponse> fetchWalletCountByDateAndGroupByCustomerType(String date) throws ParseException;
    public List<WalletResponse> fetchWalletCountByDateAndByCustomerType(Integer customerType,String date) throws ParseException;


}
