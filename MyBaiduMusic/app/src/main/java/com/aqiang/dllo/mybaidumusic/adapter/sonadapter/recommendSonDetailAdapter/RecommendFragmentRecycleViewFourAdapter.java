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

public class RecommendFragmentRecycleViewFourAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewFourAdapter.FourViewHolder> {
    private RecommendFragmentRecycleViewBean mRecommendFragmentRecycleViewBeanFour;
    private Context context;

    public RecommendFragmentRecycleViewFourAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendFragmentRecycleViewBeanFour(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanFour) {
        mRecommendFragmentRecycleViewBeanFour = recommendFragmentRecycleViewBeanFour;
        notifyDataSetChanged();
    }

    @Override
    public RecommendFragmentRecycleViewFourAdapter.FourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_item,parent,false);
        FourViewHolder holder = new FourViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewFourAdapter.FourViewHolder holder, int position) {
        holder.mTextView.setText(mRecommendFragmentRecycleViewBeanFour.getResult().getMix_9().getResult().get(position).getTitle());
        Picasso.with(context).load(mRecommendFragmentRecycleViewBeanFour.getResult().getMix_9().getResult().get(position).getPic()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class FourViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public FourViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.rec_item_iv);
            mTextView = (TextView)itemView.findViewById(R.id.rec_item_tv);
        }
    }
}
