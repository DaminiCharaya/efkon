package com.example.efkon.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.criteria.CriteriaBuilder;

@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class WalletResponse {

    Integer count;
    Integer customerType;
    String customerTypeStr;

    public WalletResponse() {

    }

    public WalletResponse(Integer counts, Integer customerType, String customerTypeStr) {
        this.count = count;
        this.customerType = customerType;
        this.customerTypeStr = customerTypeStr;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerTypeStr = customerType == 1 ? "Retailer" : "Corporate";
        this.customerType = customerType;
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
