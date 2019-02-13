package com.example.efkon.controller;

import com.example.efkon.ex.NotFoundException;
import com.example.efkon.service.TagsService;
import com.example.efkon.view.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jnlp.IntegrationService;
import javax.swing.text.html.HTML;
import javax.ws.rs.Path;
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


    @GetMapping("/tags/{customerType}/sortbymonth")
    public ResponseEntity<List<TagResponse>> fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(@PathVariable("customerType") Integer customerType) {

        List<TagResponse> list = tagsService.fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(customerType);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/tags/{customerType}/sortbymonthonly")
    public ResponseEntity<List<TagResponse>> fetchTagCountByCustomerTypeAndSortByMonth(@PathVariable("customerType") Integer customerType) {

        List<TagResponse> list = tagsService.fetchTagCountByCustomerTypeAndSortByMonth(customerType);
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

    @GetMapping("/sorttagsbymonth")
    public ResponseEntity<List<TagResponse>> fetchTagCountGroupByCustomerTypeAndSortByMonth() {
        List<TagResponse> list = tagsService.fetchTagCountGroupByCustomerTypeAndSortByMonth();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }


    @GetMapping("/tagscountbymonth")
    public ResponseEntity<List<TagResponse>> fetchTagCountGroupByCustomerTypeAndByMonth(@RequestParam("month") Integer month) throws ParseException {
        List<TagResponse> list = tagsService.fetchTagCountGroupByCustomerTypeAndByMonth(month);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/tags/{customerType}/month")
    public ResponseEntity<List<TagResponse>> fetchTagCountByCustomerTypeAndByMonth(@PathVariable("customerType") Integer customerType, @RequestParam("month") Integer month) throws ParseException {
        List<TagResponse> list = tagsService.fetchTagCountByCustomerTypeAndByMonth(customerType, month);
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

    @GetMapping("/tags/samedate")
    public ResponseEntity<?> fetchTagCountBySameDateGroupByCustomerType(@RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.fetchTagCountBySameDateGroupByCustomerType(date);
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

    @GetMapping("nooftagsofretailerbystatus/{status}")
    public ResponseEntity<List<?>> noOfTagsOfRetailerByStatus(@PathVariable("status") Integer status) {
        List<?> list = tagsService.noOfTagsOfRetailerByStatus(status);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("active30forretail/{noOfTags}")
    public ResponseEntity<List<?>> active30forretail(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.active30forretail(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("activationrateforretail/{noOfTags}")
    public ResponseEntity<List<?>> activationrateforretail(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.activationrateforretail(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("activationrateforcorporate/{noOfTags}")
    public ResponseEntity<List<?>> activationrateforcorporate(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.activationrateforcorporate(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("activationrate/{noOfTags}")
    public ResponseEntity<List<?>> activationrate(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.activationrate(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }

    @GetMapping("active30forcorporate/{noOfTags}")
    public ResponseEntity<List<?>> active30forcorporate(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.active30forcorporate(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("active30/{noOfTags}")
    public ResponseEntity<List<TagResponse>> active30(@PathVariable("noOfTags") Integer noOfTags, @RequestParam("date") String date) throws ParseException {
        List<TagResponse> list = tagsService.active30(noOfTags, date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<TagResponse>>(list, HttpStatus.OK);

    }

    @GetMapping("nooftagsofcorporatebystatus/{status}")
    public ResponseEntity<List<?>> noOfTagsOfCorporateByStatus(@PathVariable("status") Integer status) {
        List<?> list = tagsService.noOfTagsOfCorporateByStatus(status);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }


    @GetMapping("nooftagsbystatus/{status}")
    public ResponseEntity<List<?>> noOfTagsByStatus(@PathVariable("status") Integer status) {
        List<?> list = tagsService.noOfTagsByStatus(status);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);

    }

    @GetMapping("noofnewtagsissuedinoctnovdecforretailer/{status}")
    public ResponseEntity<List<?>> noOfNewTagsIssuedInOctNovDecByStatusForRetailer(@PathVariable("status") Integer status,@RequestParam("year")Integer year)
    {
        List<?> list = tagsService.noOfNewTagsIssuedInOctNovDecByStatusForRetailer(status,year);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }


    @GetMapping("noofnewtagsissuedinoctnovdecforcorporate/{status}")
    public ResponseEntity<List<?>> noOfNewTagsIssuedInOctNovDecByStatusForCorporate(@PathVariable("status") Integer status,@RequestParam("year")Integer year)
    {
        List<?> list = tagsService.noOfNewTagsIssuedInOctNovDecByStatusForCorporate(status,year);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("/newtagactivationrateforretail/{noOfTags}/{status}")
    public ResponseEntity<List<?>> newTagActivationRateForRetail(@PathVariable("noOfTags") Integer noOfTags,@PathVariable("status") Integer status, @RequestParam("year")Integer year)
    {

        List<?> list = tagsService.newTagActivationRateForRetail(noOfTags,status,year);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }


    @GetMapping("/newtagactivationrateforcorporate/{noOfTags}/{status}")
    public ResponseEntity<List<?>> newTagActivationRateForCorporate(@PathVariable("noOfTags") Integer noOfTags,@PathVariable("status") Integer status, @RequestParam("year")Integer year)
    {

        List<?> list = tagsService.newTagActivationRateForCorporate(noOfTags,status,year);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("frequencyofusageforretail")
    public ResponseEntity<List<?>> frequencyOfUsageForRetail(@RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.frequencyOfUsageForRetail(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    @GetMapping("frequencyofusageforcorporate")
    public ResponseEntity<List<?>> frequencyOfUsageForCorporate(@RequestParam("date") String date) throws ParseException {
        List<?> list = tagsService.frequencyOfUsageForCorporate(date);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity(new NotFoundException("can't find the requested data"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }
}
