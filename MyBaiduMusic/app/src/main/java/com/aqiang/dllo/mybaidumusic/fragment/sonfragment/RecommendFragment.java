package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.recommendSonDetailAdapter.RecommendFragmentRecycleViewAdapter;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.RecommendFragmentRecycleViewBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * 音乐界面的推荐界面
 */
public class RecommendFragment extends SonBaseFragment {




//    private String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.plaza.index&cuid=FD91A86A9F44B1249C42381F417D4253";
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBean;
    private RecommendFragmentRecycleViewAdapter recommendFragmentRecycleViewAdapter;
    private RecyclerView recyclerView;

    @Override
    int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    void initView(View view) {

        recyclerView = (RecyclerView) getView().findViewById(R.id.recommendFragment_rv);

    }

    @Override
    void initData() {
        /**
         * 请求网络数据
         */
     parseData();


    }




    private void parseData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Tools.RFList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                 recommendFragmentRecycleViewBean = gson.fromJson(response,RecommendFragmentRecycleViewBean.class);
                 recommendFragmentRecycleViewAdapter = new RecommendFragmentRecycleViewAdapter(getContext());
                recommendFragmentRecycleViewAdapter.setRecommendFragmentRecycleViewBean(recommendFragmentRecycleViewBean);
                recyclerView.setAdapter(recommendFragmentRecycleViewAdapter);

                LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
