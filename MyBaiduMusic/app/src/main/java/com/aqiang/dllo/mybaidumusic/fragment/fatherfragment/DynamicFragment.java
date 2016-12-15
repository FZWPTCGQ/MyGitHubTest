package com.aqiang.dllo.mybaidumusic.fragment.fatherfragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.fatheradapter.dynamicFragmentAdapter.DynamicFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.DynamicFragmentBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends BaseFragment {

/**
 *
 */

    private RecyclerView mRecyclerView;
//   private DynamicFragmentAdapter mDynamicFragmentAdapter;
    private DynamicFragmentBean mDynamicFragmentBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView(View view) {
        /**
         * 添加数据
         */
        addViews();
    }


    @Override
    protected void initData() {
        /**
         * 添加数据
         */
        addDatas();
    }

    private void addDatas() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Tools.pathD, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                 Gson gson = new Gson();
//                mDynamicFragmentBean = gson.fromJson(response,DynamicFragmentBean.class);
//                mDynamicFragmentAdapter.setDynamicFragmentBean(mDynamicFragmentBean);
//                mRecyclerView.setAdapter(mDynamicFragmentAdapter);
//                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
//                mRecyclerView.setLayoutManager(manager);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("DynamicFragment", "错误");
//            }
//        });
//        requestQueue.add(stringRequest);
        /**
         * 二次封装
         */
//        NetHelper.MyRequest(Tools.pathD, DynamicFragmentBean.class, new NetListener<DynamicFragmentBean>() {
//            @Override
//            public void successListener(DynamicFragmentBean response) {
//             mDynamicFragmentBean = response;
//                mDynamicFragmentAdapter.setDynamicFragmentBean(mDynamicFragmentBean);
//                mRecyclerView.setAdapter(mDynamicFragmentAdapter);
//                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
//                mRecyclerView.setLayoutManager(manager);
//            }
//
//            @Override
//            public void errorListener(VolleyError error) {
//
//            }
//        });

    }

    private void addViews() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.dynamic_fragment_rv);
       // mDynamicFragmentAdapter = new DynamicFragmentAdapter(getContext());
    }

}
