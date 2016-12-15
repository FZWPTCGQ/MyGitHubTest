package com.aqiang.dllo.mybaidumusic.fragment.reusefragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
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
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.BaseFragment;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReuseFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mImageViewReturn;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    public static ReuseFragment newInstance() {

        Bundle args = new Bundle();
        ReuseFragment fragment = new ReuseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView(View view) {
        mTabLayout = (TabLayout) getView().findViewById(R.id.fragment_reuse_tab);
        mViewPager = (ViewPager) getView().findViewById(R.id.fragment_reuse_vp);
        mImageViewReturn = (ImageView) getView().findViewById(R.id.fragment_reuse_return_iv);
        mImageViewReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_ONE));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_TWO));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_THREE));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_FOUR));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_FIVE));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_SIX));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_SEVEN));
        mFragments.add(ReuseFragmentDetail.newInstance(Tools.LIVE_EIGHT));
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),mFragments);
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in, R.anim.out);
        fragmentTransaction.remove(fragmentManager.findFragmentByTag("cgq"));
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
    }
    public class MyAdapter extends FragmentPagerAdapter{
         private List<Fragment> mFragmentList;
        private List<String> titles;
        public MyAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);
            mFragmentList = fragmentList;
            titles = new ArrayList<>();
            titles.add("全部");
            titles.add("女神");
            titles.add("好声音");
            titles.add("新秀");
            titles.add("劲爆");
            titles.add("搞笑");
            titles.add("萌妹");
            titles.add("推荐");
        }

        public void setFragmentList(List<Fragment> fragmentList) {
            mFragmentList = fragmentList;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
