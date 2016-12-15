package com.aqiang.dllo.mybaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.SeekBean;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;

/**
 * Created by qianggedemac on 16/12/14.
 */

public class SeekRV extends RecyclerView.Adapter<SeekRV.SeekViewHolder> {
//    @Override
//    public SeekRV.SeekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(SeekRV.SeekViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class SeekViewHolder extends RecyclerView.ViewHolder {
//        public SeekViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
    private Context mContext;
    private SeekBean mSeekBean;

    public SeekRV(Context context) {
        mContext = context;
    }

    public void setSeekBean(SeekBean seekBean) {
        Log.d("SeekRV", "seekBean.getResult().size():" + seekBean.getResult().size());
        mSeekBean = seekBean;
        Log.d("爱是福建按客户", mSeekBean.getResult().get(1).getWord());
        notifyDataSetChanged();
    }

    @Override
    public SeekRV.SeekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("沙发上", 2 + "");
        View view = LayoutInflater.from(mContext).inflate(R.layout.seek_detail_item,parent,false);
        SeekViewHolder holder = new SeekViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SeekRV.SeekViewHolder holder, int position) {
        Log.d("沙发上", 1 + "");
        holder.mTextViewTv.setText("哈哈哈");
        Log.d("SeekRV", mSeekBean.getResult().get(position).getWord());
        Log.d("按时间浪费和喀什", mSeekBean.getResult().get(1).getWord());
    }

    @Override
    public int getItemCount() {
        return mSeekBean.getResult().size();
    }

    public class SeekViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTv;
        public SeekViewHolder(View itemView) {
            super(itemView);
            mTextViewTv = (TextView)itemView.findViewById(R.id.seek_detail_item_tv);
        }
    }
}
