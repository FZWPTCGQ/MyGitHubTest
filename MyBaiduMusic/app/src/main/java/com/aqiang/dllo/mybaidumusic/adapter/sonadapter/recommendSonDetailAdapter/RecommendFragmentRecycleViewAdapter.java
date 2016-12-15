package com.aqiang.dllo.mybaidumusic.adapter.sonadapter.recommendSonDetailAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.grandSonDetailBean.SongMenuDetailBean;
import com.aqiang.dllo.mybaidumusic.tool.RVListener.OnItemClickListener;
import com.aqiang.dllo.mybaidumusic.tool.grideimageloader.GrideImageLoader;

import com.aqiang.dllo.mybaidumusic.bean.sonBean.RecommendFragmentRecycleViewBean;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

/**
 * 音乐界面的推荐界面的适配器
 */
public class RecommendFragmentRecycleViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBean;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private static final int TYPE_FOUR = 4;
    private static final int TYPE_FIVE = 5;
    private static final int TYPE_SIX = 6;
    private static final int TYPE_SEVEN = 7;
    private static final int TYPE_EIGHT = 8;

    /**
     * 加载头布局实现轮播
     */
    private View mHeaderView;
    private HeaderHolder mHeaderHolder;


    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public RecommendFragmentRecycleViewAdapter(Context context) {
        this.context = context;


    }

    public void setRecommendFragmentRecycleViewBean(RecommendFragmentRecycleViewBean recommendFragmentRecycleViewBean) {
        this.recommendFragmentRecycleViewBean = recommendFragmentRecycleViewBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        /**
         * 加载头布局
         */


        /**
         * recyclerView加载不同行布局
         */
        if (position == TYPE_HEADER) {
            return TYPE_HEADER;
        } else if (position == TYPE_ONE) {
            return 1;
        } else if (position == TYPE_TWO) {
            return 2;
        } else if (position == TYPE_THREE) {
            return 3;
        } else if (position == TYPE_FOUR) {
            return 4;
        } else if (position == TYPE_FIVE) {
            return 5;
        } else if (position == TYPE_SIX) {
            return 6;
        } else if (position == TYPE_SEVEN) {
            return 7;
        } else {
            return TYPE_EIGHT;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * recyclerView加载不同行布局
         */
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEADER:

                mHeaderView = LayoutInflater.from(context).inflate(R.layout.rocommendfragment_header_item, parent, false);

                holder = new HeaderHolder(mHeaderView);
                break;
            case TYPE_ONE:
                View view0 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view0);
                break;
            case TYPE_TWO:
                View view1 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view1);
                break;
            case TYPE_THREE:
                View view2 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view2);
                break;
            case TYPE_FOUR:
                View view3 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_two, parent, false);
                holder = new ViewHolderTwo(view3);
                break;
            case TYPE_FIVE:
                View view4 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view4);
                break;
            case TYPE_SIX:
                View view5 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view5);
                break;
            case TYPE_SEVEN:
                View view6 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_one, parent, false);
                holder = new ViewHolderOne(view6);
                break;
            case TYPE_EIGHT:
                View view7 = LayoutInflater.from(context).inflate(R.layout.recommendfragment_different_item_three, parent, false);
                holder = new ViewHolderThree(view7);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_HEADER:
                methodHeader((HeaderHolder) holder);
                break;
            case TYPE_ONE:
                methodOne((ViewHolderOne) holder);
                break;
            case TYPE_TWO:
                methodTwo((ViewHolderOne) holder, position);
                break;
            case TYPE_THREE:
                methodThree((ViewHolderOne) holder);
                break;
            case TYPE_FOUR:
                methodFour((ViewHolderTwo) holder);
                break;
            case TYPE_FIVE:
                methodFive((ViewHolderOne) holder);
                break;
            case TYPE_SIX:
                methodSix((ViewHolderOne) holder);
                break;
            case TYPE_SEVEN:
                methodSeven((ViewHolderOne) holder);
                break;
            case TYPE_EIGHT:
                methodEight((ViewHolderThree) holder);
                break;

        }
    }

    private void methodHeader(HeaderHolder holder) {
        mHeaderHolder = holder;
        ArrayList<String> image = new ArrayList<>();
        for (int i = 0; i < recommendFragmentRecycleViewBean.getResult().getFocus().getResult().size(); i++) {
            image.add(recommendFragmentRecycleViewBean.getResult().getFocus().getResult().get(i).getRandpic_iphone6());
        }
        mHeaderHolder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        mHeaderHolder.mBanner.setImageLoader(new GrideImageLoader());
        // 设置图片集合
        mHeaderHolder.mBanner.setImages(image);
        // 设置banner动画效果
        mHeaderHolder.mBanner.setBannerAnimation(Transformer.DepthPage);
        mHeaderHolder.mBanner.setBannerAnimation(Transformer.DepthPage);
        // 设置自动轮播 默认为true
        mHeaderHolder.mBanner.isAutoPlay(true);
        // 设置轮播时间
        mHeaderHolder.mBanner.setDelayTime(2000);
        // 设置指示器位置 (当banner模式中有指示器时)
        mHeaderHolder.mBanner.setIndicatorGravity(BannerConfig.CENTER);
        // banner设置方法全部调用完毕时最后调用
        mHeaderHolder.mBanner.start();
    }

    private void methodEight(ViewHolderThree holder) {
        ViewHolderThree seven = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(13).getPicurl()).into(seven.imageViewThree);
        seven.textViewThree.setText(recommendFragmentRecycleViewBean.getModule().get(13).getTitle());
        RecommendFragmentRecycleViewSevenAdapter adapter = new RecommendFragmentRecycleViewSevenAdapter(context);
        adapter.setRecommendFragmentRecycleViewBeanSeven(recommendFragmentRecycleViewBean);
        seven.recyclerViewThree.setAdapter(adapter);
        LinearLayoutManager manager6 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        seven.recyclerViewThree.setLayoutManager(manager6);
    }

    private void methodSeven(ViewHolderOne holder) {
        ViewHolderOne six = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(12).getPicurl()).into(six.imageViewOne);
        six.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(12).getTitle());
        RecommendFragmentRecycleViewSixAdapter recommendFragmentRecycleViewSixAdapter = new RecommendFragmentRecycleViewSixAdapter(context);
        recommendFragmentRecycleViewSixAdapter.setRecommendFragmentRecycleViewBeanSix(recommendFragmentRecycleViewBean);
        six.recyclerViewOne.setAdapter(recommendFragmentRecycleViewSixAdapter);
        StaggeredGridLayoutManager manager5 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        six.recyclerViewOne.setLayoutManager(manager5);
    }

    private void methodSix(ViewHolderOne holder) {
        ViewHolderOne five = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(11).getPicurl()).into(five.imageViewOne);
        five.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(11).getTitle());
        RecommendFragmentRecycleViewFiveAdapter recommendFragmentRecycleViewFiveAdapter = new RecommendFragmentRecycleViewFiveAdapter(context);
        recommendFragmentRecycleViewFiveAdapter.setRecommendFragmentRecycleViewBeanFive(recommendFragmentRecycleViewBean);
        five.recyclerViewOne.setAdapter(recommendFragmentRecycleViewFiveAdapter);
        StaggeredGridLayoutManager manager4 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        five.recyclerViewOne.setLayoutManager(manager4);
    }

    private void methodFive(ViewHolderOne holder) {
        ViewHolderOne four = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(10).getPicurl()).into(four.imageViewOne);
        four.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(10).getTitle());
        RecommendFragmentRecycleViewFourAdapter recommendFragmentRecycleViewFourAdapter = new RecommendFragmentRecycleViewFourAdapter(context);
        recommendFragmentRecycleViewFourAdapter.setRecommendFragmentRecycleViewBeanFour(recommendFragmentRecycleViewBean);
        four.recyclerViewOne.setAdapter(recommendFragmentRecycleViewFourAdapter);
        StaggeredGridLayoutManager manager3 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        four.recyclerViewOne.setLayoutManager(manager3);
    }

    private void methodFour(ViewHolderTwo holder) {
        ViewHolderTwo three = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getResult().getAd_small().getResult().get(0).getPic()).into(three.imageView);
    }

    private void methodThree(ViewHolderOne holder) {
        ViewHolderOne two = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(6).getPicurl()).into(two.imageViewOne);
        two.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(6).getTitle());
        RecommendFragmentRecycleViewTwoAdapter twoAdapter = new RecommendFragmentRecycleViewTwoAdapter(context);
        twoAdapter.setRecommendFragmentRecycleViewBeanTwo(recommendFragmentRecycleViewBean);
        two.recyclerViewOne.setAdapter(twoAdapter);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        two.recyclerViewOne.setLayoutManager(manager2);
    }

    private void methodTwo(ViewHolderOne holder, int position) {
        ViewHolderOne one = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(5).getPicurl()).into(one.imageViewOne);
        one.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(5).getTitle());
        RecommendFragmentRecycleViewOneAdapter recommendFragmentRecycleViewOneAdapter = new RecommendFragmentRecycleViewOneAdapter(context);
        recommendFragmentRecycleViewOneAdapter.setRecommendFragmentRecycleViewBeanOne(recommendFragmentRecycleViewBean);
        Log.d("zzz", recommendFragmentRecycleViewBean.getResult().getMix_1().getResult().get(position).getAuthor());
        one.recyclerViewOne.setAdapter(recommendFragmentRecycleViewOneAdapter);
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        one.recyclerViewOne.setLayoutManager(manager1);
    }


    /**
     * 推荐歌单推荐
     * @param holder
     */
    private void methodOne(ViewHolderOne holder) {
        ViewHolderOne zero = holder;
        Picasso.with(context).load(recommendFragmentRecycleViewBean.getModule().get(3).getPicurl()).into(zero.imageViewOne);
        zero.textViewOne.setText(recommendFragmentRecycleViewBean.getModule().get(3).getTitle());
        RecommendFragmentRecycleViewZeroAdapter recommendFragmentRecycleViewOne = new RecommendFragmentRecycleViewZeroAdapter(context, TYPE_ONE);
        recommendFragmentRecycleViewOne.setRecommendFragmentRecycleViewBean(recommendFragmentRecycleViewBean);
        zero.recyclerViewOne.setAdapter(recommendFragmentRecycleViewOne);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        zero.recyclerViewOne.setLayoutManager(manager);
        recommendFragmentRecycleViewOne.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, String songIdList) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("replace");
                intent.putExtra("type", 2);
                intent.putExtra("listId", songIdList);
                Log.d("RecommendFragmentRecycl", songIdList);
                intent.putExtra("position", position);
                context.sendBroadcast(intent);


            }

            @Override
            public void method(int position, SongMenuDetailBean songMenuDetailBean) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return 9;
    }


    public class HeaderHolder extends RecyclerView.ViewHolder {
        Banner mBanner;

        public HeaderHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        TextView textViewOne;
        ImageView imageViewOne;
        RecyclerView recyclerViewOne;

        public ViewHolderOne(View itemView) {
            super(itemView);
            recyclerViewOne = (RecyclerView) itemView.findViewById(R.id.recommendFragment_different_item_one_rv);
            imageViewOne = (ImageView) itemView.findViewById(R.id.recommendFragment_different_item_one_iv);
            textViewOne = (TextView) itemView.findViewById(R.id.recommendFragment_different_item_one_tv);
        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.recommendFragment_different_item_two_iv);
        }
    }

    public class ViewHolderThree extends RecyclerView.ViewHolder {
        ImageView imageViewThree;
        TextView textViewThree;
        RecyclerView recyclerViewThree;

        public ViewHolderThree(View itemView) {
            super(itemView);
            recyclerViewThree = (RecyclerView) itemView.findViewById(R.id.recommendFragment_different_item_three_rv);
            imageViewThree = (ImageView) itemView.findViewById(R.id.recommendFragment_different_item_three_iv);
            textViewThree = (TextView) itemView.findViewById(R.id.recommendFragment_different_item_three_tv);
        }
    }
}
