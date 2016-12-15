package com.aqiang.dllo.mybaidumusic.adapter.fatheradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.LiveFragmentBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/27.
 */

public class LiveFragmentAdapter extends RecyclerView.Adapter<LiveFragmentAdapter.LiveViewHolder> {
    private Context mContext;
    private LiveFragmentBean mLiveFragmentBean;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View mHeaderView;


    public LiveFragmentAdapter(Context context) {
        mContext = context;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setLiveFragmentBean(LiveFragmentBean liveFragmentBean) {
        mLiveFragmentBean = liveFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0)return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public LiveFragmentAdapter.LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 头布局
         */
        if (mHeaderView != null && viewType == TYPE_HEADER) return new LiveViewHolder(mHeaderView);
        /**
         * 下面具体内容
         */
        View layout = LayoutInflater.from(mContext).inflate(R.layout.live_fragment_content_item_detail_item,parent,false);
        return new LiveViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(LiveFragmentAdapter.LiveViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;

        holder.mTextViewNickName.setText(mLiveFragmentBean.getData().getData().get(position).getNickname());
       String usercount =  mLiveFragmentBean.getData().getData().get(position).getUsercount() + "";
       if (usercount != null && usercount.isEmpty()){
        holder.mTextViewUserCount.setText(mLiveFragmentBean.getData().getData().get(position).getUsercount());
       }
        Picasso.with(mContext).load(mLiveFragmentBean.getData().getData().get(position).getLiveimg()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mLiveFragmentBean.getData().getData().size() : mLiveFragmentBean.getData().getData().size() - 1;
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {

            ImageView mImageView;
            TextView mTextViewNickName,mTextViewUserCount;
            public LiveViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView)itemView.findViewById(R.id.live_fragment_content_item_detail_item_liveimg);
                mTextViewNickName = (TextView)itemView.findViewById(R.id.live_fragment_content_item_detail_item_nickname);
                mTextViewUserCount = (TextView)itemView.findViewById(R.id.live_fragment_content_item_detail_item_usercount);
            }

    }

    @Override
    public void onViewAttachedToWindow(LiveViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }
}
