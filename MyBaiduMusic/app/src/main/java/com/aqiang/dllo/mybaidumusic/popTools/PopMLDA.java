package com.aqiang.dllo.mybaidumusic.popTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.LikeDetailAdapter;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.MenuListDetailBean;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.PlayBean;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */

public class PopMLDA extends BaseAdapter {
    private Context mContext;
    private List<PlayBean>  mList;

    public PopMLDA(Context context) {
        mContext = context;
    }

    public void setList(List<PlayBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
    public void removeBean(int position){

        mList.remove(mList.get(position));
        notifyDataSetChanged();
    }
}
