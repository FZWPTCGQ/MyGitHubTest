package com.aqiang.dllo.mybaidumusic.tool.Lrc;

import java.util.List;

/**
 * Created by dllo on 16/12/6.
 * 展示歌词接口
 */

public interface ILrcView {
    /**
     * 设置要展示歌词行的集合
     */
    void setLrc(List<LrcRow> lrcRows);
    /**
     * 音乐播放的时候调用该方法滚动歌词
     */
    void seekLrcToTime(long time);
    /**
     * 摄制歌词拖动时候的监听类
     */
    void setListener(ILrcViewListener l);
}
