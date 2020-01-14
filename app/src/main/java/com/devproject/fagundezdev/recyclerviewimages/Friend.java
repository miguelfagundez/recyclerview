package com.devproject.fagundezdev.recyclerviewimages;

public class Friend {

    private String fName;
    private String lName;
    private String url;


    public Friend(String fName, String lName, String url) {
        this.fName = fName;
        this.lName = lName;
        this.url = url;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
