package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.tool.grideimageloader.GrideImageLoader;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.kFragmentSonDetailAdapter.KFragmentAdapter;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.KFragmentBean;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.KFragmentBeanLunBo;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * 音乐界面的K歌界面
 */
public class KFragment extends SonBaseFragment {


    private ListView mListView;
    private KFragmentBean mKFragmentBean;
    private KFragmentAdapter mKFragmentAdapter;

    private Banner mBanner;

    @Override
    int setLayout() {
        return R.layout.fragment_k;
    }

    @Override
    void initView(View view) {
      addView();
    }

    private void addView() {

        mListView = (ListView)getView().findViewById(R.id.k_fragment_header_view_lv);
        mKFragmentAdapter = new KFragmentAdapter(context);
    }

    @Override
    void initData() {
        /**
         * 网络数据
         */
     parseInternet();
        /**
         * 轮播图
         */
        parseUrl();
    }

    private void parseUrl() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Tools.KTurn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                KFragmentBeanLunBo kFragmentBeanLunBo = gson.fromJson(response,KFragmentBeanLunBo.class);
                ArrayList<String> image = new ArrayList<>();
                for (int i = 0; i < kFragmentBeanLunBo.getResult().size(); i++) {
                    image.add(kFragmentBeanLunBo.getResult().get(i).getPicture_iphone6());
                }

                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                // 设置图片加载器
                mBanner.setImageLoader(new GrideImageLoader());
                // 设置图片集合
                mBanner.setImages(image);
                // 设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                mBanner.setBannerAnimation(Transformer.DepthPage);
                // 设置自动轮播 默认为true
               mBanner.isAutoPlay(true);
                // 设置轮播时间
                mBanner.setDelayTime(2000);
                // 设置指示器位置 (当banner模式中有指示器时)
               mBanner.setIndicatorGravity(BannerConfig.CENTER);
                // banner设置方法全部调用完毕时最后调用
                mBanner.start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void parseInternet() {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Tools.KList, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                mKFragmentBean = gson.fromJson(response,KFragmentBean.class);
//                mKFragmentAdapter.setKFragmentBean(mKFragmentBean);
//                mListView.setAdapter(mKFragmentAdapter);
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
      NetHelper.MyRequest(Tools.KList, KFragmentBean.class, new NetListener<KFragmentBean>() {
          @Override
          public void successListener(KFragmentBean response) {
              mKFragmentBean = response;
              mKFragmentAdapter.setKFragmentBean(mKFragmentBean);
              mListView.setAdapter(mKFragmentAdapter);
          }

          @Override
          public void errorListener(VolleyError error) {

          }
      });
        View view = LayoutInflater.from(context).inflate(R.layout.k_fragment_header_view_item,mListView,false);
        mBanner = (Banner)view.findViewById(R.id.banner);
        mListView.addHeaderView(view);
    }

}
