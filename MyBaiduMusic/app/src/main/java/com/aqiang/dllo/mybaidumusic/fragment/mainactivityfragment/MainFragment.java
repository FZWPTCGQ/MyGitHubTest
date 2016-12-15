package com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 实现点击下面的音乐播放啦弹出fragment
 */
public class MainFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {


    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mList;
    private ImageView mImageView;
    private List<MyPoint> mMyPoints;
    private LinearLayout mLinearLayout;
    private Context mContext;
    private MFBR mMfbr;


    /**
     * 播放组件
     */
    private ImageView mNextSong1;
    private ImageView mPastSong;
    private ImageView mPauseSong;
    private ImageView mPlaySong;
    private ImageView mOrderPlay;
    private ImageView mSinglePlay;
    private ImageView mCirPlay;
    private ImageView mRandomPlay;





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 初始化组件
         */
        initViews(view);
        /**
         * 注册广播
         */
        registerBR();
    }
    //注册广播
    private void registerBR() {
        mMfbr = new MFBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mp3");
        mContext.registerReceiver(mMfbr,intentFilter);
    }

    private void initViews(View view) {
        /**
         * 拦截触摸事件,防止泄露下去
         */
        view.setOnTouchListener(this);
        mViewPager = (ViewPager)view.findViewById(R.id.main_fragment_vp);
        mImageView = (ImageView)view.findViewById(R.id.song_page_return);
        mLinearLayout = (LinearLayout)view.findViewById(R.id.main_fragment_ll);
        mList = new ArrayList<>();
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),mList);
        mNextSong1 = (ImageView)view.findViewById(R.id.main_fragment_button_next_iv);
        mPastSong = (ImageView)view.findViewById(R.id.main_fragment_button_pre_iv);
        mPauseSong = (ImageView)view.findViewById(R.id.main_fragment_button_pause_iv);
        mPlaySong = (ImageView)view.findViewById(R.id.main_fragment_button_play_iv);
   //     mOrderPlay = (ImageView)view.findViewById(R.id.main_fragment_shunxu_one_play_iv);
//        mSinglePlay = (ImageView)view.findViewById(R.id.main_fragment_danqu_play_iv);
//        mCirPlay = (ImageView)view.findViewById(R.id.main_fragment_shunxu_play_iv);
//        mRandomPlay = (ImageView)view.findViewById(R.id.main_fragment_suiji_play_iv);
    }

    private void initPoints() {
        mMyPoints = new ArrayList<>();
        for (int i = 0; i < mViewPagerAdapter.getCount(); i++) {

            MyPoint myPoint = new MyPoint(getContext());
            if(i == 1){
             myPoint.setSelected(true);
            }
            mMyPoints.add(myPoint);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
            mLinearLayout.addView(myPoint,params);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList.add(new LeftFragment());
        mList.add(new RightFragment());
        mList.add(new MiddleFragment());
        mViewPager.setAdapter(mViewPagerAdapter);
        /**
         * 退出播放页的图片按钮
         */
        initPoints();
        mImageView.setOnClickListener(this);
        /**
         * 首次进入ViewPager显示第几页
         */
        mViewPager.setCurrentItem(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               int currentPage = position % mViewPagerAdapter.getCount();
                for (MyPoint point : mMyPoints){
                    point.setSelected(false);
                }
                mMyPoints.get(currentPage).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /**
         * MainFragment组件添加点击事件
         */
        mainFragmentAddListener();
    }
    private void mainFragmentAddListener() {
        mNextSong1.setOnClickListener(this);
        mPastSong.setOnClickListener(this);
        mPauseSong.setOnClickListener(this);
        mPlaySong.setOnClickListener(this);
        mOrderPlay.setOnClickListener(this);
        mSinglePlay.setOnClickListener(this);
        mCirPlay.setOnClickListener(this);
        mRandomPlay.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.song_page_return:
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_in,R.anim.fragment_out);
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("fra_from_down_music_main"));
                fragmentTransaction.commit();

                break;
            /**
             * MainFragment组件
             */
            case R.id.main_fragment_button_next_iv:
                Toast.makeText(mContext, "下一曲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_fragment_button_pre_iv:
                Toast.makeText(mContext, "上一曲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_fragment_button_pause_iv:
                mPauseSong.setVisibility(View.INVISIBLE);
                mPlaySong.setVisibility(View.VISIBLE);
                break;
            case R.id.main_fragment_button_play_iv:
                mPlaySong.setVisibility(View.INVISIBLE);
                mPauseSong.setVisibility(View.VISIBLE);
                break;
//            case R.id.main_fragment_shunxu_one_play_iv:
//                mSinglePlay.setVisibility(View.VISIBLE);
//                mOrderPlay.setVisibility(View.INVISIBLE);
//                mCirPlay.setVisibility(View.INVISIBLE);
//                mRandomPlay.setVisibility(View.INVISIBLE);
//                break;
//            case R.id.main_fragment_danqu_play_iv:
//                mCirPlay.setVisibility(View.VISIBLE);
//                mOrderPlay.setVisibility(View.INVISIBLE);
//                mSinglePlay.setVisibility(View.INVISIBLE);
//                mRandomPlay.setVisibility(View.INVISIBLE);
//                break;
//            case R.id.main_fragment_shunxu_play_iv:
//                mOrderPlay.setVisibility(View.INVISIBLE);
//                mRandomPlay.setVisibility(View.VISIBLE);
//                mSinglePlay.setVisibility(View.INVISIBLE);
//                mCirPlay.setVisibility(View.INVISIBLE);
//                break;
//            case R.id.main_fragment_suiji_play_iv:
////                mRandomPlay.setVisibility(View.INVISIBLE);
////                mOrderPlay.setVisibility(View.VISIBLE);
////                mSinglePlay.setVisibility(View.INVISIBLE);
////                mCirPlay.setVisibility(View.INVISIBLE);
//                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        /**
         * onTouch事件 将上层的触摸事件拦截
         */
        return true;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public class MFBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
              int a = intent.getIntExtra("music",100);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext.unregisterReceiver(mMfbr);
    }
}
