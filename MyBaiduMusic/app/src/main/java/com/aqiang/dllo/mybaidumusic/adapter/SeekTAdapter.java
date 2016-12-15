package com.aqiang.dllo.mybaidumusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.SeekTBean;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;
import com.squareup.picasso.Picasso;

/**
 * Created by qianggedemac on 16/12/15.
 */

public class SeekTAdapter extends BaseAdapter {
    private SeekTBean mSeekTBean;

    public void setSeekTBean(SeekTBean seekTBean) {
        mSeekTBean = seekTBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return mSeekTBean.getAlbum().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeekTViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(MyApp.getmContext()).inflate(R.layout.seek_item,parent,false);
            holder = new SeekTViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (SeekTViewHolder) convertView.getTag();
        }
        holder.mTextViewAuthorTv.setText(mSeekTBean.getAlbum().get(position).getArtistname());
        Picasso.with(MyApp.getmContext()).load(mSeekTBean.getAlbum().get(position).getArtistpic()).into(holder.mImageViewAuthorPicIv);

        return convertView;
    }
    public class SeekTViewHolder{
        TextView mTextViewAuthorTv;
        ImageView mImageViewAuthorPicIv;
        public SeekTViewHolder(View view){
            mTextViewAuthorTv = (TextView)view.findViewById(R.id.seek_item_author_tv);
            mImageViewAuthorPicIv = (ImageView)view.findViewById(R.id.seek_item_author_pic);
        }
    }
}
