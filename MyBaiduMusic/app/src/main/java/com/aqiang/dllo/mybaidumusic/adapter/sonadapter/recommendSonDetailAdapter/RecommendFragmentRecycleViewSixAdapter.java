package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.recommendSonDetailAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.RecommendFragmentRecycleViewBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/24.
 */

public class RecommendFragmentRecycleViewSixAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewSixAdapter.SixViewHolder> {
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanSix;
    private Context mContext;

    public RecommendFragmentRecycleViewSixAdapter(Context context) {
        mContext = context;
    }

    public void setRecommendFragmentRecycleViewBeanSix(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanSix) {
        this.recommendFragmentRecycleViewBeanSix = recommendFragmentRecycleViewBeanSix;
        notifyDataSetChanged();
    }

    @Override
    public RecommendFragmentRecycleViewSixAdapter.SixViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rec_item,parent,false);
        SixViewHolder holder = new SixViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewSixAdapter.SixViewHolder holder, int position) {
      holder.mTextView.setText(recommendFragmentRecycleViewBeanSix.getResult().getRadio().getResult().get(position).getTitle());
        Picasso.with(mContext).load(recommendFragmentRecycleViewBeanSix.getResult().getRadio().getResult().get(position).getPic()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class SixViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public SixViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.rec_item_iv);
            mTextView = (TextView)itemView.findViewById(R.id.rec_item_tv);
        }
    }
}
