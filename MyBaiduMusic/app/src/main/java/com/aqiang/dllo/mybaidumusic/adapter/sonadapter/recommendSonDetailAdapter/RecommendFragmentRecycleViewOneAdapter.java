package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.recommendSonDetailAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class RecommendFragmentRecycleViewOneAdapter extends RecyclerView.Adapter<RecommendFragmentRecycleViewOneAdapter.OneViewHolder>{
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanOne;
    private Context context;

    public RecommendFragmentRecycleViewOneAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendFragmentRecycleViewBeanOne(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBeanOne) {
        this.recommendFragmentRecycleViewBeanOne = recommendFragmentRecycleViewBeanOne;
        notifyDataSetChanged();
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewOne = LayoutInflater.from(context).inflate(R.layout.rec_item_detail_one,parent,false);
        OneViewHolder holder = new OneViewHolder(viewOne);
        return holder;
    }

    @Override
    public void onBindViewHolder(OneViewHolder holder, int position) {
         holder.textViewTitle.setText(recommendFragmentRecycleViewBeanOne.getResult().getMix_1().getResult().get(position).getTitle());
        holder.textViewAuthor.setText(recommendFragmentRecycleViewBeanOne.getResult().getMix_1().getResult().get(position).getAuthor());
        Picasso.with(context).load(recommendFragmentRecycleViewBeanOne.getResult().getMix_1().getResult().get(position).getPic()).into(holder.imageView);
        Log.d("mmm", recommendFragmentRecycleViewBeanOne.getResult().getMix_1().getResult().get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class OneViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewAuthor;
        public OneViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rec_item_detail_one_pic);
            textViewTitle = (TextView) itemView.findViewById(R.id.rec_item_detail_one_title);
            textViewAuthor = (TextView) itemView.findViewById(R.id.rec_item_detail_one_author);
        }
    }
}
