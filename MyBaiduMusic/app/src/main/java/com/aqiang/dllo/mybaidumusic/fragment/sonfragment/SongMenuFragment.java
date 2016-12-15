package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.songMenuDetail.HotAdapter;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.songMenuDetail.HotBean;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.SongMenuDetailBean;
import com.aqiang.dllo.mybaidumusic.tool.RVListener.OnItemClickListener;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * 音乐界面的歌单界面
 *
 */
public class SongMenuFragment extends SonBaseFragment implements View.OnClickListener {

    private ImageView mImageView;
    private TextView mTextViewLast;
    private TextView mTextViewHot;
    private HotBean mHotBean;
    private HotAdapter mHotAdapter;
    private RecyclerView mRecyclerView;
    private boolean isUp = false;
    private boolean isDown = false;
    private Animation mAnimation;
    private Animation mAnimation1;
    private LinearLayout mLinearLayout;

    @Override
    int setLayout() {
        return R.layout.fragment_song_menu;
    }
//   StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager()
    @Override
    void initView(View view) {
        mImageView = (ImageView) view.findViewById(R.id.songfragmentmenu_detail_quanbu_iv);
        mTextViewLast = (TextView) view.findViewById(R.id.songfragmentmenu_zuixin_tv);
        mTextViewHot = (TextView) view.findViewById(R.id.songfragmentmenu_zuire_tv);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.song_menu_fragment_rv);
        mLinearLayout = (LinearLayout)view.findViewById(R.id.fragment_song_menu_ll);
        mHotAdapter = new HotAdapter(getContext());
        mTextViewLast.setTextColor(Color.BLACK);
        mTextViewHot.setTextColor(getResources().getColor(R.color.blue));

    }

    @Override
    void initData() {
        parseMethod(Tools.songMenuHot);
        animX();
        mImageView.setOnClickListener(this);
        mTextViewLast.setOnClickListener(this);
        mTextViewHot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.songfragmentmenu_detail_quanbu_iv:
                break;
            case R.id.songfragmentmenu_zuixin_tv:
                mTextViewLast.setTextColor(getResources().getColor(R.color.blue));
                mTextViewHot.setTextColor(Color.BLACK);
                parseMethod(Tools.songMenuNew);
                animX();
                break;
            case R.id.songfragmentmenu_zuire_tv:
//                replaceMethod(new HotFragment());
                mTextViewLast.setTextColor(Color.BLACK);
                mTextViewHot.setTextColor(getResources().getColor(R.color.blue));
                 parseMethod(Tools.songMenuHot);
                animX();
                break;
        }
    }

    private void parseMethod(String path) {
        /**
         * volley的二次封装
         */
        NetHelper.MyRequest(path, HotBean.class, new NetListener<HotBean>() {
            @Override
            public void successListener(HotBean response) {
                mHotBean = response;
                mHotAdapter.setHotBean(mHotBean);
                mRecyclerView.setAdapter(mHotAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                mHotAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClickListener(int position, String songIdList) {
                        Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tools.ACTION_REPLACE);
                        intent.putExtra("type",1);
                        intent.putExtra("songIdList",songIdList);
                        context.sendBroadcast(intent);
                    }
                    @Override
                    public void method(int position, SongMenuDetailBean songMenuDetailBean) {

                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void animX() {
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


}
