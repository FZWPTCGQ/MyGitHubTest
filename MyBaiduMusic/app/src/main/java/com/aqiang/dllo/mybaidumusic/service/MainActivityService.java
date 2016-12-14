package com.aqiang.dllo.mybaidumusic.service;

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
import android.widget.RemoteViews;

import com.aqiang.dllo.mybaidumusic.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */

public class MainActivityService extends Service {
    private MyBinder mMyBinder;
    private ContentResolver mContentResolver;
    private List<SystemMusicBean> lists;
    private int currentIndex = 0;
    private MediaPlayer mMediaPlayer;


    /**
     * 发一个广播通过(intent对象)
     */
    private Intent intent;
    private SystemMusicBean mSystemMusicBean;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("yyy", "yyy");

        intent = new Intent("MainBR");
        mMediaPlayer = new MediaPlayer();
        mMyBinder = new MyBinder();
        mContentResolver = getContentResolver();
        lists = getSystemMusic();
        showPopupWindow();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
             mMyBinder.nextMusic();
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
              mp.start();
            }
        });
    }

    private void showPopupWindow() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        RemoteViews rvs = new RemoteViews(getPackageName(),R.layout.notification_item_layout);
        /**
         * 退出系统
         */
        Intent intentQuit = new Intent("mp3");
        intentQuit.putExtra("music",1);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),100,intentQuit,PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_closed,pendingIntent);
        /**
         * 播放下一首
         */
        Intent intentNext = new Intent("mp3");
        intentNext.putExtra("music",2);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(getBaseContext(),200,intentNext,PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_next,pendingIntentNext);
        /**
         * 播放上一首
         */
        Intent intentLast = new Intent("mp3");
        intentLast.putExtra("music",3);
        PendingIntent pendingIntentLast = PendingIntent.getBroadcast(getBaseContext(),300,intentLast,PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_past,pendingIntentLast);
        /**
         * 播放
         */
        Intent intentPlay = new Intent("mp3");
        intentPlay.putExtra("music",4);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(getBaseContext(),400,intentPlay,PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_play,pendingIntentPlay);
        /**
         * 暂停
         */
        Intent intentPause = new Intent("mp3");
        intentPause.putExtra("music",5);
        PendingIntent pendingIntentPause = PendingIntent.getBroadcast(getBaseContext(),500,intentPause,PendingIntent.FLAG_CANCEL_CURRENT);
        rvs.setOnClickPendingIntent(R.id.notification_item_layout_pause,pendingIntentPause);

        Log.d("ooo", mSystemMusicBean.getSinge() + mSystemMusicBean.getTitler());
        rvs.setTextViewText(R.id.notification_item_layout_internet_songname,mSystemMusicBean.getTitler());
        rvs.setTextColor(R.id.notification_item_layout_internet_songname, Color.BLACK);
        rvs.setTextViewText(R.id.notification_item_layout_internet_singer,mSystemMusicBean.getSinge());
        rvs.setTextColor(R.id.notification_item_layout_internet_singer,Color.BLACK);
        builder.setContent(rvs);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(1,notification);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }
    public class MyBinder extends Binder{
        /**
         * 获取一共有几首歌
         * @return
         */
       public int getMusicCount(){
           if (lists == null){
               return 0;
           }else{
               return lists.size();
           }

       }
        /**
         * 播放歌曲
         */
        public void playMusic(){
            playMethod();
        }

        /**
         * 播放下一曲
         */
        public void nextMusic(){
            currentIndex++;
            if (currentIndex == lists.size()){
                currentIndex =0;
            }
            playMethod();
        }

        /**
         * 播放上一曲
         */
        public void pastMusic(){
            currentIndex--;
            if (currentIndex < 0){
                currentIndex = lists.size() - 1;
            }
            playMethod();
        }

        /**
         * 暂停音乐
         */
        public void pauseMusic(){
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.pause();
            }else{
                mMediaPlayer.start();
            }
        }
        /**
         * 判断当前音乐是否在播放
         */
        public boolean isPlayingingMusic(){
            return mMediaPlayer.isPlaying();
        }
        /**
         * 获取当前音乐的时长
         */
        public int getCurrentMusicDuration(){
            if (!isPlayingingMusic()){
                return 0;
            }else{
               return mMediaPlayer.getDuration();
            }
        }
        /**
         * 获取当前音乐播放的位置
         */
        public int getCurrentMusicPosition(){
            if (!isPlayingingMusic()){
                return 0;
            }else{
                return mMediaPlayer.getCurrentPosition();
            }
        }
        /**
         * 快进或者快退
         */
        public void getGoAndBack(int position){
            mMediaPlayer.seekTo(position);
        }
    }

    private void playMethod() {
        try {
            SystemMusicBean bean = lists.get(currentIndex);
            String url = bean.getUrl();

                mMediaPlayer.reset();

            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
            intent.putExtra("title",bean.getTitler());
            intent.putExtra("singer",bean.getSinge());
            sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mMediaPlayer.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public List<SystemMusicBean> getSystemMusic(){
        lists = new ArrayList<>();
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
           String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                mSystemMusicBean = new SystemMusicBean(title,singer,url);
                Log.d("ppp", mSystemMusicBean.getTitler() + mSystemMusicBean.getSinge());
                lists.add(mSystemMusicBean);
            }

        }
        cursor.close();
        return lists;
    }
}
