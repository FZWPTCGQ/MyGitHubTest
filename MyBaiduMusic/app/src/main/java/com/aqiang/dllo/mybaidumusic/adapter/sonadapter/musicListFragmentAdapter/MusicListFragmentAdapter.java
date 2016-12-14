package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.musicListFragmentAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.MusicListFragmentBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/25.
 * 音乐界面的榜单界面的适配器
 */

public class MusicListFragmentAdapter extends BaseAdapter {
    private MusicListFragmentBean mMusicListFragmentBean;
    private Context mContext;

    public MusicListFragmentAdapter(Context context) {
        mContext = context;
    }

    public void setMusicListFragmentBean(MusicListFragmentBean musicListFragmentBean) {
        mMusicListFragmentBean = musicListFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 13;
    }

    @Override
    public Object getItem(int position) {
        return mMusicListFragmentBean.getContent().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      MusicListViewHolder musicListViewHolder = null;
        if (convertView == null){
             convertView = LayoutInflater.from(mContext).inflate(R.layout.music_list_fragment_item,parent,false);
            musicListViewHolder = new MusicListViewHolder(convertView);
            convertView.setTag(musicListViewHolder);
        }else{
            musicListViewHolder = (MusicListViewHolder) convertView.getTag();
        }
        parseMethod(position, musicListViewHolder);

        return convertView;
    }

    /**
     * 绑定数据
     * @param position
     * @param musicListViewHolder
     */
    private void parseMethod(int position, MusicListViewHolder musicListViewHolder) {
        /**
         * 图片
         */
        String path = mMusicListFragmentBean.getContent().get(position).getPic_s210();
        // 图片网站不为null,没new过,在内存不存在
        // isEmpty -> ""
        if (path != null && !path.isEmpty()) {
            Picasso.with(mContext).load(mMusicListFragmentBean.getContent().get(position).getPic_s210()).fit().into(musicListViewHolder.imageViewPic);
        }
        Log.d("MusicListFragmentAdapte", musicListViewHolder.imageViewPic + "");
        /**
         * 标头
         */
        musicListViewHolder.nameFirstTv.setText(mMusicListFragmentBean.getContent().get(position).getName());
        /**
         * 第一行数据
         */
        musicListViewHolder.authorFirstTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(0).getAuthor());
        musicListViewHolder.titleFirstTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(0).getTitle() + "-");
        /**
         * 第二行
         */
        musicListViewHolder.authorTwoTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(1).getAuthor());
        musicListViewHolder.titleTwoTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(1).getTitle() + "-");
        /**
         * 第三行
         */
        musicListViewHolder.authorThreeTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(2).getAuthor());
        musicListViewHolder.titleThreeTv.setText(mMusicListFragmentBean.getContent().get(position).getContent().get(2).getTitle() + "-");
    }

    public class MusicListViewHolder{
        ImageView imageViewPic;
        TextView nameFirstTv;
        TextView titleFirstTv,authorFirstTv;
        TextView titleTwoTv,authorTwoTv;
        TextView titleThreeTv,authorThreeTv;
        public MusicListViewHolder(View view){
            imageViewPic = (ImageView)view.findViewById(R.id.music_list_fragment_internet_iv);
            /**
             * 标头
             */
            nameFirstTv = (TextView)view.findViewById(R.id.music_list_fragment_name_tv);
            /**
             * 第一行
             */
            titleFirstTv = (TextView)view.findViewById(R.id.music_list_fragment_first_title_tv);
            authorFirstTv = (TextView)view.findViewById(R.id.music_list_fragment_first_author_tv);
            /**
             * 第二行
             */
            titleTwoTv = (TextView)view.findViewById(R.id.music_list_fragment_two_title_tv);
            authorTwoTv = (TextView)view.findViewById(R.id.music_list_fragment_two_author_tv);
            /**
             * 第三行
             */
            titleThreeTv = (TextView)view.findViewById(R.id.music_list_fragment_three_title_tv);
            authorThreeTv = (TextView)view.findViewById(R.id.music_list_fragment_three_author_tv);
        }
    }
}
