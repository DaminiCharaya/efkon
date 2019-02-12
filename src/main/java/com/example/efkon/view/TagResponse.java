package com.example.efkon.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class TagResponse {
    Integer status;
    String statusDesc;
    Integer count;
    Integer customerType;
    String customerTypeStr;
    Integer month;

    public TagResponse() {

    }

    public TagResponse(Integer status, String statusDesc, Integer count, Integer customerType, String customerTypeStr,Integer month) {
        this.status = status;
        this.statusDesc = statusDesc;
        this.count = count;
        this.customerType = customerType;
        this.customerTypeStr = customerTypeStr;
        this.month=month;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerTypeStr = customerType == 1 ? "Retailer" : "Corporate";
        this.customerType = customerType;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {

        this.customerTypeStr = customerTypeStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
