package com.example.weight_manager.getterSetter;

public class Work {
    private String name = "";
    private String url = "";
    private String img = "";

    public Work(String name, String url, String img) {
        this.name = name;
        this.url = url;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlag() {
        return img;
    }

    public void setFlag(String img) {
        this.img = img;
    }
}
