package com.example.efkon.service;


import com.example.efkon.repository.TagsDaoImpl;
import com.example.efkon.repository.WalletDao;
import com.example.efkon.repository.WalletDaoImpl;
import com.example.efkon.view.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WalletService")
public class WalletService {
    @Autowired
    WalletDaoImpl walletDao;

    public List<WalletResponse> getNoOfWallets() {
        return walletDao.getNoOfWallets();
    }
    public List<WalletResponse> getNoOfWalletsByDate() {
        return walletDao.getNoOfWalletsByDate();
    }

}
