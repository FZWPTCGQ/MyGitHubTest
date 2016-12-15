package com.aqiang.dllo.mybaidumusic.tool.RVListener;

import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.SongMenuDetailBean;

/**
 * Created by dllo on 16/12/2.
 */

public interface OnItemClickListener {
    public abstract void onItemClickListener(int position,String songIdList);
    public abstract void method(int position, SongMenuDetailBean songMenuDetailBean);
}
