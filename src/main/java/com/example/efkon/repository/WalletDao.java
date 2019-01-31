package com.example.efkon.repository;

import com.example.efkon.view.WalletResponse;

import java.util.List;

public interface WalletDao {


    public List<WalletResponse> getNoOfWallets();

    public List<WalletResponse> getNoOfWalletsByDate();

}
