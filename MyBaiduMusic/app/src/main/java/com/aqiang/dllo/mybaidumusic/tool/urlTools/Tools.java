package com.aqiang.dllo.mybaidumusic.tool.urlTools;

import com.aqiang.dllo.mybaidumusic.adapter.SeekRV;
import com.aqiang.dllo.mybaidumusic.bean.SeekBean;

/**
 * Created by dllo on 16/11/26.
 */

public class Tools {
    /**
     * 动态页面
     */
    public static  final String pathD = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.ugcfriend.getList&format=json&param=MdQHiv%2F%2BBSYTl39VXcn8dESPvw4rM3naxQSdeb7JiGSv9pqRpzZMYcxA%2FqvopkR2NgdsRojMb6i2CxNCpB%2F98g%3D%3D&timestamp=1480142598&sign=462064952b8866d85f5c007d7a2cf674";
    /**
     * 直播页面
     */
    public static final String pathLive = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.show.live&page_no=1&page_size=40";
    /**
     * 歌单界面最热网址
     */
    public static final String songMenuHot = "http://tingapi.ting.baidu.com/v1/restserver/ting?fro" +
            "m=android&version=5.9.0.0&channel=wandoujia&operator=3&method=b" +
            "aidu.ting.ugcdiy.getChanneldiy&param=W%2Fzu3MyoMJNzCC81NVma3huGI8" +
            "yL9xSf11ECG8WkukpDQSvbmBQRsI0BenRr4U0HTmSXeXV%2FO0os0mE0ipHUQdr3Jw" +
            "Sn3Ky%2FAfxbhQpjNLCN8USUYW0q8LTTSqoWo%2BwgM%2FoHpPeLZm4lJBpfcdzKQg%" +
            "3D%3D&timestamp=1479994900&sign=1bad927a806dab3cedf6946994c98fa4";
    /**
     * 歌单界面最新网址
     */
    public static final String songMenuNew = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&ch" +
            "annel=wandoujia&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=guf6yxDHxhI1WoCcpv" +
            "pO7aahELennW8k6P%2FLBClgXm%2FjBqyjHqKwkoOJPHTXSnFW4d8fS318ge6sB5nB3Zm3JOD5BA1oW%2FcUdnRcZwBU6jlHySphp" +
            "yMhN6VYtC1E9Z7IY3z%2FBGvLUi9It7AEFlgVpQ%3D%3D&timestamp=1479995191&sign=0ca49c22a82d4c24792c1" +
            "fe594fc2c15";
    /**
     * 视频界面最热网址
     */
    public static final String videoHot = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E6%B8%AF%E5%8F%B0";
    /**
     * 视频界面最新网址
     */
    public static final String viewNew = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    /**
     * 榜单界面网址
     */
    public static final String musicList = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";
    /**
     * k个界面轮播图网址
     */
    public static final String KTurn = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.active.showList";
    /**
     * K个界面网址
     */
    public static final String KList = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.learn.now&page_size=50";
    /**
     * 推荐界面
     */
    public static final String RFList = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.plaza.index&cuid=FD91A86A9F44B1249C42381F417D4253";
    /**
     * 榜单详情页网址
     * 363722213
     */
    public static final String MLDUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=wandoujia&operator=3&method=baidu.ting.billboard.billList&format=json&type=参数&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song";
    /**
     * 音乐界面榜单界面音乐网址前半部分
     */
    public static final String PLAY_MUSIC_BEFORE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    /**
     * 音乐界面榜单界面音乐网址后半部分
     */
    public static final String PLAY_MUSIC_AFTER = "&_=1413017198449";

    /**
     * 歌单详情页面的网址
     */
    public static final String SONG_MUSIC_LIST_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=ppzs&operator=0&method=baidu.ting.diy.gedanInfo&format=json&listid=参数&isUGC=0";

    /**
     * 歌曲播放模式
     */
    //顺序
    public static final int SONG_PLAY_MODE_ORDER = 1;
    //单曲
    public static final int SONG_PLAY_MODE_SINGLE = 2;
    //循环
    public static final int SONG_PLAY_MODE_CYCLE = 3;
    //随机
    public static final int SONG_PLAY_MODE_RANDOM = 4;


    /**
     * 中间弹出的popWindow左边页面列表网址
     */
  //  public static final String LEFT_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.song.getRecommandSongList&song_id=参数&num=7";
  //  private String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.song.getRecommandSongList&song_id=266322598&num=7";
      public static final String LEFT_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.song.getRecommandSongList&song_id=参数&num=7";
    /**
     * 直播界面的详情页面网址
     */
    //全部
    public static final String LIVE_ONE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=hot";
    //女神
    public static final String LIVE_TWO = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=3";
    //好声音
    public static final String LIVE_THREE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=new";
    //新秀
    public static final String LIVE_FOUR = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=new";
    //劲爆
    public static final String LIVE_FIVE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=15";
    //搞笑
    public static final String LIVE_SIX = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=1";
    //萌妹
    public static final String LIVE_SEVEN = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=15";
    //推荐
    public static final String LIVE_EIGHT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=4&category=recommend";


    //广播过滤器
    public static final String ACTION_MAINBR = "MainBR";
    public static final String ACTION_MP3 = "mp3";
    public static final String ACTION_REPLACE = "replace";

    //搜索页网址
    public static final String SEEK_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.search.hot";
    //搜索内容网址
//    public static final String SEEK_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.search.catalogSug&format=json&query=参数";
//    public static final String SEEK_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=-1&method=baidu.ting.search.catalogSug&format=json&query=参数";
    public static final String SEEK_DETAIL_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=-1&method=baidu.ting.search.catalogSug&format=json&query=参数";
}
