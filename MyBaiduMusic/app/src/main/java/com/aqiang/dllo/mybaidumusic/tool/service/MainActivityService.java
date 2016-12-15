package com.aqiang.dllo.mybaidumusic.tool.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.activity.MainActivity;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.musiclistSonBean.PlayBean;

import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBPlayTools;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 16/11/24.
 */

public class MainActivityService extends Service {
    private MyBinder mMyBinder;
    private ContentResolver mContentResolver;
    private static List<SystemMusicBean> lists;
    private static int currentIndex;
    private MediaPlayer mMediaPlayer;
    private EventBus mEventBus;
    /**
     * 播放音乐的网址的集合
     */
    private static List<String> urlPlayDatas = new ArrayList<>();
    /**
     * 当前播放歌曲的模式
     */
    private static int currentMode;
    private String song_id;

    /**
     * 发一个广播通过(intent对象)
     */
    private Intent mIntent;
    private SystemMusicBean mSystemMusicBean;
    private int isNetMusic = 1;
    private SystemMusicBean mBean;
    private PlayBean mPlayBean;
    private String mMusicLink;
//    private SB mSb;
    private int position = 0;
    private int p = 0;
    private String mMusicUrl;

    @Override
    public void onCreate() {
        /**
         * 注册EventBus
         */
        EventBus.getDefault().register(this);
        super.onCreate();
        Log.d("yyy", "yyy");
        //初始化eventBus来记录随机情况,顺序情况,单曲情况下手动上一曲,下一曲的位置
        mEventBus = EventBus.getDefault();
        mIntent = new Intent(Tools.ACTION_MAINBR);
        mMediaPlayer = new MediaPlayer();
        mMyBinder = new MyBinder();
        mContentResolver = getContentResolver();
        lists = getSystemMusic();
        if(lists != null && lists.size() > 0){

            mBean = lists.get(currentIndex);
            mIntent.putExtra("title", mBean.getTitler());
            mIntent.putExtra("singer", mBean.getSinge());
            Log.d("MM", mIntent.getStringExtra("title"));
            Log.d("MM", mIntent.getStringExtra("singer"));
            mIntent.putExtra("many",lists.size());
            Log.d("yyy", lists.size() + "----lists.size()");
            /**
             * 展示通知栏
             */
            showNotificationWindow();
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (isNetMusic == 1){
                    switch (currentMode){
                        case Tools.SONG_PLAY_MODE_ORDER:
                            /**
                             * 播放下一首
                             */
                            mMyBinder.playNetNextMp3();
                            break;
                        case Tools.SONG_PLAY_MODE_CYCLE:
                            /**
                             * 播放下一首
                             */
                            mMyBinder.playNetNextMp3();
                            break;
                        case Tools.SONG_PLAY_MODE_RANDOM:
                            /**
                             * 随机播放
                             */
                            mMyBinder.playNetRandomMp3();
                            break;
                        case Tools.SONG_PLAY_MODE_SINGLE:
                            /**
                             * 单曲播放
                             */
                            mMyBinder.playNetSingleMp3();
                            break;
                    }
                }else{
                    mMyBinder.nextMusic();
                }


            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }



    private void showNotificationWindow() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        RemoteViews rvs = new RemoteViews(getPackageName(), R.layout.notification_item_layout);
        /**
         * 退出系统
         */
        Intent intentQuit = new Intent(Tools.ACTION_MP3);
        intentQuit.putExtra("music", 1);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 100, intentQuit, PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_closed, pendingIntent);
        /**
         * 播放下一首
         */
        Intent intentNext = new Intent(Tools.ACTION_MP3);
        intentNext.putExtra("music", 2);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(getBaseContext(), 200, intentNext, PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_next, pendingIntentNext);
        /**
         * 播放上一首
         */
        Intent intentLast = new Intent(Tools.ACTION_MP3);
        intentLast.putExtra("music", 3);
        PendingIntent pendingIntentLast = PendingIntent.getBroadcast(getBaseContext(), 300, intentLast, PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_past, pendingIntentLast);
        /**
         * 播放
         */
        Intent intentPlay = new Intent(Tools.ACTION_MP3);
        intentPlay.putExtra("music", 4);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(getBaseContext(), 400, intentPlay, PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_play, pendingIntentPlay);
        /**
         * 暂停
         */
        Intent intentPause = new Intent(Tools.ACTION_MP3);
        intentPause.putExtra("music", 5);
        PendingIntent pendingIntentPause = PendingIntent.getBroadcast(getBaseContext(), 500, intentPause, PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_pause, pendingIntentPause);

        Log.d("ooo", mSystemMusicBean.getSinge() + mSystemMusicBean.getTitler());
        rvs.setTextViewText(R.id.notification_item_layout_internet_songname, mSystemMusicBean.getTitler());
        rvs.setTextColor(R.id.notification_item_layout_internet_songname, Color.BLACK);
        rvs.setTextViewText(R.id.notification_item_layout_internet_singer, mSystemMusicBean.getSinge());
        rvs.setTextColor(R.id.notification_item_layout_internet_singer, Color.BLACK);
        builder.setContent(rvs);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("yyy", "哈哈");
        try {
            Thread.sleep(10);
            sendBroadcast(mIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return mMyBinder;
    }

    public class MyBinder extends Binder {
        /**
         * 获取一共有几首歌
         *
         * @return
         */
        public int getMusicCount() {
            if (lists == null) {
                return 0;
            } else {
                return lists.size();
            }

        }

        /**
         * 播放本地音乐歌曲
         */
        public void playMusic() {
            playMethod();
        }

        /**
         * 播放网络歌曲
         */

        public void playNetMp3() {
            /**
             * 判断网址不为空
             */
            currentIndex++;

            if (currentIndex == urlPlayDatas.size()){
                currentIndex = 0;
            }
            try {
                if (urlPlayDatas != null && !urlPlayDatas.isEmpty()) {
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(urlPlayDatas.get(currentIndex++));
                    mMediaPlayer.prepareAsync();
                    mMediaPlayer.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /**
         * 同步音乐时间
         */
        private String syncDuration(long time) {
            if (mMyBinder != null) {
                long currentDuration = time;
                long duration = currentDuration;
                duration = duration / 1000;
                String minute = add0(String.valueOf(duration / 60));
                String second = add0(String.valueOf(duration % 60));
                return minute + ":" + second;
            }
            return "00:00";
        }

        private String add0(String l) {
            StringBuffer temp = new StringBuffer(l);
            if (temp.length() == 1) {
                temp = new StringBuffer();
                temp.append("0");
                temp.append(l);
            }
            return temp.toString();
        }
        /**
         * 顺序播放下一首网络歌曲
         */
        public void playNetNextMp3() {
            MainActivity.mImageViewI.setVisibility(View.INVISIBLE);
            MainActivity.mImageViewII.setVisibility(View.INVISIBLE);
            /**
             * 判断网址不为空
             */
            currentIndex++;
            /**
             * 将顺序播放的下一个位置记录下来传给activity
             */
            Bean bean = new Bean(currentIndex);
            mEventBus.post(bean);
            Log.d("学不会vajk", currentIndex + "");
            if (currentIndex == urlPlayDatas.size()){
                currentIndex = 0;
            }
            song_id = DBPlayTools.getInstance().queryAll().get(currentIndex).getUrl();
             mMusicUrl = Tools.PLAY_MUSIC_BEFORE + song_id + Tools.PLAY_MUSIC_AFTER;
            /**
             * 顺序情况下忽然由顺序播放变为单曲循环将循环后的位置传给单曲循环的位置
             */
            position = currentIndex;




            MainActivity.mTextViewTitle.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            MainActivity.mTextViewAuthor.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.mSingerTv.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.mNameTv.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());

            /**
             * pop左边页面详情内容
             */
            MainActivity.leftLlAuthor.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.leftLlTitle.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            sameMethod();


        }
        /**
         * 单曲播放网络歌曲
         */
        public void playNetSingleMp3(){
            MainActivity.mImageViewI.setVisibility(View.INVISIBLE);
            MainActivity.mImageViewII.setVisibility(View.INVISIBLE);

            Log.d("政策比较好", currentIndex + "");

            String song_id = DBPlayTools.getInstance().queryAll().get(position).getUrl();
            Log.d("方后续快捷方式", position + "");
            mMusicUrl = Tools.PLAY_MUSIC_BEFORE + song_id + Tools.PLAY_MUSIC_AFTER;
            MainActivity.mTextViewTitle.setText(DBPlayTools.getInstance().queryAll().get(position).getTitle());
            MainActivity.mTextViewAuthor.setText(DBPlayTools.getInstance().queryAll().get(position).getSinger());
            MainActivity.mSingerTv.setText(DBPlayTools.getInstance().queryAll().get(position).getSinger());
            MainActivity.mNameTv.setText(DBPlayTools.getInstance().queryAll().get(position).getTitle());
            /**
             * pop左边页面详情内容
             */
            MainActivity.leftLlAuthor.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.leftLlTitle.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            sameMethod();


        }

        /**
         * 随机播放网络歌曲
         */
        public void playNetRandomMp3(){
            MainActivity.mImageViewI.setVisibility(View.INVISIBLE);
            MainActivity.mImageViewII.setVisibility(View.INVISIBLE);
            Random random = new Random();
            currentIndex = random.nextInt(DBPlayTools.getInstance().queryAll().size());
            /**
             * 如果在随机的情况下忽然变成单曲循环时,将随机后的位置传给position
             */
            position = currentIndex;

            /**
             * 将随机的位置记录下来,传入activity,因为我的下一曲播放上一曲播放,都是在activity中完成的
             */
            Bean bean = new Bean(currentIndex);
            mEventBus.post(bean);
            song_id =  DBPlayTools.getInstance().queryAll().get(currentIndex).getUrl();
            mMusicUrl = Tools.PLAY_MUSIC_BEFORE + song_id + Tools.PLAY_MUSIC_AFTER;
            MainActivity.mTextViewTitle.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            MainActivity.mTextViewAuthor.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.mSingerTv.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.mNameTv.setText(DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            /**
             * pop左边页面详情内容
             */
            MainActivity.leftLlAuthor.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getSinger());
            MainActivity.leftLlTitle.setText("歌手 " + ": " + DBPlayTools.getInstance().queryAll().get(currentIndex).getTitle());
            sameMethod();
        }

        /**
         * 播放模式的方法体
         */
        private void sameMethod() {
            if (DBPlayTools.getInstance().queryAll().get(position).getTime() != null && !DBPlayTools.getInstance().queryAll().get(position).getTime().toString().isEmpty()){

                MainActivity.mTextViewAllTime.setText(syncDuration(DBPlayTools.getInstance().queryAll().get(position).getTime() * 1000) + "");
            }
            NetHelper.MyRequest(mMusicUrl, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    String data = response.substring(1, response.length() - 2);
                    Log.d("学不会vajk", data);
                    Gson gson = new Gson();
                    mPlayBean = gson.fromJson(data, PlayBean.class);
                    mMusicLink = mPlayBean.getBitrate().getFile_link();
                    Log.d("学不会vajk", mMusicLink);
                    try {
                        mMediaPlayer.reset();
                        mMediaPlayer.setDataSource(mMusicLink);
                        mMediaPlayer.prepareAsync();
                        mMediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }

        /**
         * 播放下一曲
         */
        public void nextMusic() {
            currentIndex++;
            if (currentIndex == lists.size()) {
                currentIndex = 0;
            }
            playMethod();
        }

        /**
         * 2016:获取mediaplayer
         */

        public MediaPlayer getMediaPlayer() {

            return mMediaPlayer;
        }


        /**
         * 播放上一曲
         */
        public void pastMusic() {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = lists.size() - 1;
            }
            playMethod();
        }

        /**
         * 暂停音乐
         */
        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        }

        /**
         * 判断当前音乐是否在播放
         */
        public boolean isPlayingingMusic() {
            return mMediaPlayer.isPlaying();
        }

        /**
         * 获取当前音乐的时长
         */
        public int getCurrentMusicDuration() {
            /**
             * 判断当前音乐是否在播放
             */
            if (!isPlayingingMusic()) {
                return 0;
            }
                return mMediaPlayer.getDuration();

        }

        /**
         * 获取当前音乐播放的位置
         */
        public int getCurrentMusicPosition() {
            /**
             * 判断当前音乐是否在播放
             */
            if (!isPlayingingMusic()) {
                return 0;
            }
                return mMediaPlayer.getCurrentPosition();

        }

        /**
         * 快进或者快退
         */
        public void getGoAndBack(int position) {
            mMediaPlayer.seekTo(position);
        }

        public void clearDatas() {
            urlPlayDatas.clear();
        }

        public void setNetDatas(String musicUrl) {
            urlPlayDatas.add(musicUrl);
        }

        public void setCurrentIndex(int index) {
            currentIndex = index;
        }



    }


    /**
     * 播放本地歌曲
     */
    private void playMethod() {
        try {
            SystemMusicBean bean = lists.get(currentIndex);
            String url = bean.getUrl();
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
            mIntent.putExtra("title", bean.getTitler());
            mIntent.putExtra("singer", bean.getSinge());
            sendBroadcast(mIntent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mMediaPlayer.stop();
        return super.onUnbind(intent);
    }
 @Subscribe(threadMode = ThreadMode.MAIN)
 public void getData(Bean bean){
     position = bean.getPostion();
     Log.d("传值", "position:" + position);
 }

    /**
     * 从activity上获取当前点击事前所选中的模式将其传入到服务当中,调用对应的播放模式的方法
     * @param modeBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMode(ModeBean modeBean){
        currentMode = modeBean.getMODE();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 解除注册EventBus,只需要一次就好
         */
        EventBus.getDefault().unregister(this);
    }

    public List<SystemMusicBean> getSystemMusic() {
        lists = new ArrayList<>();
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                mSystemMusicBean = new SystemMusicBean(title, singer, url);
                Log.d("ppp", mSystemMusicBean.getTitler() + mSystemMusicBean.getSinge());
                lists.add(mSystemMusicBean);
            }

        }
        cursor.close();
        return lists;
    }

}
