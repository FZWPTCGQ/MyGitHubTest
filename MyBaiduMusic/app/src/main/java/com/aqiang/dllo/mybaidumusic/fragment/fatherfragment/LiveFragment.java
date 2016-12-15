package com.aqiang.dllo.mybaidumusic.fragment.fatherfragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.fatheradapter.LiveFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.LiveFragmentBean;
import com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment.SongMenuDetailFragment;
import com.aqiang.dllo.mybaidumusic.fragment.reusefragment.ReuseFragment;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends BaseFragment implements View.OnClickListener {



     private LiveFragmentAdapter mLiveFragmentAdapter;
    private LiveFragmentBean mLiveFragmentBean;
    private RecyclerView mRecyclerView;
    private TextView mTextViewMore;


    @Override
    protected int setLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.fragmetn_live_rv);
        mLiveFragmentAdapter = new LiveFragmentAdapter(getContext());


    }

    private void setHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.live_fragment_header_view,recyclerView,false);
        mTextViewMore = (TextView)header.findViewById(R.id.video_fragment_zuixin_tv);
        mLiveFragmentAdapter.setHeaderView(header);
        mTextViewMore.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        /**
         * volley二次分装方法
         */
        NetHelper.MyRequest(Tools.pathLive, LiveFragmentBean.class, new NetListener<LiveFragmentBean>() {
            @Override
            public void successListener(LiveFragmentBean response) {
                mLiveFragmentBean = response;
                mLiveFragmentAdapter.setLiveFragmentBean(mLiveFragmentBean);
                mRecyclerView.setAdapter(mLiveFragmentAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                setHeader(mRecyclerView);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_fragment_zuixin_tv:
                Intent intent = new Intent(Tools.ACTION_REPLACE);
                intent.putExtra("type",4);
                
                MyApp.getmContext().sendBroadcast(intent);
                break;
        }
    }
}
