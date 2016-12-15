package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.videoFragmentDetailAdapter.VideoBean;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.videoFragmentDetailAdapter.VideoDetailAdapter;

import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;

import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * 音乐界面的视频界面
 */
public class VideoFragment extends SonBaseFragment implements View.OnClickListener {


    private ImageView mImageView;
    private TextView mHotTv;
    private TextView mNewestTv;
    private LinearLayout mLinearLayout;
    private RecyclerView mRecyclerView;
    private VideoBean mVideoBean;
    private VideoDetailAdapter mVideoDetailAdapter;
    private boolean isUp = false;
    private boolean isDown = false;
    private Animation mAnimation;
    private Animation mAnimation1;

    @Override
    int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    void initView(View view) {
        mLinearLayout = (LinearLayout)view.findViewById(R.id.video_fragment_ll);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.video_fragment_rv);
        mImageView = (ImageView)view.findViewById(R.id.video_fragment_pull_down);
        mHotTv = (TextView)view.findViewById(R.id.video_fragment_zuire_tv);
        mNewestTv = (TextView)view.findViewById(R.id.video_fragment_zuixin_tv);
        mVideoDetailAdapter = new VideoDetailAdapter(context);


    }

    @Override
    void initData() {
        /**
         * 添加点击事件
         */
        addListener();
        /**
         * 替换fragment方法
         */

    }

    private void addListener() {
        mHotTv.setOnClickListener(this);
        mNewestTv.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        mHotTv.setTextColor(Color.BLACK);
        mNewestTv.setTextColor(Color.BLUE);
        parseMethod(Tools.viewNew);
        animMethod();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_fragment_pull_down:

                break;
            case R.id.video_fragment_zuire_tv:

                mHotTv.setTextColor(Color.BLUE);
                mNewestTv.setTextColor(Color.BLACK);
                parseMethod(Tools.videoHot);
                animMethod();
                break;
            case R.id.video_fragment_zuixin_tv:

                mHotTv.setTextColor(Color.BLACK);
                mNewestTv.setTextColor(Color.BLUE);
                parseMethod(Tools.viewNew);
                animMethod();
                break;
        }
    }

    private void animMethod() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    if (!isUp) {
                        mAnimation = null;
                        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.title_in);
                        mAnimation.setFillAfter(true);
                        mLinearLayout.setAnimation(mAnimation);
                        mLinearLayout.setVisibility(View.GONE);
                        isUp = true;
                        isDown = false;
                    }
                }else {
                    if (!isDown){
                        mAnimation1 = null;
                        mAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.title_out);
                        mAnimation1.setFillAfter(true);
                        mLinearLayout.setAnimation(mAnimation1);
                        mLinearLayout.setVisibility(View.VISIBLE);
                        isDown = true;
                        isUp = false;
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void parseMethod(String path) {
        /**
         * 老方法
         */
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                mVideoBean = gson.fromJson(response,VideoBean.class);
//                mVideoDetailAdapter.setVideoBean(mVideoBean);
//                mRecyclerView.setAdapter(mVideoDetailAdapter);
//                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//                mRecyclerView.setLayoutManager(manager);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
        /**
         * volley二次封装
         */
        NetHelper.MyRequest(path, VideoBean.class, new NetListener<VideoBean>() {
            @Override
            public void successListener(VideoBean response) {
                mVideoBean = response;
                mVideoDetailAdapter.setVideoBean(mVideoBean);
                mRecyclerView.setAdapter(mVideoDetailAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
