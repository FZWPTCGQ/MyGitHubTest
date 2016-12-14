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

public class RecommendFragmentRecycleViewSevenAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewSevenAdapter.SevenViewHolder> {
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanSeven;
    private Context mContext;

    public RecommendFragmentRecycleViewSevenAdapter(Context context) {
        mContext = context;
    }

    public void setRecommendFragmentRecycleViewBeanSeven(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanSeven) {
        this.recommendFragmentRecycleViewBeanSeven = recommendFragmentRecycleViewBeanSeven;
        notifyDataSetChanged();
    }

    @Override
    public RecommendFragmentRecycleViewSevenAdapter.SevenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rec_item_detail_seven,parent,false);
        SevenViewHolder holder = new SevenViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewSevenAdapter.SevenViewHolder holder, int position) {
        holder.mTextViewTitle.setText(recommendFragmentRecycleViewBeanSeven.getResult().getMod_7().getResult().get(position).getTitle());
        holder.mTextViewDesc.setText(recommendFragmentRecycleViewBeanSeven.getResult().getMod_7().getResult().get(position).getDesc());
        Picasso.with(mContext).load(recommendFragmentRecycleViewBeanSeven.getResult().getMod_7().getResult().get(position).getPic()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class SevenViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextViewDesc;
        private TextView mTextViewTitle;
        public SevenViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.rec_item_detail_seven_pic_iv);
            mTextViewDesc = (TextView)itemView.findViewById(R.id.rec_item_detail_seven_desc_tv);
            mTextViewTitle = (TextView)itemView.findViewById(R.id.rec_item_detail_seven_title_tv);
        }
    }
}
