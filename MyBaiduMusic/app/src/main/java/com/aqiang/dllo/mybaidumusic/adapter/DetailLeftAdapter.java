package com.aqiang.dllo.mybaidumusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.activity.BaseActivity;
import com.aqiang.dllo.mybaidumusic.bean.DetailLeftBean;

/**
 * Created by qianggedemac on 16/12/13.
 */

public class DetailLeftAdapter extends BaseAdapter {
    private Context mContext;
    private DetailLeftBean mDetailLeftBean;

    public DetailLeftAdapter(Context context) {
        mContext = context;
    }

    public void setDetailLeftBean(DetailLeftBean detailLeftBean) {
        mDetailLeftBean = detailLeftBean;
        //  Log.d("DetailLeftAdapter", "mDetailLeftBean:" + mDetailLeftBean.getResult().getList().size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //   Log.d("sadf", "mDetailLeftBean.getResult().getList().size():" + mDetailLeftBean.getResult().getList().size());
        return mDetailLeftBean.getResult() !=null && mDetailLeftBean.getResult().getList() != null && mDetailLeftBean.getResult().getList().size() >0 ? mDetailLeftBean.getResult().getList().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDetailLeftBean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftHolder leftHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shoucang_item, parent, false);
            leftHolder = new LeftHolder(convertView);
            convertView.setTag(leftHolder);
        } else {
            leftHolder = (LeftHolder) convertView.getTag();
        }
        leftHolder.mTextViewTitleTv.setText(mDetailLeftBean.getResult().getList().get(position).getAlbum_title());
        leftHolder.mTextViewSingerTv.setText(mDetailLeftBean.getResult().getList().get(position).getAuthor());
        return convertView;
    }

    public class LeftHolder {
        private TextView mTextViewSingerTv;
        private TextView mTextViewTitleTv;

        public LeftHolder(View view) {
            mTextViewSingerTv = (TextView) view.findViewById(R.id.shoucang_use_name_tv);
            mTextViewTitleTv = (TextView) view.findViewById(R.id.shoucang_title_tv);
        }
    }
}
