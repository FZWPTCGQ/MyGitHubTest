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

public class RecommendFragmentRecycleViewFiveAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewFiveAdapter.FiveViewHolder> {
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanFive;
    private Context context;

    public RecommendFragmentRecycleViewFiveAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendFragmentRecycleViewBeanFive(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanFive) {
        this.recommendFragmentRecycleViewBeanFive = recommendFragmentRecycleViewBeanFive;
        notifyDataSetChanged();
    }

    @Override
    public RecommendFragmentRecycleViewFiveAdapter.FiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_item_detail_one,parent,false);
        FiveViewHolder holder = new FiveViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewFiveAdapter.FiveViewHolder holder, int position) {
       holder.mTextViewTitle.setText(recommendFragmentRecycleViewBeanFive.getResult().getMix_5().getResult().get(position).getTitle());
        holder.mTextViewAuthor.setText(recommendFragmentRecycleViewBeanFive.getResult().getMix_5().getResult().get(position).getAuthor());
        Picasso.with(context).load(recommendFragmentRecycleViewBeanFive.getResult().getMix_5().getResult().get(position).getPic()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class FiveViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextViewTitle;
        TextView mTextViewAuthor;
        public FiveViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.rec_item_detail_one_pic);
            mTextViewAuthor = (TextView)itemView.findViewById(R.id.rec_item_detail_one_author);
            mTextViewTitle = (TextView)itemView.findViewById(R.id.rec_item_detail_one_title);
        }
    }
}
