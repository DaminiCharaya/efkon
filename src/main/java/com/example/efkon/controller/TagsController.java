package com.example.efkon.controller;

import com.example.efkon.ex.NotFoundException;
import com.example.efkon.service.TagsService;
import com.example.efkon.view.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class TagsController {

    @Autowired
    TagsService tagsService;

    @GetMapping("/tags/{customerType}")
    public ResponseEntity<List<TagResponse>> fetchTagCountByCustomerTypeAndGroupByStatus(@PathVariable("customerType") Integer customerType) {

        List<TagResponse> list = tagsService.fetchTagCountByCustomerTypeAndGroupByStatus(customerType);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponse>> fetchTagCountGroupByCustomerType() {
        List<TagResponse> list = tagsService.fetchTagCountGroupByCustomerType();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/tags/date")
    public ResponseEntity<?> fetchTagCountByDateGroupByCustomerType(@RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.fetchTagCountByDateGroupByCustomerType(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }


    @GetMapping("/tags/{customerType}/date")
    public ResponseEntity<?> fetchTagCountByDateAndByCustomerType(@PathVariable("customerType") Integer customerType, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.fetchTagCountByDateAndByCustomerType(customerType, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("tagsissued/")
    public ResponseEntity<List<TagResponse>> noOfTagsIssuedInGivenDates(@RequestParam("firstdate") String firstdate, @RequestParam("seconddate") String seconddate, @RequestParam("thirddate") String thirddate) throws ParseException {
        List<TagResponse> list = tagsService.noOfTagsIssuedInGivenDates(firstdate, seconddate, thirddate);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("tagsissued/{customerType}")
    public ResponseEntity<List<TagResponse>> noOfTagsIssuedInGivenDatesByCustomerType(@RequestParam("firstdate") String firstdate, @RequestParam("seconddate") String seconddate, @RequestParam("thirddate") String thirddate, @PathVariable("customerType") Integer customerType) throws ParseException {
        List<TagResponse> list = tagsService.noOfTagsIssuedInGivenDatesByCustomerType(firstdate, seconddate, thirddate, customerType);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }


}
