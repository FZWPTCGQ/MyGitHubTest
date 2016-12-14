package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.musicListFragmentAdapter.MusicListFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.MusicListFragmentBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * 音乐界面的榜单界面
 */
public class MusicListFragment extends SonBaseFragment {


    private ListView mListView;
    private MusicListFragmentAdapter mMusicListFragmentAdapter;
    private MusicListFragmentBean mMusicListFragmentBean;

//    private String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";
    @Override
    int setLayout() {
        return R.layout.fragment_music_list;
    }

    @Override
    void initView(View view) {
        mListView = (ListView)view.findViewById(R.id.musicListFragment_lv);
        mMusicListFragmentAdapter = new MusicListFragmentAdapter(context);
    }

    @Override
    void initData() {
        /**
         * 解析网络数据
         */
        parseMethod();
    }

    private void parseMethod() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Tools.musicList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mMusicListFragmentBean = gson.fromJson(response,MusicListFragmentBean.class);
                mMusicListFragmentAdapter.setMusicListFragmentBean(mMusicListFragmentBean);
                mListView.setAdapter(mMusicListFragmentAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
