package com.aqiang.dllo.mybaidumusic.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.DynamicFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.LiveFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.MineFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.MusicFragment;
import com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment.MainFragment;
import com.aqiang.dllo.mybaidumusic.service.MainActivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/22.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView playIv;
    private ImageView pauseIv;
    private ImageView showDrawIv;
    private DrawerLayout drawerLayout;
    private ImageView drawerBtn;
    private TabAdapter tabAdapter;
    private List<Fragment> fragments;
    private TabLayout tab;
    private ViewPager viewPager;
    private ImageView mNextSong;
    private MainActivityService.MyBinder mMyBinder;
    private Intent intent;
    private ServiceConnection mServiceConnection;
    private TextView mSingerTv;
    private TextView mNameTv;
    private MainBR mMainBR;
    private ImageView mPlayBtnN;
    private ImageView mPauseBtnN;
    private LinearLayout mPlayMusicHurdle;
    private ImageView mSeekIv;
    private DrawerLayout mDrawerLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        /**
         * 初始化布局控件
         */
        addViews();


    }

    private void addViews() {
        View view = LayoutInflater.from(this).inflate(R.layout.notification_item_layout, null);
        mPlayBtnN = (ImageView) view.findViewById(R.id.notification_item_layout_play);
        mPauseBtnN = (ImageView) view.findViewById(R.id.notification_item_layout_pause);
        intent = new Intent(this, MainActivityService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                mMyBinder = (MainActivityService.MyBinder) service;
                if (mMyBinder != null) {
                    mMyBinder.playMusic();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


        tab = (TabLayout) findViewById(R.id.main_tab);
        viewPager = (ViewPager) findViewById(R.id.main_vp);
        mSeekIv = (ImageView)findViewById(R.id.main_activity_seek);
        showDrawIv = (ImageView) findViewById(R.id.main_show_draw_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.root_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.root_drawer_seek);

        drawerBtn = (ImageView) findViewById(R.id.drawer_btn);
        fragments = new ArrayList<>();
        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        /**
         * 播放音乐组件初始化
         */
        playIv = (ImageView) findViewById(R.id.song_play_iv);
        pauseIv = (ImageView) findViewById(R.id.song_pause_iv);
        mNextSong = (ImageView) findViewById(R.id.main_foot_next_iv);
        mSingerTv = (TextView) findViewById(R.id.main_foot_singer_tv);
        mNameTv = (TextView) findViewById(R.id.main_foot_name_tv);
        mPlayMusicHurdle = (LinearLayout) findViewById(R.id.activity_main_play_music_hurdle_ll);

    }

    @Override
    protected void initDatas() {
        /**
         * 添加数据
         */
        addDatas();
        /**
         * 监听事件
         */
        addListener();
        /**
         * 注册广播接受者
         */
        registerBR();
    }

    private void registerBR() {
        mMainBR = new MainBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MainBR");
        intentFilter.addAction("mp3");
        registerReceiver(mMainBR, intentFilter);
    }

    private void addDatas() {
        fragments.add(new MineFragment());
        fragments.add(new MusicFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());
        viewPager.setAdapter(tabAdapter);
        tab.setupWithViewPager(viewPager);

    }

    private void addListener() {
        playIv.setOnClickListener(this);
        pauseIv.setOnClickListener(this);
        showDrawIv.setOnClickListener(this);
        drawerBtn.setOnClickListener(this);
        mNextSong.setOnClickListener(this);
        mPlayMusicHurdle.setOnClickListener(this);
        mSeekIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 播放音乐按钮
             */
            case R.id.song_play_iv:
                pauseIv.setVisibility(View.VISIBLE);
                playIv.setVisibility(View.INVISIBLE);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                if (mMyBinder != null) {
                    mMyBinder.pauseMusic();
                }
                break;
            /**
             * 暂停音乐按钮
             */
            case R.id.song_pause_iv:
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                playIv.setVisibility(View.VISIBLE);
                pauseIv.setVisibility(View.INVISIBLE);
                if (mMyBinder != null) {
                    mMyBinder.pauseMusic();
                }
                break;
            /**
             * 播放下一曲
             */
            case R.id.main_foot_next_iv:
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                if (mMyBinder != null) {
                    mMyBinder.nextMusic();
                }
                break;
            /**
             * 弹出抽屉按钮左边图案
             */
            case R.id.main_show_draw_iv:
                drawerLayout.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(Gravity.RIGHT);
                Animation in = AnimationUtils.loadAnimation(this, R.anim.in);
                drawerLayout.setAnimation(in);
                drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        drawerLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });

                break;
            /**
             * 关闭抽屉左边图案
             */
            case R.id.drawer_btn:
                Animation out = AnimationUtils.loadAnimation(this, R.anim.out);
                drawerLayout.setAnimation(out);
                drawerLayout.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.RIGHT);

                break;
            /**
             * 下边音乐播放栏添加fragment
             */
            case R.id.activity_main_play_music_hurdle_ll:
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.in);
                FragmentManager fragmentManager = getSupportFragmentManager();

                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                /**
                 *
                 */
                if (fragmentManager.findFragmentByTag("fra_from_down_music_main")==null) {
                    fragmentTransaction.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out);
                    fragmentTransaction.replace(R.id.main_rv, new MainFragment(), "fra_from_down_music_main");

                    fragmentTransaction.commit();
                }
                break;
            case R.id.main_activity_seek:
                mDrawerLayout.setVisibility(View.VISIBLE);
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                Animation inSeek = AnimationUtils.loadAnimation(this, R.anim.in);
                mDrawerLayout.setAnimation(inSeek);
                mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        mDrawerLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });
                break;

        }
    }

    /**
     * tabViewPager适配器
     */
    public class TabAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private List<String> lists;

        public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
            lists = new ArrayList<>();
            lists.add("我的");
            lists.add("音乐");
            lists.add("动态");
            lists.add("直播");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return lists.get(position);
        }
    }

    /**
     * 建一个广播接受者的内部类
     */
    public class MainBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mMyBinder != null) {
                String title = intent.getStringExtra("title");
                String singer = intent.getStringExtra("singer");
                mSingerTv.setText(singer);
                mNameTv.setText(title);
                int a = intent.getIntExtra("music", 6);
                switch (a) {
                    case 1:
                        finish();
                        break;
                    case 2:
                        mMyBinder.nextMusic();
                        Toast.makeText(MainActivity.this, "下一首", Toast.LENGTH_SHORT).show();
                        pauseIv.setVisibility(View.INVISIBLE);
                        playIv.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mMyBinder.pastMusic();
                        Toast.makeText(MainActivity.this, "上一首", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        mMyBinder.playMusic();
                        mPlayBtnN.setVisibility(View.VISIBLE);
                        mPauseBtnN.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        mMyBinder.pauseMusic();
                        mPlayBtnN.setVisibility(View.INVISIBLE);
                        mPauseBtnN.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(mMainBR);
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
