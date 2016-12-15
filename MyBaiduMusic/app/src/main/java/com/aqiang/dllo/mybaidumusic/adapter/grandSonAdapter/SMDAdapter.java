package com.aqiang.dllo.mybaidumusic.adapter.grandSonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.SongMenuDetailBean;
import com.aqiang.dllo.mybaidumusic.tool.RVListener.OnItemClickListener;

/**
 * Created by dllo on 16/12/2.
 * 歌单界面详情页适配器
 */

public class SMDAdapter extends RecyclerView.Adapter<SMDAdapter.SMDViewHolder>{
    private Context mContext;
    private SongMenuDetailBean mSongMenuDetailBean;
    private OnItemClickListener mOnItemClickListener;
    public SMDAdapter(Context context) {
        mContext = context;
    }

    public void setSongMenuDetailBean(SongMenuDetailBean songMenuDetailBean) {
        mSongMenuDetailBean = songMenuDetailBean;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public SMDAdapter.SMDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.smda_rv_item,parent,false);
        SMDViewHolder holder = new SMDViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SMDAdapter.SMDViewHolder holder, int position) {
        holder.mTextViewTitle.setText(mSongMenuDetailBean.getContent().get(position).getTitle());
        holder.mTextViewAuthor.setText(mSongMenuDetailBean.getContent().get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = holder.getLayoutPosition();
                SongMenuDetailBean bean = mSongMenuDetailBean;
                mOnItemClickListener.method(p,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongMenuDetailBean.getContent().size();
    }

    public class SMDViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTitle;
        TextView mTextViewAuthor;
        public SMDViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView)itemView.findViewById(R.id.smda_rv_item_title_tv);
            mTextViewAuthor = (TextView)itemView.findViewById(R.id.smda_rv_item_author);
        }
    }
}
