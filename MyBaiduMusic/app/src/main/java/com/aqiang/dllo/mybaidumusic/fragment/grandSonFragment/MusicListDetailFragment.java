package com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.grandSonAdapter.MLDFAdapter;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.MenuListDetailBean;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBPlayTools;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.PlayBean;
import com.aqiang.dllo.mybaidumusic.tool.service.Bean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 榜单详情页
 */
public class MusicListDetailFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 榜单详情页的bean
     * @return
     */
    private MenuListDetailBean mMenuListDetailBean;
    /**
     * 榜单详情页适配器
     * @return
     */
    private MLDFAdapter mMLDFAdapter;

    /**
     * 用来播放歌曲
     * @return
     */
    private ArrayList<String> songIdList;

    /**
     * 详情页内容
     */
    //标头的图片
    private ImageView mImageViewS;
    //歌曲总数
    private TextView mCountTv;
    //下载
    private ImageView mImageViewDownload;
    //分享
    private ImageView mImageViewShare;
    //lv
    private ListView mListViewDetail;
    //左上角返回键
    private ImageView mImageViewBack;
    //背景大图
    private ImageView mImageViewBig;
    /**
     * 歌曲的地址
     * @return
     */
    private String url;

    /**
     * 网址类型(歌曲列表类型),songId;
     * @return
     */
    public static String urlType;

    /**
     *
     *  可改变字符串
     */

   StringBuffer mStringBuffer;

    /**
     * 存入数据库
     * @param urlType
     * @return
     */
    private List<PlayBean> mPlayBeen;
    private EventBus mEventBus;


    public static MusicListDetailFragment newInstance(String urlType) {

        Bundle args = new Bundle();
        args.putString("type",urlType);
        MusicListDetailFragment fragment = new MusicListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_list_detail;
    }

    @Override
    protected void initView(View view) {
        mEventBus = EventBus.getDefault();
        mPlayBeen = new ArrayList<>();
        mStringBuffer = new StringBuffer(Tools.MLDUrl);
        mImageViewS = (ImageView)getActivity().findViewById(R.id.fragment_menu_list_detail_top_bg);
        mImageViewBig = (ImageView)getActivity().findViewById(R.id.fragment_menu_list_detail_top_bg);
        mCountTv = (TextView) getActivity().findViewById(R.id.fragment_menu_list_detail_song_count);

        mImageViewDownload = (ImageView) getActivity().findViewById(R.id.fragment_menu_list_detail_download);
        mImageViewShare = (ImageView) getActivity().findViewById(R.id.fragment_menu_list_detail_share);
        mListViewDetail = (ListView) getActivity().findViewById(R.id.fragment_menu_list_detail_lv);
        /**
         * 将状态栏取消
         */
        mListViewDetail.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mImageViewBack = (ImageView) getActivity().findViewById(R.id.fragment_menu_list_detail_back_iv);
        songIdList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        /**
         * 发送广播
         */
        sendMethod();
        addListener();
    }

    private void addListener() {
        mImageViewBack.setOnClickListener(this);
    }

    /**
     * 哈哈哈
     */
    private void sendMethod() {
        Bundle bundle = getArguments();
        urlType = bundle.getString("type");
        if (urlType.isEmpty()){
            Toast.makeText(getContext(), "没有取到值", Toast.LENGTH_SHORT).show();
        }else {
            int start = mStringBuffer.indexOf("参数", 0);
            mStringBuffer.replace(start, start + 2, urlType);
            url = mStringBuffer.toString();
        }
        /**
         * 获取音乐的播放路径
         */
        getNetPath(url);
        mListViewDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 发一个广播,参数是map3
                 */
                Intent intent = new Intent(Tools.ACTION_MP3);
                /**
                 * 对不同的广播进行分类
                 */
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                intent.putExtra("music", 6);
                intent.putStringArrayListExtra("songList", songIdList);

                intent.putExtra("title",mMenuListDetailBean.getSong_list().get(position).getTitle());
                intent.putExtra("singer",mMenuListDetailBean.getSong_list().get(position).getAuthor());
                intent.putExtra("position", position);
                intent.putExtra("pic",mMenuListDetailBean.getSong_list().get(position).getPic_small());
                getContext().sendBroadcast(intent);

                mEventBus.post(new Bean(position));

                /**
                 * 先判断数据库中是否有数据
                 */
                if (DBPlayTools.getInstance().queryAll().size() > 0){
                    /**
                     * 清空数据
                     */
                    DBPlayTools.getInstance().deleteAll();
                }

                for (int i = 0; i < mMenuListDetailBean.getSong_list().size(); i++) {

                    mPlayBeen.add(new PlayBean(null,mMenuListDetailBean.getSong_list().get(i).getTitle(),mMenuListDetailBean.getSong_list().get(i).getAuthor(), mMenuListDetailBean.getSong_list().get(i).getSong_id(),Long.valueOf(mMenuListDetailBean.getSong_list().get(i).getFile_duration())));

                }

                DBPlayTools.getInstance().insertList(mPlayBeen);
                Log.d("后就开始放不放假", DBPlayTools.getInstance().queryAll().get(position).getUrl());
                Log.d("fs87f9", "mPlayBeen.size():" + mPlayBeen.size());
            }
        });
    }

    private void getNetPath(String urlType) {
        NetHelper.MyRequest(urlType, MenuListDetailBean.class, new NetListener<MenuListDetailBean>() {
            @Override
            public void successListener(MenuListDetailBean response) {
                mMenuListDetailBean = response;
                Picasso.with(context).load(mMenuListDetailBean.getBillboard().getPic_s210()).into(mImageViewS);
                mMLDFAdapter = new MLDFAdapter(context);
                mMLDFAdapter.setSongListBeen(mMenuListDetailBean.getSong_list());
                mListViewDetail.setAdapter(mMLDFAdapter);
                /**
                 * 将int型装变为String类型
                 */
                mCountTv.setText("共有" + String.valueOf(mMenuListDetailBean.getSong_list().size()) + "首歌");
                for (int i = 0; i < mMenuListDetailBean.getSong_list().size(); i++) {
                    Log.d("ooo", mMenuListDetailBean.getSong_list().get(i).getSong_id());
                    songIdList.add(mMenuListDetailBean.getSong_list().get(i).getSong_id());
//                    mPlayBeen.add(new PlayBean(null,mMenuListDetailBean.getSong_list().get(i).getTitle(),mMenuListDetailBean.getSong_list().get(i).getAuthor()));
//
                }


            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_menu_list_detail_back_iv:
                /**
                 * 弹回占
                 */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                fragmentTransaction.setCustomAnimations(R.anim.in,R.anim.out);

                fragmentTransaction.remove(fragmentManager.findFragmentByTag("abc"));
                fragmentTransaction.commit();
                break;
        }
    }
}
