package model;

import org.w3c.dom.Text;

/**
 * Created by u201400194 on 8/10/2016.
 */

public class restaurant {

    private String name;
    private String description;
    private Integer level;
    private Text websiteurl;
    private Text logourl;
    private int status;
    private int service;

    public restaurant() {
    }

    public restaurant(int service, int status, Text logourl, Text websiteurl, Integer level, String description, String name) {
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
