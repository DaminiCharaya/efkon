package com.example.efkon.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.criteria.CriteriaBuilder;

@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class WalletResponse {

    Integer count;
    Integer customerType;
    String customerTypeStr;
    Integer month;

    public WalletResponse() {

    }

    public WalletResponse(Integer counts, Integer customerType, String customerTypeStr, Integer month) {
        this.count = count;
        this.customerType = customerType;
        this.customerTypeStr = customerTypeStr;
        this.month = month;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
