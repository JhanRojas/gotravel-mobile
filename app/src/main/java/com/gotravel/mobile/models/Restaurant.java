package com.gotravel.mobile.models;

import org.w3c.dom.Text;

public class Restaurant {

    private String name;
    private String description;
    private Integer level;
    private Text websiteurl;
    private Text logourl;
    private int status;
    private int service;

    public Restaurant() {
    }

    public Restaurant(int service, int status, Text logourl, Text websiteurl, Integer level, String description, String name) {
        this.service = service;
        this.status = status;
        this.logourl = logourl;
        this.websiteurl = websiteurl;
        this.level = level;
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getService() {
        return service;
    }

    public Text getLogourl() {
        return logourl;
    }

    public int getStatus() {
        return status;
    }

    public Text getWebsiteurl() {
        return websiteurl;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
