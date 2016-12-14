package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.kFragmentSonDetailAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.KFragmentBean;

/**
 * Created by dllo on 16/11/25.
 */

/**
 * 音乐界面的K歌界面的适配器
 */

public class KFragmentAdapter extends BaseAdapter {
    private KFragmentBean mKFragmentBean;
    private Context mContext;

    public KFragmentAdapter(Context context) {
        mContext = context;
    }

    public void setKFragmentBean(KFragmentBean KFragmentBean) {
        mKFragmentBean = KFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return mKFragmentBean.getResult().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.k_fragment_listview_item,parent,false);
            holder = new KViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (KViewHolder) convertView.getTag();
        }
        holder.songTitleTv.setText(mKFragmentBean.getResult().getItems().get(position).getSong_title() + " - ");
        holder.artistNameTv.setText(mKFragmentBean.getResult().getItems().get(position).getArtist_name());
        holder.playNameTv.setText(mKFragmentBean.getResult().getItems().get(position).getPlay_num() + "人唱过");
        return convertView;
    }
    public class KViewHolder{
         TextView songTitleTv;
        TextView artistNameTv;
        TextView playNameTv;
        public KViewHolder(View view){
          songTitleTv = (TextView)view.findViewById(R.id.k_fragment_listview_item_song_title);
           artistNameTv = (TextView)view.findViewById(R.id.k_fragment_listview_item_artist_name);
            playNameTv = (TextView)view.findViewById(R.id.k_fragment_listview_item_play_num);
        }
    }
}
