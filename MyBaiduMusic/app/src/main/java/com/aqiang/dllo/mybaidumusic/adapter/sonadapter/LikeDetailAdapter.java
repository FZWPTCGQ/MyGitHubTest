package com.aqiang.dllo.mybaidumusic.adapter.sonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.CollectionBean;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */

public class LikeDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<CollectionBean> mList;

    public LikeDetailAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<CollectionBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null && mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null && mList.size() > 0 ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LikeHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shoucang_item,parent,false);
            holder = new LikeHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (LikeHolder) convertView.getTag();
        }
        holder.titleTv.setText(mList.get(position).getTitle());
        holder.singerTv.setText(mList.get(position).getSinger());
        return convertView;
    }
    public class LikeHolder{
        TextView singerTv,titleTv;
        public LikeHolder(View view){
            singerTv = (TextView)view.findViewById(R.id.shoucang_use_name_tv);
            titleTv = (TextView)view.findViewById(R.id.shoucang_title_tv);
        }
    }
}
