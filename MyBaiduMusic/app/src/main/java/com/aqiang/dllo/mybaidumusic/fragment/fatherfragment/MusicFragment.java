package com.aqiang.dllo.mybaidumusic.fragment.fatherfragment;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.KFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.MusicListFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.RecommendFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.SongMenuFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment {


    private TabLayout tab;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private MusicFragmentAdapter musicFragmentAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView(View view) {
        /**
         * 初始化数据
         */
      addView();
    }

    private void addView() {
        tab = (TabLayout) getView().findViewById(R.id.musicfragment_tab);
        viewPager = (ViewPager) getView().findViewById(R.id.musicfragment_vp);
        fragments = new ArrayList<>();
        musicFragmentAdapter = new MusicFragmentAdapter(getChildFragmentManager(),fragments);

    }

    @Override
    protected void initData() {
        /**
         * 添加子fragment
         */
      addDatas();
    }

    private void addDatas() {
        fragments.add(new RecommendFragment());
        fragments.add(new SongMenuFragment());
        fragments.add(new MusicListFragment());
        fragments.add(new VideoFragment());
        fragments.add(new KFragment());
        viewPager.setAdapter(musicFragmentAdapter);
        tab.setupWithViewPager(viewPager);
        tab.setSelectedTabIndicatorColor(0xff00b4ff);
        tab.setTabTextColors(Color.rgb(0x00,0x00,0x00), Color.rgb(0x49,0xB3,0xE8));
    }


    public class MusicFragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragments;
        private List<String> titles;
        public MusicFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
            titles = new ArrayList<>();
            titles.add("推荐");
            titles.add("歌单");
            titles.add("榜单");
            titles.add("视频");
            titles.add("K歌");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
