package com.example.efkon.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class TxnResponse {
    Integer status;
    String statusDesc;
    Integer count;
    Integer customerType;
    String customerTypeStr;
    Integer month;

    public TxnResponse() {

    }

    public TxnResponse(Integer status, String statusDesc, Integer count, Integer customerType, String customerTypeStr, Integer month) {
        this.status = status;
        this.statusDesc = statusDesc;
        this.count = count;
        this.customerType = customerType;
        this.customerTypeStr = customerTypeStr;
        this.month = month;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }
}
