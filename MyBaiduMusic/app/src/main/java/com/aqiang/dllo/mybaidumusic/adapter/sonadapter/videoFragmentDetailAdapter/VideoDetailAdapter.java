package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.videoFragmentDetailAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/25.
 * 音乐界面的适配界面的适配器
 */

public class VideoDetailAdapter extends RecyclerView.Adapter<VideoDetailAdapter.VideoViewHolder> {
    private VideoBean mVideoBean;
    private Context mContext;

    public VideoDetailAdapter(Context context) {
        mContext = context;
    }

    public void setVideoBean(VideoBean videoBean) {
        mVideoBean = videoBean;
        notifyDataSetChanged();
    }

    @Override
    public VideoDetailAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_fragment_detail_item,parent,false);
        VideoViewHolder holder = new VideoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoDetailAdapter.VideoViewHolder holder, int position) {

        holder.titleTv.setText(mVideoBean.getResult().getMv_list().get(position).getTitle());
        Log.d("vvv", mVideoBean.getResult().getMv_list() + "");
        holder.artistTv.setText(mVideoBean.getResult().getMv_list().get(position).getArtist());
        Picasso.with(mContext).load(mVideoBean.getResult().getMv_list().get(position).getThumbnail()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView titleTv,artistTv;
        public VideoViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.video_fragment_detail_item_thum);
            titleTv = (TextView)itemView.findViewById(R.id.video_fragment_detail_item_title);
            artistTv = (TextView)itemView.findViewById(R.id.video_fragment_detail_item_artist);
        }
    }
}
