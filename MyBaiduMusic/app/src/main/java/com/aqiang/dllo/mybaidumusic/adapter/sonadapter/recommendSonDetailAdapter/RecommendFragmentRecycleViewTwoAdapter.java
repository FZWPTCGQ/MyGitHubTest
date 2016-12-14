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

public class RecommendFragmentRecycleViewTwoAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewTwoAdapter.TwoViewHoder> {
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanTwo;
    private Context context;

    public RecommendFragmentRecycleViewTwoAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendFragmentRecycleViewBeanTwo(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanTwo) {
        this.recommendFragmentRecycleViewBeanTwo = recommendFragmentRecycleViewBeanTwo;
        notifyDataSetChanged();
    }


    @Override
    public RecommendFragmentRecycleViewTwoAdapter.TwoViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_item_detail_one,parent,false);
        TwoViewHoder holder = new TwoViewHoder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendFragmentRecycleViewTwoAdapter.TwoViewHoder holder, int position) {
           holder.textViewTitle.setText(recommendFragmentRecycleViewBeanTwo.getResult().getMix_22().getResult().get(position).getTitle());
           holder.textViewAuthor.setText(recommendFragmentRecycleViewBeanTwo.getResult().getMix_22().getResult().get(position).getAuthor());
        Picasso.with(context).load(recommendFragmentRecycleViewBeanTwo.getResult().getMix_22().getResult().get(position).getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class TwoViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewAuthor;
        public TwoViewHoder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rec_item_detail_one_pic);
            textViewTitle = (TextView) itemView.findViewById(R.id.rec_item_detail_one_title);
            textViewAuthor = (TextView) itemView.findViewById(R.id.rec_item_detail_one_author);
        }
    }
}
