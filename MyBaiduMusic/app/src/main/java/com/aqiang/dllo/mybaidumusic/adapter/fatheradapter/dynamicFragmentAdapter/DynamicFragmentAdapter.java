package com.aqiang.dllo.mybaidumusic.adapter.fatheradapter.dynamicFragmentAdapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.bean.fatherBean.DynamicFragmentBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/26.
 * 动态界面的适配器
 */

public class DynamicFragmentAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private DynamicFragmentBean mDynamicFragmentBean;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private static final int TYPE_FOUR = 4;
    private static final int TYPE_FIVE = 5;
    private static final int TYPE_SIX = 6;
    private static final int TYPE_SEVEN = 7;
    private static final int TYPE_EIGHT = 8;
    private static final int TYPE_NINE = 9;
    private static final int TYPE_TEN = 10;
    private int mP;


    public DynamicFragmentAdapter(Context context) {
        mContext = context;
    }

    public void setDynamicFragmentBean(DynamicFragmentBean dynamicFragmentBean) {
        mDynamicFragmentBean = dynamicFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
            mP = mDynamicFragmentBean.getMsg().get(position).getPiclist().size();
        }

        if (position == 0&&mDynamicFragmentBean.getMsg().get(position).getPiclist() != null) {
            return TYPE_NINE;
        } else if (position == 1) {
            return TYPE_TEN;
        } else if (mP == 1){
            return TYPE_ONE;
        } else if (mP == 2){
            return TYPE_TWO;
        } else if (mP == 3){
            return TYPE_THREE;
        } else if (mP == 4){
            return TYPE_FOUR;
        }else if (mP == 5){
            return TYPE_FIVE;
        }else if (mP == 6){
            return TYPE_SIX;
        }else if ( mDynamicFragmentBean.getMsg().get(position).getMsgtype() == 2){
            return TYPE_SEVEN;
        }else if (mDynamicFragmentBean.getMsg().get(position).getPiclist() == null){
            return TYPE_EIGHT;
        }else {
            return TYPE_NINE;
        }
        /**
         *
         */
//        if (position == 0) {
//            return TYPE_NINE;
//        } else if (mDynamicFragmentBean.getMsg().get(position).getPiclist()==null){
//            return TYPE_EIGHT;
//
//        }else if (position == 1) {
//            return TYPE_TEN;
//        } else if (mP == 1){
//            return TYPE_ONE;
//        } else if (mP == 2){
//            return TYPE_TWO;
//        } else if (mP == 3){
//            return TYPE_THREE;
//        } else if (mP == 4){
//            return TYPE_FOUR;
//        }else if (mP == 5){
//            return TYPE_FIVE;
//        }else if (mP == 6){
//            return TYPE_SIX;
//        }else if ( mDynamicFragmentBean.getMsg().get(position).getMsgtype() == 2){
//            return TYPE_SEVEN;
//        } else {
//            return TYPE_NINE;
//        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NINE:
                /**
                 * 第一行
                 */
                View viewZero = LayoutInflater.from(mContext).inflate(R.layout.dynamic_fragment_adapter_one_item, parent, false);
                holder = new NineViewHolder(viewZero);
                break;
            case TYPE_TEN:
                /**
                 *   第二行
                 */

                View viewTen = LayoutInflater.from(mContext).inflate(R.layout.dynamic_fragment_adapter_two_item, parent, false);
                holder = new TenViewHolder(viewTen);
                break;
            /**
             * 一张图
             */
            case TYPE_ONE:
                View viewOne = LayoutInflater.from(mContext).inflate(R.layout.dfa_one_tu, parent, false);
                holder = new OneViewHolder(viewOne);
                break;
            /**
             * 两张图
             */
            case TYPE_TWO:
                View viewTwo = LayoutInflater.from(mContext).inflate(R.layout.dfa_two_tu,parent,false);
                holder = new TwoViewHolder(viewTwo);
                break;
            case TYPE_THREE:
                View viewThree = LayoutInflater.from(mContext).inflate(R.layout.dfa_three_tu,parent,false);
                holder = new ThreeViewHolder(viewThree);
                break;
            case TYPE_FOUR:
                View viewFour = LayoutInflater.from(mContext).inflate(R.layout.dfa_four_tu,parent,false);
                holder = new FourViewHolder(viewFour);
                break;
            case TYPE_FIVE:
                View viewFive = LayoutInflater.from(mContext).inflate(R.layout.dfa_five_tu,parent,false);
                holder = new FiveViewHolder(viewFive);
                break;
            case TYPE_SIX:
                View viewSix = LayoutInflater.from(mContext).inflate(R.layout.dfa_six_tu,parent,false);
                holder = new SixViewHolder(viewSix);
                break;
            /**
             * msgType == 2
             */
            case TYPE_SEVEN:
                View viewSeven = LayoutInflater.from(mContext).inflate(R.layout.dfa_seven_tu, parent, false);
                holder = new SevenViewHolder(viewSeven);
                break;
            /**
             * 没有图
             */
            case TYPE_EIGHT:
                Log.d("无图", "无图");
                View viewEight = LayoutInflater.from(mContext).inflate(R.layout.dfa_eight_tu, parent, false);
                holder = new EightViewHolder(viewEight);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int a = getItemViewType(position);
        switch (a) {
            case TYPE_NINE:
                NineViewHolder nine = (NineViewHolder) holder;
                Picasso.with(mContext).load(mDynamicFragmentBean.getTopics().get(1).getPic_750x215()).into(nine.mImageView);
                break;
            case TYPE_TEN:
                /**
                 * 第二行
                 */
                TenViewHolder ten = (TenViewHolder) holder;
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                ten.mRecyclerView.setLayoutManager(manager);
                DynamicFragmentDetailOneItemAdapter oneItemAdapter = new DynamicFragmentDetailOneItemAdapter(mContext);
                oneItemAdapter.setDynamicFragmentBean(mDynamicFragmentBean);
                ten.mRecyclerView.setAdapter(oneItemAdapter);

                break;
            /**
             * 一张图
             */
            case TYPE_ONE:
                OneViewHolder one = (OneViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        one.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePic = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePic != null && !usePic.isEmpty()){
                    Picasso.with(mContext).load(usePic).into(one.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userName = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userName != null && !userName.isEmpty()){
                    one.mTextViewUserName.setText(userName);
                }
                /**
                 * 日期
                 */
                String cTime = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTime != null && !cTime.isEmpty()){
                    one.mTextViewCTime.setText(cTime);
                }
                /**
                 * 信息
                 */
                String msg = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msg != null && !msg.isEmpty()){
                    one.mTextViewMsg.setText(msg);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getMaster();
                    Picasso.with(mContext).load(picLarge).into(one.mImageViewPicLarge);
                }
                /**
                 * 小图
                 */
                String pic = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (pic != null && !pic.isEmpty()){
                    Picasso.with(mContext).load(pic).into(one.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String title = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (title != null && !title.isEmpty()){
                    one.mTextViewTitle.setText(title);
                }
                break;
            /**
             * 两张图
             */
            case TYPE_TWO:
                TwoViewHolder two = (TwoViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        two.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicTwo = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicTwo != null && !usePicTwo.isEmpty()){
                    Picasso.with(mContext).load(usePicTwo).into(two.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameTwo = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameTwo != null && !userNameTwo.isEmpty()){
                    two.mTextViewUserName.setText(userNameTwo);
                }
                /**
                 * 日期
                 */
                String cTimeTwo = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeTwo != null && !cTimeTwo.isEmpty()){
                    two.mTextViewCTime.setText(cTimeTwo);
                }
                /**
                 * 信息
                 */
                String msgTwo = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgTwo != null && !msgTwo.isEmpty()){
                    two.mTextViewMsg.setText(msgTwo);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getMaster();
                    Picasso.with(mContext).load(picLarge).into(two.mImageViewPicLarge);
                    String picLarge1 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(1).getMaster();
                    Picasso.with(mContext).load(picLarge1).into(two.mImageViewPicLargeTwo);
                }
                /**
                 * 小图
                 */
                String picTwo = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picTwo != null && !picTwo.isEmpty()){
                    Picasso.with(mContext).load(picTwo).into(two.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleTwo = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleTwo != null && !titleTwo.isEmpty()){
                    two.mTextViewTitle.setText(titleTwo);
                }
                break;
            /**
             * 三张图
             */
            case TYPE_THREE:
                ThreeViewHolder three = (ThreeViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        three.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicThree = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicThree != null && !usePicThree.isEmpty()){
                    Picasso.with(mContext).load(usePicThree).into(three.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameThree = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameThree != null && !userNameThree.isEmpty()){
                    three.mTextViewUserName.setText(userNameThree);
                }
                /**
                 * 日期
                 */
                String cTimeThree = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeThree != null && !cTimeThree.isEmpty()){
                    three.mTextViewCTime.setText(cTimeThree);
                }
                /**
                 * 信息
                 */
                String msgThree = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgThree != null && !msgThree.isEmpty()){
                    three.mTextViewMsg.setText(msgThree);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getPic_large();
                    Picasso.with(mContext).load(picLarge).into(three.mImageViewPicLarge);
                    String picLarge1 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(1).getPic_large();
                    Picasso.with(mContext).load(picLarge1).into(three.mImageViewPicLargeTwo);
                    String picLarge2 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(2).getPic_large();
                    Picasso.with(mContext).load(picLarge2).into(three.mImageViewPicLargeThree);
                }
                /**
                 * 小图
                 */
                String picThree = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picThree != null && !picThree.isEmpty()){
                    Picasso.with(mContext).load(picThree).into(three.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleThree = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleThree != null && !titleThree.isEmpty()){
                    three.mTextViewTitle.setText(titleThree);
                }
                break;
            /**
             * 四张图
             */
            case TYPE_FOUR:
                FourViewHolder four = (FourViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        four.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicFour = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicFour != null && !usePicFour.isEmpty()){
                    Picasso.with(mContext).load(usePicFour).into(four.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameFour = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameFour != null && !userNameFour.isEmpty()){
                    four.mTextViewUserName.setText(userNameFour);
                }
                /**
                 * 日期
                 */
                String cTimeFour = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeFour != null && !cTimeFour.isEmpty()){
                    four.mTextViewCTime.setText(cTimeFour);
                }
                /**
                 * 信息
                 */
                String msgFour = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgFour != null && !msgFour.isEmpty()){
                    four.mTextViewMsg.setText(msgFour);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getMaster();
                    Picasso.with(mContext).load(picLarge).into(four.mImageViewPicLarge);
                    String picLarge1 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(1).getMaster();
                    Picasso.with(mContext).load(picLarge1).into(four.mImageViewPicLargeTwo);
                    String picLarge2 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(2).getMaster();
                    Picasso.with(mContext).load(picLarge2).into(four.mImageViewPicLargeThree);
                    String picLarge3 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(3).getMaster();
                    Picasso.with(mContext).load(picLarge2).into(four.mImageViewPicLargeFour);
                }
                /**
                 * 小图
                 */
                String picFour = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picFour != null && !picFour.isEmpty()){
                    Picasso.with(mContext).load(picFour).into(four.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleFour = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleFour != null && !titleFour.isEmpty()){
                    four.mTextViewTitle.setText(titleFour);
                }
                break;
            /**
             * 五张图
             */
            case TYPE_FIVE:
                FiveViewHolder five = (FiveViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        five.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicFive = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicFive != null && !usePicFive.isEmpty()){
                    Picasso.with(mContext).load(usePicFive).into(five.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameFive = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameFive != null && !userNameFive.isEmpty()){
                    five.mTextViewUserName.setText(userNameFive);
                }
                /**
                 * 日期
                 */
                String cTimeFive = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeFive != null && !cTimeFive.isEmpty()){
                    five.mTextViewCTime.setText(cTimeFive);
                }
                /**
                 * 信息
                 */
                String msgFive = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgFive != null && !msgFive.isEmpty()){
                    five.mTextViewMsg.setText(msgFive);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getMaster();
                    Picasso.with(mContext).load(picLarge).into(five.mImageViewPicLarge);
                    String picLarge1 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(1).getMaster();
                    Picasso.with(mContext).load(picLarge1).into(five.mImageViewPicLargeTwo);
                    String picLarge2 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(2).getMaster();
                    Picasso.with(mContext).load(picLarge2).into(five.mImageViewPicLargeThree);
                    String picLarge3 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(3).getMaster();
                    Picasso.with(mContext).load(picLarge3).into(five.mImageViewPicLargeFour);
                    String picLarge4 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(4).getMaster();
                    Picasso.with(mContext).load(picLarge4).into(five.mImageViewPicLargeFive);
                }
                /**
                 * 小图
                 */
                String picFive = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picFive != null && !picFive.isEmpty()){
                    Picasso.with(mContext).load(picFive).into(five.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleFive = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleFive != null && !titleFive.isEmpty()){
                    five.mTextViewTitle.setText(titleFive);
                }
                break;
            /**
             * 六张图
             */
            case TYPE_SIX:
                SixViewHolder six = (SixViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        six.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicSix = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicSix != null && !usePicSix.isEmpty()){
                    Picasso.with(mContext).load(usePicSix).into(six.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameSix = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameSix != null && !userNameSix.isEmpty()){
                    six.mTextViewUserName.setText(userNameSix);
                }
                /**
                 * 日期
                 */
                String cTimeSix = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeSix != null && !cTimeSix.isEmpty()){
                    six.mTextViewCTime.setText(cTimeSix);
                }
                /**
                 * 信息
                 */
                String msgSix = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgSix != null && !msgSix.isEmpty()){
                    six.mTextViewMsg.setText(msgSix);
                }
                /**
                 * 大图
                 */
                if (mDynamicFragmentBean.getMsg().get(position).getPiclist() != null){
                    String picLarge = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(0).getPic_large();
                    Picasso.with(mContext).load(picLarge).into(six.mImageViewPicLarge);
                    String picLarge1 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(1).getPic_large();
                    Picasso.with(mContext).load(picLarge1).into(six.mImageViewPicLargeTwo);
                    String picLarge2 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(2).getPic_large();
                    Picasso.with(mContext).load(picLarge2).into(six.mImageViewPicLargeThree);
                    String picLarge3 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(3).getPic_large();
                    Picasso.with(mContext).load(picLarge3).into(six.mImageViewPicLargeFour);
                    String picLarge4 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(4).getPic_large();
                    Picasso.with(mContext).load(picLarge4).into(six.mImageViewPicLargeFive);
                    String picLarge5 = mDynamicFragmentBean.getMsg().get(position).getPiclist().get(5).getPic_large();
                    Picasso.with(mContext).load(picLarge5).into(six.mImageViewPicLargeSix);
                }
                /**
                 * 小图
                 */
                String picSix = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picSix != null && !picSix.isEmpty()){
                    Picasso.with(mContext).load(picSix).into(six.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleSix = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleSix != null && !titleSix.isEmpty()){
                    six.mTextViewTitle.setText(titleSix);
                }
                break;
            /**
             * msg == 2
             */
            case TYPE_SEVEN:
                SevenViewHolder seven = (SevenViewHolder) holder;
                seven.mTextViewTitle.setText(mDynamicFragmentBean.getMsg().get(position).getTopic().getTopic_title());
                seven.mTextViewNums.setText(mDynamicFragmentBean.getMsg().get(position).getTopic().getNums());
                Picasso.with(mContext).load(mDynamicFragmentBean.getMsg().get(position).getTopic().getPic_750x215()).into(seven.mImageView);
                break;

            /**
             * 没有图
             */
            case TYPE_EIGHT:
                EightViewHolder eight = (EightViewHolder) holder;
                if (mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name()!=null ) {
                    String artistName = mDynamicFragmentBean.getMsg().get(position).getContent().getArtist_name();
                    if (artistName != null && !artistName.isEmpty()) {
                        eight.mTextViewArtistName.setText(artistName);
                    }
                }
                /**
                 * 用户头像
                 */
                String usePicEight = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUserpic();
                if (usePicEight != null && !usePicEight.isEmpty()){
                    Picasso.with(mContext).load(usePicEight).into(eight.mImageViewUsePic);
                }
                /**
                 * 用户名
                 */
                String userNameEight = mDynamicFragmentBean.getMsg().get(position).getAuthor().getUsername();
                if (userNameEight != null && !userNameEight.isEmpty()){
                    eight.mTextViewUserName.setText(userNameEight);
                }
                /**
                 * 日期
                 */
                String cTimeEight = mDynamicFragmentBean.getMsg().get(position).getCtime() + "";
                if (cTimeEight != null && !cTimeEight.isEmpty()){
                    eight.mTextViewCTime.setText(cTimeEight);
                }
                /**
                 * 信息
                 */
                String msgEight = mDynamicFragmentBean.getMsg().get(position).getMsg();
                if (msgEight != null && !msgEight.isEmpty()){
                    eight.mTextViewMsg.setText("aaaaa");
                    Log.d("DynamicFragmentAdapter", "aaaaaaaaaaa");
                }

                /**
                 * 小图
                 */
                String picEight = mDynamicFragmentBean.getMsg().get(position).getContent().getPic();
                if (picEight != null && !picEight.isEmpty()){
                    Picasso.with(mContext).load(picEight).into(eight.mImageViewPic);
                }
                /**
                 * 标题
                 */
                String titleEight = mDynamicFragmentBean.getMsg().get(position).getContent().getTitle();
                if (titleEight != null && !titleEight.isEmpty()){
                    eight.mTextViewTitle.setText(titleEight);
                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    /**
     * 第一个行布局
     */
    public class NineViewHolder extends RecyclerView.ViewHolder{
         ImageView mImageView;
        public NineViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.dynamic_fragment_adapter_one_item_iv);
        }
    }
    /**
     * 第二行的缓存类
     */
    public class TenViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public TenViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.dynamic_fragment_adapter_two_item_rv);
        }
    }


    /**
     * 一张图
     */
    public class OneViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public OneViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_pic_large);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dynamic_fragment_adapter_three_detail_item_artist_name);
        }
    }
    /**
     * 两张图
     */
    public class TwoViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic,mImageViewPicLargeTwo;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public TwoViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_two_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dfa_two_pic_large_one);
            mImageViewPicLargeTwo = (ImageView) itemView.findViewById(R.id.dfa_two_pic_large_two);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_two_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_two_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_two_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_two_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_two_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_two_artist_name);
        }
    }
    /**
     * 三张图
     */
    public class ThreeViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic,mImageViewPicLargeTwo,mImageViewPicLargeThree;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_three_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dfa_three_pic_large_one);
            mImageViewPicLargeTwo = (ImageView) itemView.findViewById(R.id.dfa_three_pic_large_two);
            mImageViewPicLargeThree = (ImageView) itemView.findViewById(R.id.dfa_three_pic_large_three);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_three_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_three_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_three_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_three_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_three_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_three_artist_name);
        }
    }

    /**
     * 四张图
     */
    public class FourViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic,mImageViewPicLargeTwo,mImageViewPicLargeThree,mImageViewPicLargeFour;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public FourViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_four_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dfa_four_pic_large_one);
            mImageViewPicLargeTwo = (ImageView) itemView.findViewById(R.id.dfa_four_pic_large_two);
            mImageViewPicLargeThree = (ImageView) itemView.findViewById(R.id.dfa_four_pic_large_three);
            mImageViewPicLargeFour = (ImageView) itemView.findViewById(R.id.dfa_four_pic_large_four);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_four_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_four_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_four_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_four_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_four_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_four_artist_name);
        }
    }
    /**
     * 五张图
     */
    public class FiveViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic,mImageViewPicLargeTwo,mImageViewPicLargeThree,mImageViewPicLargeFour,mImageViewPicLargeFive;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public FiveViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_five_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dfa_five_pic_large_one);
            mImageViewPicLargeTwo = (ImageView) itemView.findViewById(R.id.dfa_five_pic_large_two);
            mImageViewPicLargeThree = (ImageView) itemView.findViewById(R.id.dfa_five_pic_large_three);
            mImageViewPicLargeFour = (ImageView) itemView.findViewById(R.id.dfa_five_pic_large_four);
            mImageViewPicLargeFive = (ImageView) itemView.findViewById(R.id.dfa_five_pic_large_five);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_five_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_five_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_five_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_five_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_five_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_five_artist_name);
        }
    }
    /**
     * 六张图
     */
    public class SixViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPicLarge, mImageViewPic,mImageViewPicLargeTwo,mImageViewPicLargeThree,mImageViewPicLargeFour,mImageViewPicLargeFive,mImageViewPicLargeSix;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public SixViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_six_usepic);
            mImageViewPicLarge = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_one);
            mImageViewPicLargeTwo = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_two);
            mImageViewPicLargeThree = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_three);
            mImageViewPicLargeFour = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_four);
            mImageViewPicLargeFive = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_five);
            mImageViewPicLargeSix = (ImageView) itemView.findViewById(R.id.dfa_six_pic_large_six);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_six_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_six_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_six_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_six_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_six_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_six_artist_name);
        }
    }
    /**
     * msg == 2
     */
    public class SevenViewHolder extends RecyclerView.ViewHolder {
         ImageView mImageView;
        TextView mTextViewTitle,mTextViewNums;
        public SevenViewHolder(View itemView) {
            super(itemView);
           mImageView = (ImageView)itemView.findViewById(R.id.dfa_seven_iv);
            mTextViewTitle = (TextView)itemView.findViewById(R.id.dfa_seven_topic_title);
            mTextViewNums = (TextView)itemView.findViewById(R.id.dfa_seven_item_nums);
        }
    }
    /**
     * 没有piclist
     */
    public class EightViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageViewUsePic, mImageViewPic;
        TextView mTextViewUserName, mTextViewCTime, mTextViewMsg, mTextViewTitle, mTextViewArtistName;

        public EightViewHolder(View itemView) {
            super(itemView);
            mImageViewUsePic = (ImageView) itemView.findViewById(R.id.dfa_eight_usepic);
            mImageViewPic = (ImageView) itemView.findViewById(R.id.dfa_eight_pic);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.dfa_eight_username);
            mTextViewMsg = (TextView) itemView.findViewById(R.id.dfa_eight_msg);
            mTextViewCTime = (TextView) itemView.findViewById(R.id.dfa_eight_ctime);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.dfa_eight_title);
            mTextViewArtistName = (TextView) itemView.findViewById(R.id.dfa_eight_artist_name);
        }
    }


}
