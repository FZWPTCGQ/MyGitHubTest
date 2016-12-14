package com.aqiang.dllo.mybaidumusic.service;

/**
 * Created by dllo on 16/11/24.
 */

public class SystemMusicBean  {
    private String titler;
    private String singe;
    private String url;

    public SystemMusicBean() {
    }

    public SystemMusicBean(String titler, String singe, String url) {
        this.titler = titler;
        this.singe = singe;
        this.url = url;
    }

    public String getTitler() {
        return titler;
    }

    public void setTitler(String titler) {
        this.titler = titler;
    }

    public String getSinge() {
        return singe;
    }

    public void setSinge(String singe) {
        this.singe = singe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
