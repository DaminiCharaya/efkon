package com.example.efkon.controller;


import com.example.efkon.ex.NotFoundException;
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
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByCustomerType(@PathVariable("customerType") Integer customerType) {
        List<WalletResponse> list = walletService.fetchWalletCountByCustomerType(customerType);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/wallet/{customerType}/sortbymonthonly")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByCustomerTypeAndSortByMonth(@PathVariable("customerType") Integer customerType) {
        List<WalletResponse> list = walletService.fetchWalletCountByCustomerTypeAndSortByMonth(customerType);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/wallet")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountGroupByCustomerType() {
        List<WalletResponse> list = walletService.fetchWalletCountGroupByCustomerType();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/wallet/bymonth")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountGroupByCustomerTypeAndSortByMonth() {
        List<WalletResponse> list = walletService.fetchWalletCountGroupByCustomerTypeAndSortByMonth();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("wallet/date")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByDateAndGroupByCustomerType(@RequestParam("date") String date) throws ParseException {
        List<WalletResponse> list = walletService.fetchWalletCountByDateAndGroupByCustomerType(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }


    @GetMapping("wallet/{customerType}/date")
    public ResponseEntity<List<WalletResponse>> fetchWalletCountByDateAndByCustomerType(@PathVariable("customerType") Integer customerType, @RequestParam("date") String date) throws ParseException {
        List<WalletResponse> list = walletService.fetchWalletCountByDateAndByCustomerType(customerType, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("walletbalance/")
    public Integer fetchNoOfWalletByBalance() {
        Integer list = walletService.fetchNoOfWalletByBalance();
        return list;
    }

    @GetMapping("walletbalance/{customerType}")
    public Integer fetchNoOfWalletByBalanceAndByCustomerType(@PathVariable("customerType") Integer customerType) {
        Integer list = walletService.fetchNoOfWalletByBalanceAndByCustomerType(customerType);
        return list;
    }

    @GetMapping("walletbalances/")
    public ResponseEntity<List<WalletResponse>> fetchNoOfWalletByBalanceAndGroupByCustomerType() {
        List<WalletResponse> list = walletService.fetchNoOfWalletByBalanceAndGroupByCustomerType();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("walletbalance/{customerType}/date")
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(@PathVariable("customerType") Integer customerType, @RequestParam("date") String date) throws ParseException {
        Integer list = walletService.fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(customerType, date);
        return list;
    }

    @GetMapping("walletbalances/date")
    public ResponseEntity<List<WalletResponse>> fetchNoOfWalletByBalanceAndGroupByCustomerTypeAndByDate(@RequestParam("date") String date) throws ParseException {

        List<WalletResponse> list = walletService.fetchNoOfWalletByBalanceAndGroupByCustomerTypeAndByDate(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<WalletResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("wallets/{customerType}/date")
    public Integer fetchNoOfWalletByCustomerTypeAndByDate(@PathVariable("customerType") Integer customerType, @RequestParam("date") String date) throws ParseException {
        Integer list = walletService.fetchNoOfWalletByCustomerTypeAndByDate(customerType, date);
        return list;
    }

    @GetMapping("fetchwallet/{customerType}/Samedate")
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(@PathVariable("customerType") Integer customerType, @RequestParam("date") String date) throws ParseException {
        Integer list = walletService.fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(customerType, date);
        return list;
    }
   @GetMapping("walletbalancelessthan100forretailer")
    public ResponseEntity<List<?>> fetchWalletOfRetailerByBalanceAndByDateInTxn_Media_Type_Id(@RequestParam("date")String date) throws ParseException {
    List<?> list = walletService.fetchWalletOfRetailerByBalanceAndByDateInTxn_Media_Type_Id(date);
    if (list == null || list.isEmpty()) {
        return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<List<?>>(list, HttpStatus.OK);

}


    @GetMapping("walletbalancelessthan100forboth")
    public ResponseEntity<List<?>> fetchWalletByBalanceAndByDateInTxn_Media_Type_Id(@RequestParam("date")String date) throws ParseException {
        List<?> list = walletService.fetchWalletByBalanceAndByDateInTxn_Media_Type_Id(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("walletbalancelessthan100forcorporate")
    public ResponseEntity<List<?>> fetchWalletOfCorporateByBalanceAndByDateInTxn_Media_Type_Id(@RequestParam("date")String date) throws ParseException {
        List<?> list = walletService.fetchWalletOfCorporateByBalanceAndByDateInTxn_Media_Type_Id(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }
}
