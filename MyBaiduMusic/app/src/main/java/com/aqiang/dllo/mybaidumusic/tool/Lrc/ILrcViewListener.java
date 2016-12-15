package com.aqiang.dllo.mybaidumusic.tool.Lrc;

/**
 * Created by dllo on 16/12/6.
 * 歌词拖动时候的监听类
 */
public interface ILrcViewListener {
    /**
     * 当歌词被用户上下拖动的时候回调该方法
     */
    void onLrcSeeked(int newPosition, LrcRow row);
}
