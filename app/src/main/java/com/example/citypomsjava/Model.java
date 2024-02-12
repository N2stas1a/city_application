package com.example.citypomsjava;

public class Model {

    private String src_url, title;

    private int sid;

    public Model(int id, String src_url,String body) {
        this.src_url = src_url;
        this.title = title;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
