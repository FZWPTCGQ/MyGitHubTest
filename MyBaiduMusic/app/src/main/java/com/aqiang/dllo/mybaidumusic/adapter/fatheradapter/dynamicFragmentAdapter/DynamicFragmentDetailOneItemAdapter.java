package com.aqiang.dllo.mybaidumusic.adapter.fatheradapter.dynamicFragmentAdapter;

/**
 * Created by dllo on 16/11/26.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.DynamicFragmentBean;
import com.squareup.picasso.Picasso;

/**
 * 动态页面行布局利用recyclerView加载
 */

/**
 * 动态界面其中一个行布局适配器
 */
public class DynamicFragmentDetailOneItemAdapter extends RecyclerView.Adapter<DynamicFragmentDetailOneItemAdapter.TwoDetailViewHolder>{
    private Context mContext;
    private DynamicFragmentBean mDynamicFragmentBean;

    public DynamicFragmentDetailOneItemAdapter(Context context) {
        mContext = context;
    }

    public void setDynamicFragmentBean(DynamicFragmentBean dynamicFragmentBean) {
        mDynamicFragmentBean = dynamicFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public DynamicFragmentDetailOneItemAdapter.TwoDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dynamic_fragment_adapter_two_detail_item,parent,false);
        TwoDetailViewHolder holder = new TwoDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DynamicFragmentDetailOneItemAdapter.TwoDetailViewHolder holder, int position) {
           holder.mTextViewTitle.setText(mDynamicFragmentBean.getTopics().get(position).getTopic_title());

        String string = mDynamicFragmentBean.getTopics().get(position).getPic_350x170();
        int a = string.indexOf("@");
        String path = string.substring(0,a);
        Picasso.with(mContext).load(path).into(holder.mImageViewPic);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class TwoDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewPic;
        TextView mTextViewTitle;
        public TwoDetailViewHolder(View itemView) {
            super(itemView);
            mImageViewPic = (ImageView)itemView.findViewById(R.id.dynamic_fragment_adapter_two_detail_item_pic);
            mTextViewTitle = (TextView)itemView.findViewById(R.id.dynamic_fragment_adapter_two_detail_item_topic_title);
        }
    }
}
