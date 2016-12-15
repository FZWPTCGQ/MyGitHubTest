package com.aqiang.dllo.mybaidumusic.adapter.grandSonAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.MenuListDetailBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/30.
 * 音乐界面榜单那个界面的详情页面
 */

public class MLDFAdapter extends BaseAdapter {
    private Context mContext;
    private List<MenuListDetailBean.SongListBean> mSongListBeen;

    public MLDFAdapter(Context context) {
        mContext = context;
    }

    public void setSongListBeen(List<MenuListDetailBean.SongListBean> songListBeen) {
        mSongListBeen = songListBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSongListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mSongListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MLDFViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mldf_lv_item,parent,false);
            holder = new MLDFViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (MLDFViewHolder) convertView.getTag();
        }
        if (position == 0){
            holder.mImageViewColor.setImageResource(R.mipmap.img_king_mask01);
        }else if (position == 1){
            holder.mImageViewColor.setImageResource(R.mipmap.img_king_mask02);
        }else if (position == 2){
            holder.mImageViewColor.setImageResource(R.mipmap.img_king_mask03);
        }else{
            holder.mImageViewColor.setImageResource(R.mipmap.img_king_mask1);
        }
        holder.mTextViewRank.setText(mSongListBeen.get(position).getRank());
        holder.mTextViewAuthor.setText(mSongListBeen.get(position).getAuthor());
        holder.mTextViewTitle.setText(mSongListBeen.get(position).getTitle());
        Picasso.with(mContext).load(mSongListBeen.get(position).getPic_big()).into(holder.mImageViewPic);
        Log.d("MLDFEE", mSongListBeen.get(position).getPic_small());
        return convertView;
    }
    public class MLDFViewHolder{
        ImageView mImageViewPic,mImageViewColor;
        TextView mTextViewRank,mTextViewTitle,mTextViewAuthor;
        public MLDFViewHolder(View view){
          mImageViewPic = (ImageView)view.findViewById(R.id.mldf_lv_item_pic_small);
            mImageViewColor = (ImageView)view.findViewById(R.id.mldf_lv_item_img_color);
            mTextViewRank = (TextView) view.findViewById(R.id.mldf_lv_item_rank);
            mTextViewTitle = (TextView)view.findViewById(R.id.mldf_lv_item_title);
            mTextViewAuthor = (TextView)view.findViewById(R.id.mldf_lv_item_author);
        }
    }

}
