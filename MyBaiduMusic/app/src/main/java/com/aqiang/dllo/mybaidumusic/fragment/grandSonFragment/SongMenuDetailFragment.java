package com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.activity.MainActivity;
import com.aqiang.dllo.mybaidumusic.adapter.grandSonAdapter.MLDFAdapter;
import com.aqiang.dllo.mybaidumusic.adapter.grandSonAdapter.SMDAdapter;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.MenuListDetailBean;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.SongMenuDetailBean;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.RVListener.OnItemClickListener;
import com.aqiang.dllo.mybaidumusic.tool.blur.BlurTranformation;

import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBPlayTools;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.PlayBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 歌单详情页
 */
public class SongMenuDetailFragment extends BaseFragment implements View.OnClickListener {
    /**
     * 榜单详情页的bean
     * @return
     */
    private SongMenuDetailFragment mSongMenuDetailFragment;
    /**
     * 榜单详情页适配器
     * @return
     */
    private SMDAdapter mSMDAdapter;

    /**
     * 用来播放歌曲
     * @return
     */

    /**
     * 详情页内容
     */
    //标头的图片
    private ImageView mImageViewS;
    //标头文字
    private TextView mTextViewTitle;
    //图片下边的文字
    private TextView mTextViewTag;
    //歌曲总数
    private TextView mCountTv;
    //歌曲描述
    private TextView mTextView;
    //网页id
    private String songId;
    //下载
    private ImageView mImageViewDownload;
    //分享
    private ImageView mImageViewShare;
    //lv
    private RecyclerView mRecyclerView;
    //左上角返回键
    private ImageView mImageViewBack;
    //背景图
    private ImageView mImageViewBig;
    /**
     * 歌曲的地址
     * @return
     */
    private String url;
    /**
     * 拼接的新网址
     */
    private String mNewUrl;
    /**
     * 详情页实体类
     */
    private SongMenuDetailBean mSongMenuDetailBean;
    /**
     * 存放格局Id的集合;
     * @return
     */
    private ArrayList<String> songIdList;
    private String urlType;

    /**
     *
     *  可改变字符串
     */

    StringBuffer mStringBuffer;
    private ImageView mImageViewStarOn;
    private ImageView mImageViewStarOff;
    private List<PlayBean> mList;


    public static SongMenuDetailFragment newInstance(String songIdList) {
        Bundle args = new Bundle();
        args.putString("songIdList",songIdList);
        SongMenuDetailFragment fragment = new SongMenuDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_song_menu_detail;
    }

    @Override
    protected void initView(View view) {
     addView();
    }

    private void addView() {
        mSMDAdapter = new SMDAdapter(context);
        songIdList = new ArrayList<>();
        mList = new ArrayList<>();
        mImageViewS = (ImageView)getView().findViewById(R.id.fragment_song_menu_detail_pic_three);
        mStringBuffer = new StringBuffer(Tools.SONG_MUSIC_LIST_DETAIL_URL);
        mImageViewBack = (ImageView)getView().findViewById(R.id.fragment_song_menu_detail_back_iv);
        mTextViewTitle = (TextView)getView().findViewById(R.id.fragment_song_menu_detail_title);
        mTextViewTag = (TextView)getView().findViewById(R.id.fragment_song_menu_detail_tag_tv);
        mCountTv = (TextView)getView().findViewById(R.id.fragment_song_menu_detail_song_count);
        mTextView = (TextView)getView().findViewById(R.id.fragment_song_menu_detail_desc);
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.fragment_song_menu_detail_rv);
        mImageViewBig = (ImageView)getView().findViewById(R.id.song_menu_big_tu);
        mRecyclerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mImageViewStarOn = (ImageView)getView().findViewById(R.id.fragment_song_menu_detail_star_on);
        mImageViewStarOff = (ImageView)getView().findViewById(R.id.fragment_song_menu_detail_star_off);
        addListener();
    }

    private void addListener() {
        mImageViewBack.setOnClickListener(this);
        mImageViewStarOn.setOnClickListener(this);
        mImageViewStarOff.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        songId = bundle.getString("songIdList");
        if (songId.isEmpty()){
            Log.d("SongMenuDetailFragment", "为取到值");
        }else{
            int start = mStringBuffer.indexOf("参数",0);
            mStringBuffer.replace(start,start + 2, songId);
            mNewUrl = mStringBuffer.toString();
        }
        /**
         * 解析数据额
         */
     parseData(mNewUrl);

        /**
         * 点击事件
         */
        mSMDAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, String songIdList) {


            }

            @Override
            public void method(int position, SongMenuDetailBean songMenuDetailBean) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();

                Log.d("333", "songIdList.size():" + songIdList.get(position));
                Log.d("666", songMenuDetailBean.getContent().get(position).getSong_id() + "");
                Intent intent = new Intent(Tools.ACTION_MP3);
                intent.putExtra("music", 7);
                intent.putStringArrayListExtra("SMDSongIdList", songIdList);

                intent.putExtra("title", mSongMenuDetailBean.getContent().get(position).getTitle());
                intent.putExtra("singer", mSongMenuDetailBean.getContent().get(position).getAuthor());
                intent.putExtra("position", position);
                context.sendBroadcast(intent);
                Log.d("风比较卡不舒服", mSongMenuDetailBean.getContent().size() + "");

                if (DBPlayTools.getInstance().queryAll().size() > 0){
                    DBPlayTools.getInstance().deleteAll();
                }
                for (int i = 0; i < mSongMenuDetailBean.getContent().size(); i++) {
                    mList.add(new PlayBean(null,mSongMenuDetailBean.getContent().get(i).getTitle(),mSongMenuDetailBean.getContent().get(i).getAuthor(),mSongMenuDetailBean.getContent().get(i).getSong_id(),MainActivity.currentTime));
                    Log.d("合法噶uydsf", "mList.size():" + mList.size());
                }
                DBPlayTools.getInstance().insertList(mList);
                Log.d("kbgncfbn", "DBPlayTools.getInstance().queryAll().size():" + DBPlayTools.getInstance().queryAll().size());
            }
        });
    }

    private void parseData(String newUrl) {
        NetHelper.MyRequest(newUrl, SongMenuDetailBean.class, new NetListener<SongMenuDetailBean>() {
            @Override
            public void successListener(SongMenuDetailBean response) {
                mSongMenuDetailBean = response;
                Picasso.with(context).load(mSongMenuDetailBean.getPic_300()).into(mImageViewS);
                mTextViewTitle.setText(mSongMenuDetailBean.getTitle());
                mTextViewTag.setText(mSongMenuDetailBean.getTag());
                mCountTv.setText(mSongMenuDetailBean.getContent().size()+"首歌");
                mTextView.setText(mSongMenuDetailBean.getDesc());
                Picasso.with(context).load(mSongMenuDetailBean.getPic_w700()).transform(new BlurTranformation(context)).into(mImageViewBig);
                mSMDAdapter.setSongMenuDetailBean(mSongMenuDetailBean);
                mRecyclerView.setAdapter(mSMDAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(manager);
                for (int i = 0; i < mSongMenuDetailBean.getContent().size(); i++) {
                    songIdList.add(mSongMenuDetailBean.getContent().get(i).getSong_id());
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
            case R.id.fragment_song_menu_detail_back_iv:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                fragmentTransaction.setCustomAnimations(R.anim.in,R.anim.out);
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("bcd"));

                fragmentTransaction.commit();
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_song_menu_detail_star_on:
                mImageViewStarOff.setVisibility(View.VISIBLE);
                mImageViewStarOn.setVisibility(View.INVISIBLE);

                /**
                 * 发送收藏的内容发给MineFragment
                 */




                break;
            case R.id.fragment_song_menu_detail_star_off:
                mImageViewStarOff.setVisibility(View.INVISIBLE);
                mImageViewStarOn.setVisibility(View.VISIBLE);
                break;
        }
    }
}
