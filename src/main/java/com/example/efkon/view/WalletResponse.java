package com.example.efkon.view;

public class WalletResponse {

    int counts;
    Integer customerType;
    String customerTypeStr;

    public WalletResponse()
    {

    }

    public WalletResponse(int counts, Integer customerType, String customerTypeStr) {
        this.counts = counts;
        this.customerType = customerType;
        this.customerTypeStr = customerTypeStr;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerTypeStr = customerType==1?"Retailer": "Corporate";
        this.customerType = customerType;
    }

    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {

        this.customerTypeStr = customerTypeStr;
    }


    public int getCount() {
        return counts;
    }

    public void setCount(int count) {
        this.counts = count;
    }
}
