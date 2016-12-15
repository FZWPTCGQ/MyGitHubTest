package com.aqiang.dllo.mybaidumusic.fragment.reusefragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;
import com.squareup.picasso.Picasso;

/**
 * Created by qianggedemac on 16/12/13.
 */

public class ReuseAdapter extends RecyclerView.Adapter<ReuseAdapter.ReuseViewHolder> {
    private ReuseFragmentBean mReuseFragmentBean;

    public void setReuseFragmentBean(ReuseFragmentBean reuseFragmentBean) {
        mReuseFragmentBean = reuseFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public ReuseAdapter.ReuseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApp.getmContext()).inflate(R.layout.live_fragment_content_item_detail_item,parent,false);
        ReuseViewHolder holder = new ReuseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReuseAdapter.ReuseViewHolder holder, int position) {
        holder.mTextViewNickName.setText(mReuseFragmentBean.getData().getData().get(position).getNickname());
        Log.d("ReuseAdapter", mReuseFragmentBean.getData().getData().get(position).getNickname());
        Picasso.with(MyApp.getmContext()).load(mReuseFragmentBean.getData().getData().get(position).getLiveimg()).into(holder.mImageViewLiveImg);
    }

    @Override
    public int getItemCount() {
        return mReuseFragmentBean.getData().getData().size();
    }

    public class ReuseViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewNickName;
        ImageView mImageViewLiveImg;
        public ReuseViewHolder(View itemView) {
            super(itemView);
            mTextViewNickName = (TextView)itemView.findViewById(R.id.live_fragment_content_item_detail_item_nickname);
            mImageViewLiveImg = (ImageView)itemView.findViewById(R.id.live_fragment_content_item_detail_item_liveimg);
        }
    }
}
