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
 * Created by dllo on 16/11/23.
 */

public class RecommendFragmentRecycleViewZeroAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewZeroAdapter.RECONE> {
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBean;
    private Context context;
    int pos;


    public RecommendFragmentRecycleViewZeroAdapter(Context context, int pos) {
        this.context = context;
        this.pos = pos;
    }

    public void setRecommendFragmentRecycleViewBean(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBean) {
        this.recommendFragmentRecycleViewBean = recommendFragmentRecycleViewBean;
        notifyDataSetChanged();
    }

    @Override
    public RecommendFragmentRecycleViewZeroAdapter.RECONE onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_item,parent,false);
        RECONE recone = new RECONE(view);
        return recone;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewZeroAdapter.RECONE holder, int position) {
        holder.textView.setText(recommendFragmentRecycleViewBean.getResult().getDiy().getResult().get(position).getTitle());
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getResult().getDiy().getResult().get(position).getPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class RECONE extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public RECONE(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rec_item_iv);
            textView = (TextView) itemView.findViewById(R.id.rec_item_tv);
        }
    }
}
