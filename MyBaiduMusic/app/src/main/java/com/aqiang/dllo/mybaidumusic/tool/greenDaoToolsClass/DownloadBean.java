package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/9.
 */
@Entity
public class DownloadBean {
    @Id
    private Long id;
    private String singer;
    private String title;
    private String Url;
    @Generated(hash = 1697991586)
    public DownloadBean(Long id, String singer, String title, String Url) {
        this.id = id;
        this.singer = singer;
        this.title = title;
        this.Url = Url;
    }
    @Generated(hash = 2040406903)
    public DownloadBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSinger() {
        return this.singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return this.Url;
    }
    public void setUrl(String Url) {
        this.Url = Url;
    }

}
