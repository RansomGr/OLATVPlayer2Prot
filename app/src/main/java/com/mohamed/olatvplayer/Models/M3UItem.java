package com.mohamed.olatvplayer.Models;

public class M3UItem {

    private String itemDuration;

    private String itemName;

    private String itemUrl;

    private String itemIcon;

    private String group;

    private String userAgent;

    public M3UItem(String itemDuration, String itemName, String itemUrl, String itemIcon, String group, String userAgent) {
        this.itemDuration = itemDuration;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.itemIcon = itemIcon;
        this.group = group;
        this.userAgent = userAgent;
    }

    public M3UItem() {
    }

    public String getItemDuration() {
        return itemDuration;
    }

    public void setItemDuration(String itemDuration) {
        this.itemDuration = itemDuration;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "M3UItem{" +
                "itemDuration='" + itemDuration + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", itemIcon='" + itemIcon + '\'' +
                ", group='" + group + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}

