package com.aqiang.dllo.mybaidumusic.fragment.fatherfragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.fatheradapter.LiveFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.LiveFragmentBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends BaseFragment {



     private LiveFragmentAdapter mLiveFragmentAdapter;
    private LiveFragmentBean mLiveFragmentBean;
    private RecyclerView mRecyclerView;


    @Override
    int setLayout() {
        return R.layout.fragment_live;
    }

    @Override
    void initView(View view) {
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.fragmetn_live_rv);
        mLiveFragmentAdapter = new LiveFragmentAdapter(getContext());

    }

    private void setHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.live_fragment_header_view,recyclerView,false);
        mLiveFragmentAdapter.setHeaderView(header);
    }

    @Override
    void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Tools.pathLive, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mLiveFragmentBean = gson.fromJson(response,LiveFragmentBean.class);
                mLiveFragmentAdapter.setLiveFragmentBean(mLiveFragmentBean);
                mRecyclerView.setAdapter(mLiveFragmentAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                setHeader(mRecyclerView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
