package com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.DefaultLrcBuilder;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcBuilder;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcView;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcViewListener;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.LrcRow;
import com.aqiang.dllo.mybaidumusic.tool.service.MainActivityService;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends BaseFragment {




    //自定义LrcView，用来展示歌词
    ILrcView mLrcView;
    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;
    //歌词的网址
    private String lrcUrl="http://musicdata.baidu.com/data2/lrc/569291fde50375827e25444ab7baab60/269082162/269082162.lrc";
    private MediaPlayer mMediaPlayer;
    private MainActivityService.MyBinder mMyBinder;
    private ServiceConnection mServiceConnection;
    private Intent intent;

    @Override
    protected int setLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view) {
        mLrcView = (ILrcView)getView().findViewById(R.id.right_lrcView);
        mMediaPlayer = new MediaPlayer();
        intent = new Intent(context,MainActivityService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMyBinder = (MainActivityService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
        mMediaPlayer = mMyBinder.getMediaPlayer();
    }

    @Override
    protected void initData() {
        NetHelper.MyRequest(lrcUrl, new NetListener<String>() {
            @Override
            public void successListener(String response) {
//                L.d("解析成功!!!");
                String praseResult = null; // 解决中文乱码问题
                try {
                    praseResult = new String(response.getBytes("ISO8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String lrc=praseResult;
//                T.show(lrc,2000);
                //解析歌词构造器
                ILrcBuilder builder = new DefaultLrcBuilder();
                //解析歌词返回LrcRow集合
                List<LrcRow> rows = builder.getLrcRows(lrc);
                //将得到的歌词集合传给mLrcView用来展示
                mLrcView.setLrc(rows);

                //开始播放歌曲并同步展示歌词
                beginLrcPlay();

                //设置自定义的LrcView上下拖动歌词时监听
                mLrcView.setListener(new ILrcViewListener() {
                    //当歌词被用户上下拖动的时候回调该方法,从高亮的那一句歌词开始播放
                    public void onLrcSeeked(int newPosition, LrcRow row) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.seekTo((int) row.time);
                        }
                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay(){
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource("http://yinyueshiting.baidu.com/data2/music/6b52c38590b8f59274138fb036a483d0/266603096/266603096.mp3?xcode=a95e4972a69d33230fd1a9e14bd081d7");
            //准备播放歌曲监听
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //准备完毕
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if(mTimer == null){
                        mTimer = new Timer();
                        mTask = new LrcTask();
                        mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
                    }
                }
            });
            //歌曲播放完毕监听
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stopLrcPlay();
                }
            });
            //准备播放歌曲
            mMediaPlayer.prepare();
            //开始播放歌曲
            mMediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止展示歌曲
     */
    public void stopLrcPlay(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }


    /**
     * 展示歌曲的定时任务
     */
    class LrcTask extends TimerTask{
        @Override
        public void run() {
            //获取歌曲播放的位置
            final long timePassed = mMediaPlayer.getCurrentPosition();
            ((Activity)context).runOnUiThread(new Runnable() {
                public void run() {
                    //滚动歌词
                    mLrcView.seekLrcToTime(timePassed);
                }
            });

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

}
