package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.songMenuDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.tool.RVListener.OnItemClickListener;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/24.
 */

/**
 *
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotViewHolder> {
    private HotBean mHotBean;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setHotBean(HotBean hotBean) {
        mHotBean = hotBean;
        notifyDataSetChanged();
    }

    @Override
    public HotAdapter.HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_fragment_menu_rv_item,parent,false);
        HotViewHolder holder = new HotViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HotAdapter.HotViewHolder holder, final int position) {
        holder.titleTv.setText(mHotBean.getDiyInfo().get(position).getTitle());
        holder.userNameTv.setText(mHotBean.getDiyInfo().get(position).getUsername());
        holder.mTextViewListId.setText(mHotBean.getDiyInfo().get(position).getList_id());
        Log.d("uuu", "mHotBean.getDiyInfo().get(position).getListen_num():" + mHotBean.getDiyInfo().get(position).getListen_num());
        Picasso.with(context).load(mHotBean.getDiyInfo().get(position).getList_pic()).into(holder.listPicIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    int p = holder.getLayoutPosition();
                    String songId = mHotBean.getDiyInfo().get(position).getList_id();
                    mOnItemClickListener.onItemClickListener(p,songId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        ImageView listPicIv;
        TextView numTv;
        TextView userNameTv;
        TextView titleTv;
        TextView mTextViewListId;
        public HotViewHolder(View itemView) {
            super(itemView);
            listPicIv = (ImageView)itemView.findViewById(R.id.songfragmentmenu_rv_item_list_pic);
            numTv = (TextView)itemView.findViewById(R.id.songfragmentmenu_rv_item_listen_num_tv);
            userNameTv = (TextView)itemView.findViewById(R.id.songfragmentmenu_rv_item_username);
            titleTv = (TextView)itemView.findViewById(R.id.songfragmentmenu_rv_item_title);
            mTextViewListId = (TextView)itemView.findViewById(R.id.songfragmentmenu_rv_item_songId);
        }
    }
}
