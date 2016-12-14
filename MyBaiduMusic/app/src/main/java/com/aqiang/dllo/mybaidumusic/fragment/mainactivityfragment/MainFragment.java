package com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 实现点击下面的音乐播放啦弹出fragment
 */
public class MainFragment extends Fragment implements View.OnClickListener {


    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mList;
    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager)view.findViewById(R.id.main_fragment_vp);
        mImageView = (ImageView)view.findViewById(R.id.song_page_return);
        mList = new ArrayList<>();
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),mList);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList.add(new Fragment());
        mList.add(new TwoFragment());
        mList.add(new ThreeFragment());
        mViewPager.setAdapter(mViewPagerAdapter);
        /**
         * 退出播放页的图片按钮
         */
        mImageView.setOnClickListener(this);
        /**
         * 首次进入ViewPager显示第几页
         */
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.song_page_return:
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
              FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_in,R.anim.fragment_out);
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("fra_from_down_music_main"));
                fragmentTransaction.commit();

                break;
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
