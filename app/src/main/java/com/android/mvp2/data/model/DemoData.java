package com.android.mvp2.data.model;

/**
 * Created by cjw on 2016/7/8.
 */
public class DemoData {

    private String name; // 库的名字
    private String description; // 描述
    private String language; // 语言

    public DemoData() {

    }

    public DemoData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
