package com.example.efkon.service;


import com.example.efkon.repository.TagsDaoImpl;
import com.example.efkon.repository.WalletDao;
import com.example.efkon.repository.WalletDaoImpl;
import com.example.efkon.view.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("WalletService")
public class WalletService {
    @Autowired
    WalletDaoImpl walletDao;

    public List<WalletResponse> fetchWalletCountGroupByCustomerType() {
        return walletDao.fetchWalletCountGroupByCustomerType();
    }

    public List<WalletResponse> fetchWalletCountByCustomerType(Integer customerType) {
        return walletDao.fetchWalletCountByCustomerType(customerType);
    }
    public List<WalletResponse> fetchWalletCountByDateAndGroupByCustomerType(String date) throws ParseException {
        return walletDao.fetchWalletCountByDateAndGroupByCustomerType(date);
    }

    public List<WalletResponse> fetchWalletCountByDateAndByCustomerType(Integer customerType,String date) throws ParseException {
        return walletDao.fetchWalletCountByDateAndByCustomerType(customerType,date);
    }

}
