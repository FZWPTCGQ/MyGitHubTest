package com.aqiang.dllo.mybaidumusic.fragment.fatherfragment;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;

import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBTools;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {


    private ContentResolver mContentResolver;
    private TextView mMTvLike;
    private RelativeLayout mRelativeLayout;
    private TextView mTextViewCollection;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        mContentResolver = context.getContentResolver();
        mMTvLike = (TextView) getView().findViewById(R.id.mine_fragment_two_line_bendiyinyue_tv_params);
        mRelativeLayout = (RelativeLayout) getView().findViewById(R.id.mine_fragment_six_line_layout);
        mTextViewCollection = (TextView)getView().findViewById(R.id.mine_fragment_six_line_like_param);
    }


    @Override
    protected void initData() {
        int a = getLocalMusicCount();
        mMTvLike.setText(a + "");
        mRelativeLayout.setOnClickListener(this);
        int count = DBTools.getInstance().queryAll().size();
        mTextViewCollection.setText(count + "");
    }

    private int getLocalMusicCount() {
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        /**
         * 获取库中歌曲数量
         */
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_fragment_six_line_layout:
                /**
                 * 发一个广播发送给activity通知fragment占位
                 */
                Toast.makeText(context, "点击了我", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tools.ACTION_REPLACE);
                intent.putExtra("type",3);
                context.sendBroadcast(intent);
                break;

        }
    }
}
