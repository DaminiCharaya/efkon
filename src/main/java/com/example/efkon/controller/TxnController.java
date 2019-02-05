package com.example.efkon.controller;

import com.example.efkon.ex.NotFoundException;
import com.example.efkon.service.TxnService;
import com.example.efkon.view.TagResponse;
import com.example.efkon.view.TxnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class TxnController {

    @Autowired
    TxnService txnService;

    @GetMapping("/txn/date")
    public ResponseEntity<List<TxnResponse>> fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(@RequestParam("date") String date) throws ParseException {
        List<TxnResponse> list = txnService.fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(date);
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TxnResponse>>(list, HttpStatus.OK);
    }


    @GetMapping("/txns")
    public Integer fetchAllTxnForDistinctTagsAndByCreatedDate(@RequestParam("date") String date)throws ParseException {
        Integer list = txnService.fetchAllTxnForDistinctTagsAndByCreatedDate(date);

        return list;
    }

    @GetMapping("/txns/{customerType}/date")
    public ResponseEntity<List<TxnResponse>> fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(@PathVariable("customerType")Integer customerType,@RequestParam("date") String date)throws ParseException {
        List<TxnResponse> list = txnService.fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(customerType,date);
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TxnResponse>>(list, HttpStatus.OK);
    }


    @GetMapping("/txns/date")
    public ResponseEntity<List<TxnResponse>> fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(@RequestParam("date") String date)throws ParseException {
        List<TxnResponse> list = txnService.fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(date);
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TxnResponse>>(list, HttpStatus.OK);
    }
    @GetMapping("/txnsfortags")
    public Integer fetchAllTxnForDistinctTags() {
        Integer list = txnService.fetchAllTxnForDistinctTags();
        return list;
    }


    @GetMapping("/txnsincard")
    public ResponseEntity<List<?>> fetchAllDistinctTxnInSmCard() {
        List<?> list = txnService. fetchAllDistinctTxnInSmCard();
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("/txnsincard/{customerType}")
    public ResponseEntity<List<?>> fetchAllDistinctTxnInSmCardByCustomerType(@PathVariable("customerType")Integer customerType) {
        List<?> list = txnService. fetchAllDistinctTxnInSmCardByCustomerType(customerType);
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("/txnsincardforallcustomers")
    public ResponseEntity<List<?>> fetchAllDistinctTxnInSmCardGroupByCustomerType() {
        List<?> list = txnService. fetchAllDistinctTxnInSmCardGroupByCustomerType();
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("/txnsincardfordifferentdates")
    public ResponseEntity<List<?>> fetchAllDistinctTxnInSmCardForDifferentDates() {
        List<?> list = txnService. fetchAllDistinctTxnInSmCardForDifferentDates();
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }


    @GetMapping("/txnsincardandissuerdetailsfordifferentdates/")
    public ResponseEntity<List<?>> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(@RequestParam("firstdate") String firstdate,@RequestParam("seconddate") String seconddate,@RequestParam("thirddate") String thirddate) throws ParseException {
        List<?> list = txnService. fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(firstdate,seconddate,thirddate);
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("/txnsincardandissuerdetailsfordifferentdates/{customerType}/")
    public ResponseEntity<List<?>> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(@RequestParam("firstdate") String firstdate,@RequestParam("seconddate") String seconddate,@RequestParam("thirddate") String thirddate,@PathVariable("customerType")Integer customerType) throws ParseException {
        List<?> list = txnService. fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(firstdate,seconddate,thirddate,customerType);
        if (list== null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }



}
