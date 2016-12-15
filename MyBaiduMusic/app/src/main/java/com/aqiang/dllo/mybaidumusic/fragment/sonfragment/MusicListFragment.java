package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.musicListFragmentAdapter.MusicListFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.MusicListFragmentBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;

/**
 * A simple {@link Fragment} subclass.
 * 音乐界面的榜单界面
 */
public class MusicListFragment extends SonBaseFragment {


    private ListView mListView;
    private MusicListFragmentAdapter mMusicListFragmentAdapter;
    private MusicListFragmentBean mMusicListFragmentBean;


    /**
     * 通过newInstance方法传传递数据
     */
    public static MusicListFragment newInstance(String urlType) {

        Bundle args = new Bundle();
        args.putString("type", urlType);
        MusicListFragment fragment = new MusicListFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    int setLayout() {
        return R.layout.fragment_music_list;
    }

    @Override
    void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.musicListFragment_lv);
        mMusicListFragmentAdapter = new MusicListFragmentAdapter(context);
    }


    @Override
    void initData() {
        /**
         * 解析网络数据
         */
        parseMethod();
        /**
         * listView点击事件
         * 通过发送广播,通知activity的占位布局更换
         */
        addListener();

    }

    private void addListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tools.ACTION_REPLACE);
                intent.putExtra("type", 0);
                intent.putExtra("urlType", String.valueOf(mMusicListFragmentBean.getContent().get(position).getType()));
                Log.d("cao", mMusicListFragmentBean.getContent().get(position).getType() + "");
                context.sendBroadcast(intent);

            }
        });
    }

    private void parseMethod() {

        /**
         * volley二次封装
         */
        NetHelper.MyRequest(Tools.musicList, MusicListFragmentBean.class, new NetListener<MusicListFragmentBean>() {
            @Override
            public void successListener(MusicListFragmentBean response) {
                mMusicListFragmentBean = response;
                mMusicListFragmentAdapter.setMusicListFragmentBean(mMusicListFragmentBean);
                mListView.setAdapter(mMusicListFragmentAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
