package com.example.efkon.controller;


import com.example.efkon.service.WalletService;
import com.example.efkon.view.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping("/wallet/{customerType}")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByCustomerType(@PathVariable("customerType") Integer customerType)
    {
        List<WalletResponse> list=walletService.fetchWalletCountByCustomerType(customerType);
        return new ResponseEntity<List<WalletResponse>>(list,HttpStatus.OK);
    }

    @GetMapping("/wallet")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountGroupByCustomerType() {
        List<WalletResponse> list = walletService.fetchWalletCountGroupByCustomerType();
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("wallet/date")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByDateAndGroupByCustomerType(@RequestParam("date")String date) throws ParseException
    {
        List<WalletResponse> list= walletService.fetchWalletCountByDateAndGroupByCustomerType(date);
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }


    @GetMapping("wallet/{customerType}/date")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByDateAndByCustomerType(@PathVariable("customerType") Integer customerType,@RequestParam("date")String date) throws ParseException
    {
        List<WalletResponse> list= walletService.fetchWalletCountByDateAndByCustomerType(customerType,date);
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("walletbalance/")
    public Integer fetchNoOfWalletByBalance()
    {
        Integer list= walletService.fetchNoOfWalletByBalance();
        return list;
    }

    @GetMapping("walletbalance/{customerType}")
    public Integer fetchNoOfWalletByBalanceAndByCustomerType(@PathVariable("customerType") Integer customerType)
    {
        Integer list= walletService.fetchNoOfWalletByBalanceAndByCustomerType(customerType);
        return list;
    }

    @GetMapping("walletbalance/{customerType}/date")
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(@PathVariable("customerType") Integer customerType,@RequestParam("date")String date) throws ParseException
    {
        Integer list= walletService.fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(customerType,date);
        return list;
    }
    @GetMapping("wallets/{customerType}/date")
    public Integer fetchNoOfWalletByCustomerTypeAndByDate(@PathVariable("customerType") Integer customerType,@RequestParam("date")String date) throws ParseException
    {
        Integer list= walletService.fetchNoOfWalletByCustomerTypeAndByDate(customerType,date);
        return list;
    }

    @GetMapping("fetchwallet/{customerType}/date")
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(@PathVariable("customerType") Integer customerType,@RequestParam("date")String date) throws ParseException
    {
        Integer list= walletService.fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(customerType,date);
        return list;
    }


}
