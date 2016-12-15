package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/10.
 */
@Entity
public class PlayBean {
    @Id
    private Long id;
    private String title;
    private String singer;
    private String url;
    private Long time;
    @Generated(hash = 276542993)
    public PlayBean(Long id, String title, String singer, String url, Long time) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.url = url;
        this.time = time;
    }
    @Generated(hash = 1251142461)
    public PlayBean() {
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
