package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/7.
 */
@Entity
public class CollectionBean {
    @Id
    private Long id;
    private String title;
    private String singer;
    private int isNetMusic;
    private String url;
    private Long time;
    @Generated(hash = 740139754)
    public CollectionBean(Long id, String title, String singer, int isNetMusic,
            String url, Long time) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.isNetMusic = isNetMusic;
        this.url = url;
        this.time = time;
    }
    @Generated(hash = 1423617684)
    public CollectionBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSinger() {
        return this.singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public int getIsNetMusic() {
        return this.isNetMusic;
    }
    public void setIsNetMusic(int isNetMusic) {
        this.isNetMusic = isNetMusic;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
}
