package com.pandit.application.fcmnotificationpractice.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Deal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String storeNAME;
    private String deal;
    private String dealDesc;
    private String expiry;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreNAME() {
        return storeNAME;
    }

    public void setStoreNAME(String storeNAME) {
        this.storeNAME = storeNAME;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}