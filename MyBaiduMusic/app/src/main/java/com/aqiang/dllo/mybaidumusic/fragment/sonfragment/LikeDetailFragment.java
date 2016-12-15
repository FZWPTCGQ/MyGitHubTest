package com.aqiang.dllo.mybaidumusic.fragment.sonfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.LikeDetailAdapter;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.CollectionBean;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBTools;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeDetailFragment extends BaseFragment implements View.OnClickListener {


    private ImageView mImageViewBack;
    private TextView mTextViewCount;
    private ListView mListView;
    private LikeDetailAdapter mLikeDetailAdapter;
    private List<CollectionBean> mList;

    public static LikeDetailFragment newInstance() {

        Bundle args = new Bundle();

        LikeDetailFragment fragment = new LikeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initView(View view) {
        mList = new ArrayList<>();
        mImageViewBack = (ImageView)getView().findViewById(R.id.fra_mi_my_like_song_return);
        mTextViewCount = (TextView) getView().findViewById(R.id.fra_mi_my_like_song_count);
        mListView = (ListView)getView().findViewById(R.id.middle_listview);
        mList = DBTools.getInstance().queryAll();
        mLikeDetailAdapter = new LikeDetailAdapter(context);

    }

    @Override
    protected void initData() {
        addListener();
    }

    private void addListener() {
        mImageViewBack.setOnClickListener(this);
        addDatas();
        mLikeDetailAdapter.setList(mList);
        mListView.setAdapter(mLikeDetailAdapter);
    }

    private void addDatas() {
        int count = DBTools.getInstance().queryAll().size();
        mTextViewCount.setText(count + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fra_mi_my_like_song_return:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.in,R.anim.out);
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("789"));
                fragmentManager.popBackStack();
                fragmentTransaction.commit();
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
