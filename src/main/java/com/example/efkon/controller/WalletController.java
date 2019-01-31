package com.example.efkon.controller;


import com.example.efkon.service.WalletService;
import com.example.efkon.view.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping("/")
    public ResponseEntity<List<WalletResponse>> getWallets() {
        List<WalletResponse> list = walletService.getNoOfWallets();
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<WalletResponse>> getWalletsByDate()
    {
        List<WalletResponse> list= walletService.getNoOfWalletsByDate();
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

}
