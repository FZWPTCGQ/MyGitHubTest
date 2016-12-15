package com.aqiang.dllo.mybaidumusic.fragment.reusefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReuseFragmentDetail extends BaseFragment {

    private ReuseAdapter mReuseAdapter;
    private ReuseFragmentBean mReuseFragmentBean;
    private RecyclerView mRv;

    public static ReuseFragmentDetail newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("reuse",url);
        ReuseFragmentDetail fragment = new ReuseFragmentDetail();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse_fragment_detail;
    }

    @Override
    protected void initView(View view) {
        mRv = (RecyclerView)view.findViewById(R.id.reuse_fragment_detail_rv);

    }

    @Override
    protected void initData() {
         Bundle bundle = getArguments();
        String urlD = bundle.getString("reuse");
        NetHelper.MyRequest(urlD, ReuseFragmentBean.class, new NetListener<ReuseFragmentBean>() {
            @Override
            public void successListener(ReuseFragmentBean response) {
                mReuseFragmentBean = response;
                mReuseAdapter = new ReuseAdapter();
                mReuseAdapter.setReuseFragmentBean(mReuseFragmentBean);
                mRv.setAdapter(mReuseAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
