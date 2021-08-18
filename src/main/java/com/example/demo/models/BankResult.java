package com.example.demo.models;

import java.util.List;

public class BankResult {

    public List<Bank> getBankList() {
        return BankList;
    }

    public void setBankList(List<Bank> bankList) {
        BankList = bankList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    List<Bank> BankList;
    int totalPages;

    public String getSearchName() {
        return SearchName;
    }

    public void setSearchName(String searchName) {
        SearchName = searchName;
    }

    String SearchName;
}
