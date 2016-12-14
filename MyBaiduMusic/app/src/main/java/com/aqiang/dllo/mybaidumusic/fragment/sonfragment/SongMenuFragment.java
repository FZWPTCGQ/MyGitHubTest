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
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.songMenuDetail.HotAdapter;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.songMenuDetail.HotBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
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

    @Override
    void initView(View view) {
        mImageView = (ImageView) view.findViewById(R.id.songfragmentmenu_detail_quanbu_iv);
        mTextViewLast = (TextView) view.findViewById(R.id.songfragmentmenu_zuixin_tv);
        mTextViewHot = (TextView) view.findViewById(R.id.songfragmentmenu_zuire_tv);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.song_menu_fragment_rv);
        mLinearLayout = (LinearLayout)view.findViewById(R.id.fragment_song_menu_ll);
        mHotAdapter = new HotAdapter(getContext());
        mTextViewLast.setTextColor(Color.BLACK);
        mTextViewHot.setTextColor(Color.BLUE);

    }

    @Override
    void initData() {
        parseMethod(Tools.songMenuHot);
        animX();
//        replaceMethod(new HotFragment());
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
//                replaceMethod(new LastFragment());
                mTextViewLast.setTextColor(Color.BLUE);
                mTextViewHot.setTextColor(Color.BLACK);
                parseMethod(Tools.songMenuNew);
                animX();
                break;
            case R.id.songfragmentmenu_zuire_tv:
//                replaceMethod(new HotFragment());
                mTextViewLast.setTextColor(Color.BLACK);
                mTextViewHot.setTextColor(Color.BLUE);
                 parseMethod(Tools.songMenuHot);
                animX();
                break;
        }
    }

    private void parseMethod(String path) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mHotBean = gson.fromJson(response,HotBean.class);
                mHotAdapter.setHotBean(mHotBean);
                mRecyclerView.setAdapter(mHotAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

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

//    private void replaceMethod(Fragment fragment) {
//        FragmentManager manager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.replace(R.id.songfragmentmenu_fl,fragment);
//        fragmentTransaction.commit();
//    }
}
