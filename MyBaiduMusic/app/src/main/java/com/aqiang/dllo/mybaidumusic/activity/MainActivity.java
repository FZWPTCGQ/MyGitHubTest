package com.aqiang.dllo.mybaidumusic.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.adapter.DetailLeftAdapter;
import com.aqiang.dllo.mybaidumusic.adapter.SeekRV;
import com.aqiang.dllo.mybaidumusic.adapter.SeekTAdapter;
import com.aqiang.dllo.mybaidumusic.adapter.sonadapter.LikeDetailAdapter;
import com.aqiang.dllo.mybaidumusic.bean.DetailLeftBean;
import com.aqiang.dllo.mybaidumusic.bean.SeekBean;
import com.aqiang.dllo.mybaidumusic.bean.SeekTBean;
import com.aqiang.dllo.mybaidumusic.bean.sonBean.musiclistSonBean.PlayBean;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.DynamicFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.LiveFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.MineFragment;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.MusicFragment;
import com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment.SongMenuDetailFragment;
import com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment.SongMenuRecommendDetailFragment;
import com.aqiang.dllo.mybaidumusic.fragment.grandSonFragment.MusicListDetailFragment;
import com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment.MyPoint;
import com.aqiang.dllo.mybaidumusic.fragment.reusefragment.ReuseFragment;
import com.aqiang.dllo.mybaidumusic.fragment.sonfragment.LikeDetailFragment;
import com.aqiang.dllo.mybaidumusic.popTools.PopMLDA;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.DefaultLrcBuilder;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcBuilder;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcView;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.ILrcViewListener;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.LrcRow;
import com.aqiang.dllo.mybaidumusic.tool.Lrc.LrcView;
import com.aqiang.dllo.mybaidumusic.tool.blur.BlurTranformation;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.CollectionBean;

import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBPlayTools;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DBTools;
import com.aqiang.dllo.mybaidumusic.tool.service.Bean;
import com.aqiang.dllo.mybaidumusic.tool.service.MainActivityService;
import com.aqiang.dllo.mybaidumusic.tool.service.ModeBean;
import com.aqiang.dllo.mybaidumusic.tool.urlTools.Tools;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetHelper;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.NetListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;


/**
 * Created by dllo on 16/11/22.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView playIv;
    private ImageView pauseIv;
    private ImageView showDrawIv;
    private DrawerLayout drawerLayout;
    private ImageView drawerBtn;
    private TabAdapter tabAdapter;
    private List<Fragment> fragments;
    private TabLayout tab;
    private ViewPager viewPager;
    private ImageView mNextSong;
    private MainActivityService.MyBinder mMyBinder;
    private Intent intent;
    private ServiceConnection mServiceConnection;
    public static TextView mSingerTv;
    public static TextView mNameTv;

    private MainBR mMainBR;
    private ImageView mPlayBtnN;
    private ImageView mPauseBtnN;
    private LinearLayout mPlayMusicHurdle;
    private ImageView mSeekIv;
    private DrawerLayout mDrawerLayout;
    private ImageView mImageViewHead;
    /**
     * popupWindow中的布局
     */
    private LinearLayout leftLl, rightLl;
    private RelativeLayout middleLl;
    /**
     * 设置一个int型的变量判断其歌曲类型,默认是本地歌曲
     */
    private int isNetMusic = 0;
    /**
     * 当前歌曲的网址
     */
    //当前播放歌曲网址
    private static String currentUrl;
    //当前歌曲的歌手
    private static String currentSinger;
    //当前歌曲的歌名
    private static String currentTitle;
    //当前歌曲的时间
    public static Long currentTime;
    //当前歌曲类型
    private static int currentMusicType;
    /**
     * 播放榜单界面音乐
     */
    /**
     * 哈哈哈哈
     */
    //音乐Id的集合
    private static ArrayList<String> songIdList;
    //某个音乐的网址
    private String musicUrl = null;
    //播放歌曲地址的集合
    private static ArrayList<String> urlPlayDates = new ArrayList<>();
    //播放歌曲地址集合
    private static Map<Integer, String> netUrlList = new HashMap<>();
    //网络音乐实体类的集合
    private static Map<Integer, PlayBean> netDatasBean = new HashMap<>();
    //当前歌曲的位置
    private static int currentIndex = 2;
    private MediaPlayer mMediaPlayer;
    private String mPlayUrl;
    /**
     * 当前播放音乐的实体类
     */
    private PlayBean mPlayBean;
    private Long currentSongDuration;
    private Long currentSongPosition;
    private View mViewPop;
    /**
     * 播放详情页
     */
    //中间详情页
    //背景大图
    private ImageView mImageViewBg;
    private ImageView mImageViewAbove;
    private ImageView mImageViewBelow;
    private ImageView mImageViewAuthorPic;
    public static  TextView mTextViewTitle;
    public static  TextView mTextViewAuthor;
    /**
     * 按底边的minibar弹出的popwindow的内容
     */
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPagerPop;
    private List<MyPoint> mMyPoints;
    private LinearLayout mLinearLayoutPoint;
    private PopupWindow mPopupWindow;


    /**
     * 歌词相关
     */
    //自定义LrcView，用来展示歌词
    ILrcView mLrcView;
    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;
    private Button searchLrc;
    /**
     * 网络歌曲总时间
     */
    public static TextView mTextViewAllTime;
    private TextView mTextViewCurrentPosition;

    /**
     * 播放组件
     */
    private ImageView mNextSong1;
    private ImageView mPastSong;
    private ImageView mPauseSong;
    private ImageView mPlaySong;
    private ImageView mOrderPlay;
    private ImageView mSinglePlay;
    private ImageView mCirPlay;
    private ImageView mRandomPlay;
    private ImageView mImageViewDismiss;
    private SeekBar mMSeekBar;
    private ImageView mImageViewLike;
    private ImageView mImageViewDetail;

    /**
     * 底边bar相关
     */
    private View mViewPopDetail;
    private LinearLayout mLl;
    private PopupWindow mPopupWindowDetail;
    private ListView mListViewPopDetail;
    private LikeDetailAdapter mLikeDetailAdapter;
    private String mType;
    private StringBuffer mStringBuffer;
    private String mUrl;
    private List<com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.PlayBean> mList;
    private TextView mTextViewClear;
    private PopMLDA mPopMLDA;
    private FrameLayout mFrameLayout;
    private ImageView mMImageViewDownload;
    private ImageView mImageViewShare;
    private ImageView mImageViewDetail1;
    private Button mBtnClose;
    //向服务中传当前各种情况的各种位置
    private EventBus mEventBus;
    //向服务中传当前播放模式
    private EventBus mMEventBusMode;
    /**
     * 播放模式 :
     * @return
     */
    private ModeBean mModeBean = new ModeBean();

    /**
     * 定义一个播放模式的量
     * 默认是循环播放
     */
    private static int PLAY_MODE = Tools.SONG_PLAY_MODE_CYCLE;
    public static ImageView mImageViewI;
    public static ImageView mImageViewII;


    private View mViewDrawer;
    private RelativeLayout mRvDrawer;

    /**
     * 抽屉的内容
     * @return
     */
    public static TextView userNameTv;
    public static ImageView userPicIv;
    private static Button unRBtn;
    private PlatformActionListener platformActionListener;
    public static ImageView leftLlAuthorIv;
    public static TextView leftLlAuthor;
    public static TextView leftLlTitle;
    private ListView mLeftLlListView;
    private View mViewHeader;
    private String mName;
    private String mIcon;
    private View mViewSeek;
    private RecyclerView mRecyclerViewSeek;
    private SeekRV mSeekRV;
    private SeekBean mSeekBean;
    private ImageView mSeekIv1;
    private EditText mEditTextContent;
    private String mString;
    private TextView mTextViewHotSeek;
    private ListView mListViewSeek;
    private SeekTAdapter mSeekTAdapter;

    @Override
    protected int setLayout() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        /**
         * 注册从服务传来的EventBus
         */
        EventBus.getDefault().register(this);
        /**
         * 当前歌曲的位置记录传给服务
         */
        mEventBus = EventBus.getDefault();
        /**
         * 播放模式的传值
         */
        mMEventBusMode = EventBus.getDefault();
        /**
         * 启动授权
         */
        ShareSDK.initSDK(this);
        /**
         * 初始化布局控件
         */
        intent = new Intent(this, MainActivityService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                mMyBinder = (MainActivityService.MyBinder) service;
                /**
                 * 开启
                 */
                new Thread(new MusicRunnable()).start();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        addViews();
        startService(intent);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

    }


    private void addViews() {
        View view = LayoutInflater.from(this).inflate(R.layout.notification_item_layout, null);
        mMyPoints = new ArrayList<>();
        mList = new ArrayList<>();
        mPlayBtnN = (ImageView) view.findViewById(R.id.notification_item_layout_play);
        mPauseBtnN = (ImageView) view.findViewById(R.id.notification_item_layout_pause);
        mImageViewHead = (ImageView) findViewById(R.id.main_foot_circle_iv);
        tab = (TabLayout) findViewById(R.id.main_tab);
        viewPager = (ViewPager) findViewById(R.id.main_vp);
        mSeekIv = (ImageView) findViewById(R.id.main_activity_seek);
        showDrawIv = (ImageView) findViewById(R.id.main_show_draw_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.root_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.root_drawer_seek);
        songIdList = new ArrayList<>();
        drawerBtn = (ImageView) findViewById(R.id.drawer_btn);
        fragments = new ArrayList<>();
        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        /**
         * 播放音乐组件初始化
         */
        playIv = (ImageView) findViewById(R.id.song_play_iv);
        pauseIv = (ImageView) findViewById(R.id.song_pause_iv);

        mNextSong = (ImageView) findViewById(R.id.main_foot_next_iv);
        mSingerTv = (TextView) findViewById(R.id.main_foot_singer_tv);
        mNameTv = (TextView) findViewById(R.id.main_foot_name_tv);
        mPlayMusicHurdle = (LinearLayout) findViewById(R.id.activity_main_play_music_hurdle_ll);
        /**
         * MainFragment组件
         */
        //popWindow的根视图
        mViewPop = LayoutInflater.from(this).inflate(R.layout.fragment_main, null);
        mViewPagerPop = (ViewPager) mViewPop.findViewById(R.id.main_fragment_vp);
        mLinearLayoutPoint = (LinearLayout) mViewPop.findViewById(R.id.main_fragment_ll);
        mImageViewDismiss = (ImageView) mViewPop.findViewById(R.id.song_page_return);
        mNextSong1 = (ImageView) mViewPop.findViewById(R.id.main_fragment_button_next_iv);
        mPastSong = (ImageView) mViewPop.findViewById(R.id.main_fragment_button_pre_iv);
        mPauseSong = (ImageView) mViewPop.findViewById(R.id.main_fragment_button_pause_iv);
        mPlaySong = (ImageView) mViewPop.findViewById(R.id.main_fragment_button_play_iv);
        mCirPlay = (ImageView) mViewPop.findViewById(R.id.main_fragment_shunxu_play_iv);
        mMSeekBar = (SeekBar) mViewPop.findViewById(R.id.main_fragment_seekbar);
        mTextViewAllTime = (TextView) mViewPop.findViewById(R.id.main_fragment_all_time_tv);
        mTextViewCurrentPosition = (TextView) mViewPop.findViewById(R.id.main_fragment_current_time_tv);
        mImageViewLike = (ImageView) mViewPop.findViewById(R.id.main_fragment_we_like);
        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal_new);
        mMImageViewDownload = (ImageView) mViewPop.findViewById(R.id.main_fragment_download);
        mImageViewShare = (ImageView) mViewPop.findViewById(R.id.main_fragment_share);
        mImageViewDetail1 = (ImageView) mViewPop.findViewById(R.id.main_fragment_button_list_iv);
        /**
         * 初始化popupWindow三个布局
         */
        leftLl = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.fragment_first, null);
        mViewHeader = LayoutInflater.from(MyApp.getmContext()).inflate(R.layout.fragment_first_detail_header_view,null);
        leftLlAuthorIv = (ImageView)mViewHeader.findViewById(R.id.fragment_first_author_pic);
        leftLlAuthor = (TextView)mViewHeader.findViewById(R.id.fragment_first_author);
        leftLlTitle = (TextView)mViewHeader.findViewById(R.id.fragment_first_title);
        mLeftLlListView = (ListView)leftLl.findViewById(R.id.fragment_first_lv);
        showLeftList();


        middleLl = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.fragment_three, null);
        rightLl = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.fragment_two, null);
        /**
         * 中间详情页组件初始化
         */
        mImageViewBg = (ImageView) middleLl.findViewById(R.id.background_ig);
        mImageViewAbove = (ImageView) middleLl.findViewById(R.id.middle_above_pic);
        mImageViewBelow = (ImageView) middleLl.findViewById(R.id.middle_below_pic);
        mImageViewAuthorPic = (ImageView) middleLl.findViewById(R.id.middle_author_pic);
        mTextViewTitle = (TextView) middleLl.findViewById(R.id.middle_title_tv);
        mTextViewAuthor = (TextView) middleLl.findViewById(R.id.middle_author_tv);
        mImageViewI = (ImageView)middleLl.findViewById(R.id.middle_iv1);
        mImageViewII = (ImageView)middleLl.findViewById(R.id.middle_iv2);
        /**
         * 搜索歌词页面初始化
         */
        searchLrc = (Button) rightLl.findViewById(R.id.right_begin_search_lrc_btn);
        mLrcView = (LrcView) rightLl.findViewById(R.id.right_lrcView);
        /**
         * 底边的详情bar弹出的popWindow
         */
        mViewPopDetail = LayoutInflater.from(this).inflate(R.layout.pop_detail_item, null);
        mListViewPopDetail = (ListView) mViewPopDetail.findViewById(R.id.pop_detail_item_lv);
        mTextViewClear = (TextView) mViewPopDetail.findViewById(R.id.pop_detail_item_clear_tv);
        mBtnClose = (Button) mViewPopDetail.findViewById(R.id.pop_detail_item_btn_close);
        /**
         * 底边的bar
         */
        mImageViewDetail = (ImageView) findViewById(R.id.main_foot_detail_iv);
        mLl = (LinearLayout) mViewPopDetail.findViewById(R.id.pop_detail_item_ll);
        initPopWindowDatas();
        showPopWindow();

       // mViewDrawer = LayoutInflater.from(this).inflate(R.layout.drawer_item_include,null);
        /**
         * 抽屉内容
         */
        mRvDrawer = (RelativeLayout)findViewById(R.id.include_layout_two_line);
        userNameTv = (TextView) findViewById(R.id.auto_login_btn);
        userPicIv = (ImageView)findViewById(R.id.drawer_item_include_circle);
        unRBtn = (Button)findViewById(R.id.include_layout_ten_line_btn);

        mViewSeek = LayoutInflater.from(MyApp.getmContext()).inflate(R.layout.seek_detail_page_content,null);
//        mRecyclerViewSeek = (RecyclerView) mViewSeek.findViewById(R.id.seek_detail_page_content_rv);
        mSeekIv1 = (ImageView)mViewSeek.findViewById(R.id.seek_detail_item_iv);
        mEditTextContent = (EditText)mViewSeek.findViewById(R.id.seek_detail_item_et);
        mString = mEditTextContent.getText().toString();
        Log.d("爱上就直接离开", mString);
        mTextViewHotSeek = (TextView)mViewSeek.findViewById(R.id.seek_detail_page_content_hot_seek_tv);
        mListViewSeek = (ListView)mViewSeek.findViewById(R.id.seek_detail_item_lv);
        mSeekTAdapter = new SeekTAdapter();
        if (!mString.isEmpty()){
            mListViewSeek.setVisibility(View.VISIBLE);
            mTextViewHotSeek.setVisibility(View.GONE);
            StringBuffer stringBuffer = new StringBuffer(Tools.SEEK_DETAIL_URL);
            int a = stringBuffer.indexOf("参数",0);
            String newUrl = stringBuffer.replace(a,a+2,mString).toString();
            Log.d("爱上就直接离开", newUrl);
            NetHelper.MyRequest(newUrl, SeekTBean.class, new NetListener<SeekTBean>() {
                @Override
                public void successListener(SeekTBean response) {
                    SeekTBean seekTBean = response;
                    mSeekTAdapter.setSeekTBean(seekTBean);
                    mListViewSeek.setAdapter(mSeekTAdapter);
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
    }

    private void showLeftList() {
        StringBuffer stringBuffer = new StringBuffer(Tools.LEFT_DETAIL_URL);
        int start = stringBuffer.indexOf("参数",0);
        Log.d("方法即可", DBPlayTools.getInstance().queryAll().get(currentIndex).getUrl());
        if (DBPlayTools.getInstance().queryAll().get(currentIndex).getUrl() != null ){
            final String result = stringBuffer.replace(start,start + 2,DBPlayTools.getInstance().queryAll().get(currentIndex).getUrl()).toString();

            Log.d("家属可好了房价开始打", result);
            NetHelper.MyRequest(result, DetailLeftBean.class, new NetListener<DetailLeftBean>() {
                @Override
                public void successListener(DetailLeftBean response) {
                    DetailLeftBean detailLeftBean = response;
                        if (!result.equals("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.5.1&channel=1426d&operator=3&method=baidu.ting.ugcmsg.getNums&param=lU%2Fpn8QKAhIWe%2FGqgDuzCA8T1%2BEfB5quKHc1D2QStXrnv4TicueTtvwHT9HRr%2F8N99PvhlKZ3nUXSOVwc7dhiuMYyiV0CIm23Wa5FDbaeBU%3D&timestamp=1481619904&sign=65d83c129d5c42695d440fd88a9c3364")){
                            DetailLeftAdapter detailLeftAdapter = new DetailLeftAdapter(getApplicationContext());
                            detailLeftAdapter.setDetailLeftBean(detailLeftBean);
                            mLeftLlListView.setAdapter(detailLeftAdapter);
                            mLeftLlListView.addHeaderView(mViewHeader);
                        }else {
                            Toast.makeText(MainActivity.this, "没有相似歌曲", Toast.LENGTH_SHORT).show();

                        }


                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }


    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 101) {
                int currentPosition = msg.arg1;
                int musicDuration = msg.arg2;
                mMSeekBar.setProgress(currentPosition);
                mMSeekBar.setMax(musicDuration);
                /**
                 * 从小向大转
                 */
                mTextViewCurrentPosition.setText(syncDuration(currentPosition));
            }
            return false;
        }
    });

    @Override
    protected void initDatas() {
        /**
         * 添加数据
         */
        addDatas();
        /**
         * 监听事件
         */
        addListener();
        /**
         * 注册广播接受者
         */
        registerBR();

        isLogin();


    }

    private void isLogin() {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);

        PlatformDb platformDb = platform.getDb();
        mName = platformDb.getUserName();
        mIcon = platformDb.getUserIcon();
        if (!TextUtils.isEmpty(mName)) {
            userNameTv.setText(mName);
            Picasso.with(MyApp.getmContext()).load(mIcon).into(userPicIv);
        }
    }


    private void registerBR() {
        mMainBR = new MainBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Tools.ACTION_MAINBR);
        intentFilter.addAction(Tools.ACTION_MP3);
        intentFilter.addAction(Tools.ACTION_REPLACE);
        registerReceiver(mMainBR, intentFilter);


    }

    private void addDatas() {
        fragments.add(new MineFragment());
        fragments.add(new MusicFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());
        viewPager.setAdapter(tabAdapter);
        tab.setupWithViewPager(viewPager);
        /**
         * 设置下划线颜色
         */
        tab.setSelectedTabIndicatorColor(0x00000000);
        /**
         * 选中与被选中
         */
        tab.setTabTextColors(0x99ffffff, Color.rgb(0xff, 0xff, 0xff));


    }

    private void addListener() {
        playIv.setOnClickListener(this);
        pauseIv.setOnClickListener(this);
        showDrawIv.setOnClickListener(this);
        drawerBtn.setOnClickListener(this);
        mNextSong.setOnClickListener(this);
        mPlayMusicHurdle.setOnClickListener(this);
        mSeekIv.setOnClickListener(this);


        /**
         * 中间详情页播放组件点击事件
         */
        mImageViewDismiss.setOnClickListener(this);
        mNextSong1.setOnClickListener(this);
        mPastSong.setOnClickListener(this);
        mPauseSong.setOnClickListener(this);
        mPlaySong.setOnClickListener(this);
        mCirPlay.setOnClickListener(this);
        mMSeekBar.setOnSeekBarChangeListener(this);
        mImageViewLike.setOnClickListener(this);
        mMImageViewDownload.setOnClickListener(this);
        mImageViewShare.setOnClickListener(this);
        mImageViewDetail1.setOnClickListener(this);
        /**
         * 中间详情页背景图点击事件
         */
        mImageViewBg.setOnClickListener(this);
        /**
         * 右边歌词栏详情页
         */
        searchLrc.setOnClickListener(this);
        /**
         * 底边详情页
         */
        mImageViewDetail.setOnClickListener(this);
        mLl.setOnClickListener(this);
        mTextViewClear.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);

        mRvDrawer.setOnClickListener(this);
        unRBtn.setOnClickListener(this);

        /**
         * 搜索页的搜索键
         */
        mSeekIv1.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Toast.makeText(this, "已经退出", Toast.LENGTH_SHORT).show();
            mName= "";
            mIcon = "";
        }

        if (data!= null && requestCode == 1 && resultCode == 0){
            // 登录成功
           mName =  data.getStringExtra("name");
            mIcon =  data.getStringExtra("icon");
            mNameTv.setText(mName);
            Picasso.with(MyApp.getmContext()).load(mIcon).into(userPicIv);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            /**
             * 播放音乐按钮
             */
            case R.id.song_play_iv:
                pauseIv.setVisibility(View.VISIBLE);
                playIv.setVisibility(View.INVISIBLE);
                if (mMyBinder != null) {
                    mMyBinder.pauseMusic();
                }
                break;
            /**
             * 暂停音乐按钮
             */
            case R.id.song_pause_iv:
                playIv.setVisibility(View.VISIBLE);
                pauseIv.setVisibility(View.INVISIBLE);
                if (mMyBinder != null) {
                    mMyBinder.pauseMusic();
                }
                break;
            /**
             * 播放下一曲
             */
            case R.id.main_foot_next_iv:
                pauseIv.setVisibility(View.INVISIBLE);
                playIv.setVisibility(View.VISIBLE);
                Toast.makeText(this, "下一曲", Toast.LENGTH_SHORT).show();
                if (mMyBinder != null) {
                    Log.d("MainAa", isNetMusic + "");
                    if (isNetMusic == 1) {
                        nextNetMusic();

                    } else {
                        mMyBinder.nextMusic();
                    }
                }
                break;
            /**
             * 弹出抽屉按钮左边图案
             */
            case R.id.main_show_draw_iv:
                drawerLayout.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(Gravity.RIGHT);
                Animation in = AnimationUtils.loadAnimation(this, R.anim.in);
                drawerLayout.setAnimation(in);
                drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        drawerLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });

                break;
            /**
             * 关闭抽屉左边图案
             */
            case R.id.drawer_btn:
                Animation out = AnimationUtils.loadAnimation(this, R.anim.out);
                drawerLayout.setAnimation(out);
                drawerLayout.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.RIGHT);

                break;
            /**
             * 下边音乐播放栏添加pop
             */
            case R.id.activity_main_play_music_hurdle_ll:
                /**
                 * 初始化popwindow数据
                 */

                /**
                 * 显示popWindow
                 */


                if (!mPopupWindow.isShowing()){

                    mPopupWindow.showAtLocation(mPlayMusicHurdle, Gravity.BOTTOM, 0, 0);
                }

                break;
            /**
             * 打开搜索栏的抽屉
             */
            case R.id.main_activity_seek:
                mDrawerLayout.setVisibility(View.VISIBLE);
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                Animation inSeek = AnimationUtils.loadAnimation(this, R.anim.in);
                mDrawerLayout.setAnimation(inSeek);
                mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        mDrawerLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });
                break;
            /**
             * MainFragment组件
             */
            case R.id.main_fragment_button_next_iv:
                if (isNetMusic == 1) {

                    nextNetMusic();
                } else {
                    mMyBinder.playMusic();
                }
                mPauseSong.setVisibility(View.INVISIBLE);
                mPlaySong.setVisibility(View.VISIBLE);
                Toast.makeText(this, "下一曲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_fragment_button_pre_iv:
                if (isNetMusic == 1) {
                    pastNetMusic();
                } else {
                    mMyBinder.playMusic();
                }
                mPauseSong.setVisibility(View.INVISIBLE);
                mPlaySong.setVisibility(View.VISIBLE);

                Toast.makeText(this, "上一曲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_fragment_button_pause_iv:
                if (mMyBinder != null) {
                    mPauseSong.setVisibility(View.INVISIBLE);
                    mPlaySong.setVisibility(View.VISIBLE);
                    mMyBinder.pauseMusic();
                }

                break;
            case R.id.main_fragment_button_play_iv:
                if (mMyBinder != null) {
                    mPlaySong.setVisibility(View.INVISIBLE);
                    mPauseSong.setVisibility(View.VISIBLE);
                    mMyBinder.pauseMusic();
                }

                break;
            case R.id.main_fragment_shunxu_play_iv:
                switch (PLAY_MODE){
                    case Tools.SONG_PLAY_MODE_CYCLE:
                        /**
                         * 默认循环列表开启
                         */
                        mCirPlay.setImageResource(R.mipmap.bt_playpage_loop_normal_new);
                        Toast.makeText(this, "默认循环列表开启", Toast.LENGTH_SHORT).show();
                        PLAY_MODE = Tools.SONG_PLAY_MODE_ORDER;
                        break;
                    case Tools.SONG_PLAY_MODE_ORDER:
                        /**
                         * 循环列表一次开启
                         */
                        mCirPlay.setImageResource(R.mipmap.bt_playpage_roundsingle_normal_new);
                        Toast.makeText(this, "单曲循环一次开启", Toast.LENGTH_SHORT).show();
                        PLAY_MODE = Tools.SONG_PLAY_MODE_SINGLE;
                        break;
                    case Tools.SONG_PLAY_MODE_SINGLE:
                        mCirPlay.setImageResource(R.mipmap.bt_playpage_random_normal_new);
                        Toast.makeText(this, "随机播放已开启", Toast.LENGTH_SHORT).show();
                        PLAY_MODE = Tools.SONG_PLAY_MODE_RANDOM;
                        break;
                    case Tools.SONG_PLAY_MODE_RANDOM:
                        mCirPlay.setImageResource(R.mipmap.bt_playpage_loop_normal_new);
                        Toast.makeText(this, "列表循环播放已开启", Toast.LENGTH_SHORT).show();
                        PLAY_MODE = Tools.SONG_PLAY_MODE_CYCLE;
                        break;
                }
                /**
                 * 利用eventBus获取播放模式
                 */
                mModeBean.setMODE(PLAY_MODE);
                mMEventBusMode.post(mModeBean);
                break;
            case R.id.song_page_return:
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            //中间详情页背景图
            case R.id.background_ig:
                Toast.makeText(this, "背景图被点击了", Toast.LENGTH_SHORT).show();
                break;
            //开始搜索歌词
            case R.id.right_begin_search_lrc_btn:

                searchLrc.setVisibility(View.GONE);
                if (mPlayBean.getSonginfo() != null) {
                    getLrc(mPlayBean.getSonginfo().getLrclink());
                } else {
                    searchLrc.setText("没有搜索到歌词");
                }
                Log.d("dgkhjkdfghsdjkl", mPlayBean.getSonginfo().getLrclink());
                break;
            //收藏功能
            case R.id.main_fragment_we_like:
                /**
                 * 收藏功能
                 */
                collectionFunction();
                break;
            //底边bar详情
            case R.id.main_foot_detail_iv:
                Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
                showDetailPopupWindow();
                if (!mPopupWindowDetail.isShowing()) {
                    mPopupWindowDetail.showAtLocation(mLl, Gravity.CENTER, 0, 500);
                } else {
                    mPopupWindowDetail.dismiss();
                }

                mPopMLDA = new PopMLDA(getApplicationContext());
                mList = DBPlayTools.getInstance().queryAll();
                Log.d("fs87f9", mList.size() + "");
                mPopMLDA.setList(mList);
                mListViewPopDetail.setAdapter(mPopMLDA);
                /**
                 * 长按删除行布局
                 */
                mListViewPopDetail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("test", "p" + position);
                        mPopMLDA.removeBean(position);
                        DBPlayTools.getInstance().deletePositionBean(mList.get(position).getTitle());
                        return false;
                    }
                });
                mListViewPopDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });

                break;
            case R.id.pop_detail_item_ll:
                mPopupWindowDetail.dismiss();
                break;
            case R.id.pop_detail_item_clear_tv:
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                if (DBPlayTools.getInstance().queryAll().size() > 0) {
                    DBPlayTools.getInstance().deleteAll();
                }
                mPopupWindowDetail.dismiss();
                break;
            case R.id.main_fragment_download:
                Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
                downloadMethod();
                break;
            case R.id.main_fragment_share:
                /**
                 * 展示分享
                 */
                showShare();
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_fragment_button_list_iv:

                /**
                 * 弹出的细节对话框
                 */
                Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
                showDetailPopupWindow();
                if (!mPopupWindowDetail.isShowing()) {
                    mPopupWindowDetail.showAtLocation(mLl, Gravity.CENTER, 0, 500);
                } else {
                    mPopupWindowDetail.dismiss();
                }

                mPopMLDA = new PopMLDA(getApplicationContext());
                mList = DBPlayTools.getInstance().queryAll();
                Log.d("fs87f9", mList.size() + "");
                mPopMLDA.setList(mList);
                mListViewPopDetail.setAdapter(mPopMLDA);
                /**
                 * 长按删除行布局
                 */
                mListViewPopDetail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("test", "p" + position);
                        mPopMLDA.removeBean(position);
                        DBPlayTools.getInstance().deletePositionBean(mList.get(position).getTitle());
                        return false;
                    }
                });
                break;
            case R.id.pop_detail_item_btn_close:
                mPopupWindowDetail.dismiss();
                break;
            case R.id.include_layout_two_line:
                Intent intent = new Intent(this,ResigsterActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.include_layout_ten_line_btn:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
                    userNameTv.setText("用户名");
                    userPicIv.setImageResource(R.mipmap.ic_launcher);
                }
                else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                // isValid和removeAccount不开启线程，会直接返回。
                // qq.removeAccount(true);
                
                setResult(-1);
                break;
            case R.id.seek_detail_item_iv:

                break;

        }



    }

    /**
     * 显示分享
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
       //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(currentTitle);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("这是条文本");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(currentSinger);
       // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
       //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
         // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("这是条文本");
       // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这是条文本");
       // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(currentUrl);
        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 下载方法
     */
    private void downloadMethod() {

    }

    /**
     * 点击底边bar的详情弹出的popupWindow
     */
    private void showDetailPopupWindow() {
        mPopupWindowDetail = new PopupWindow(mViewPopDetail);
        mPopupWindowDetail.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindowDetail.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindowDetail.setAnimationStyle(R.style.pop_anim_style);
        mPopupWindowDetail.setFocusable(true);
        mLl.setSystemUiVisibility(
                mViewPopDetail.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        mPopupWindowDetail.setContentView(mViewPopDetail);
    }


    /**
     * 收藏功能方法
     */
    private void collectionFunction() {
        /**
         * 首先判断数据库中是否有该歌曲
         */
        Log.d("音乐位置1", "currentIndex:" + currentIndex);
        Log.d("音乐网址2", currentUrl + "-------");
        if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
            /**
             * 如果有的话,当点击该按钮的时候删除该条数据
             */
            DBTools.getInstance().deleteByTitle(currentTitle);
            mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal_new);
            /**
             * 这是验证是否取消收藏成功
             */
            if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                Toast.makeText(this, "取消收藏失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                /**
                 * 当取消了收藏之后切换图片
                 */
                mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal_new);
            }
        } else {
            /**
             * 如果没有的话,那么这次的点击就变成了喜欢,则向数据库中添加数据,首先切换图片
             */
            mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl_new);
            /**
             * 将当前获取到的各种信息存入数据库
             */
            DBTools.getInstance().insertCollectionBean(new CollectionBean(null, currentTitle, currentSinger, currentMusicType, currentUrl, currentTime));
            /**
             * 验证到底是否存入到了数据库
             */
            if (DBTools.getInstance().isHaveTheTitle(currentUrl)) {
                /**
                 * 如果存到了
                 */
                Toast.makeText(this, "收藏失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        }
        Log.d("486746sf", "DBTools.getInstance().queryAll().size():" + DBTools.getInstance().queryAll().size());
    }

    /**
     * 展示popWindow内容
     *
     *
     */
    private void showPopWindow() {
        mPopupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        /**
         *  View.SYSTEM_UI_FLAG_HIDE_NAVIGATION:消失之后再也显示不出来装天蓝
         *  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION 隐藏下边home键的东西
         *  | View.SYSTEM_UI_FLAG_FULLSCREEN 充满全屏
         */
        mViewPagerPop.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        mPopupWindow.setContentView(mViewPop);
        mPopupWindow.setFocusable(true);
        /**
         * 在底部显示
         */


    }


    /**
     * 初始化popWindow内容
     */
    private void initPopWindowDatas() {
        List<View> linearLayouts = new ArrayList<>();
        linearLayouts.add(leftLl);
        linearLayouts.add(middleLl);
        linearLayouts.add(rightLl);
        mViewPagerAdapter = new ViewPagerAdapter(linearLayouts);
        mViewPagerPop.setAdapter(mViewPagerAdapter);
        initPoints();
        /**
         * 首次进入ViewPager显示第几页
         */
        mViewPagerPop.setCurrentItem(1);
        mViewPagerPop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPage = position % mViewPagerAdapter.getCount();
                for (MyPoint point : mMyPoints) {
                    point.setSelected(false);
                }
                mMyPoints.get(currentPage).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * seekBar滑动监听事件
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (mMyBinder != null) {
                mMyBinder.getGoAndBack(progress);
                mMSeekBar.setProgress(progress);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mMyBinder != null) {
            mMyBinder.getGoAndBack(seekBar.getProgress());
        }
    }

    /**
     * 歌词同步
     *
     * @param lrclink
     */
    private void getLrc(String lrclink) {
        NetHelper.MyRequest(lrclink, new NetListener<String>() {
            @Override
            public void successListener(String response) {
                //解决中文乱码问题
                String praseResult = null;
                try {
                    if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(response)) {
                        praseResult = new String(response.getBytes("ISO8859-1"), "utf-8"); // 解决中文乱码问题
                    } else {
                        praseResult = response;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String lrc = praseResult;

                //解析歌词构造器
                ILrcBuilder builder = new DefaultLrcBuilder();
                //解析歌词返回LrcRow集合
                List<LrcRow> rows = builder.getLrcRows(lrc);
                if (rows.size() > 0) {
                    //将得到的歌词集合传给mLrcView用来展示
                    mLrcView.setLrc(rows);
                    //开始播放歌曲并同步展示歌词
                    beginLrcPlay();

                    //设置自定义的LrcView上下拖动歌词时监听
                    mLrcView.setListener(new ILrcViewListener() {
                        //当歌词被用户上下拖动的时候回调该方法,从高亮的那一句歌词开始播放
                        public void onLrcSeeked(int newPosition, LrcRow row) {
                            //if (mPlayer != null) {
                            //mPlayer.seekTo((int) row.time);
                            mMyBinder.getGoAndBack((int) row.time);
                            //}
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "歌词没有解析出来", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay() {
        try {
            if (mTimer == null) {
                mTimer = new Timer();
                mTask = new LrcTask();
                mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
            }
            mMyBinder.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stopLrcPlay();
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止展示歌曲
     */
    public void stopLrcPlay() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * 展示歌曲的定时任务
     */
    class LrcTask extends TimerTask {
        @Override
        public void run() {
            //获取歌曲播放的位置
            final long timePassed = mMyBinder.getMediaPlayer().getCurrentPosition();
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    //滚动歌词
                    mLrcView.seekLrcToTime(timePassed);
                }
            });

        }
    }

    /**
     * tabViewPager适配器
     */
    public class TabAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private List<String> lists;

        public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
            lists = new ArrayList<>();
            lists.add("我的");
            lists.add("音乐");
            lists.add("动态");
            lists.add("直播");
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
            return lists.get(position);
        }
    }

    /**
     * 播放详情页上面的加点
     */
    private void initPoints() {
        mMyPoints = new ArrayList<>();
        /**
         * 防止下次再进入的话点重叠
         */
        if (mLinearLayoutPoint.getChildCount() != 0) {
            mLinearLayoutPoint.removeAllViews();
        }
        for (int i = 0; i < mViewPagerAdapter.getCount(); i++) {
            MyPoint myPoint = new MyPoint(this);
            if (i == 1) {
                myPoint.setSelected(true);
            }
            mMyPoints.add(myPoint);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            mLinearLayoutPoint.addView(myPoint, params);
        }
    }
   @Subscribe(threadMode = ThreadMode.MAIN)
   public void getPosition(Bean bean){
       currentIndex = bean.getPostion();
   }
    /**
     * 播放网络歌曲上一首
     */
    private void pastNetMusic() {

        /**
         * 下次重新搜索
         */
        mLrcView.setLrc(null);
        searchLrc.setVisibility(View.VISIBLE);
        /**
         * urlPlayDatas是歌曲地址的集合
         */
        currentIndex--;
        Log.d("abcde", currentIndex + "");
        if (currentIndex < 0) {
            Toast.makeText(this, "前面没有歌了", Toast.LENGTH_SHORT).show();
        } else {
            musicUrl = Tools.PLAY_MUSIC_BEFORE + songIdList.get(currentIndex) + Tools.PLAY_MUSIC_AFTER;
            NetHelper.MyRequest(musicUrl, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    /**
                     * 解析之后的网址最前边有个大括号,后边有个分号和大括号,所以需要格式化
                     */
                    String newStr = response.substring(1, response.length() - 2);
                    /**
                     * 初始化gson类
                     */
                    Gson gson = new Gson();
                    /**
                     * 利用gson解析来获取当前播放音乐的实体类,网址用格式化之后的标准网址
                     */
                    mPlayBean = gson.fromJson(newStr, PlayBean.class);

                    /**
                     * 下面的minibar
                     */
                    String title = mPlayBean.getSonginfo().getTitle();
                    String author = mPlayBean.getSonginfo().getAuthor();
                    mSingerTv.setText(title);
                    mNameTv.setText(author);
                    /**
                     * 播放详情页内容
                     */
                    mTextViewTitle.setText(title);
                    mTextViewAuthor.setText(author);
                    String picAuthor = mPlayBean.getSonginfo().getPic_big();
                    Glide.with(getApplicationContext()).load(picAuthor).into(mImageViewAuthorPic);

                    /**
                     * pop详情页左边主要内容
                     */
                    /**
                     * 详情页左边页面的信息
                     */
                    leftLlTitle.setText("歌手 " + ": " + title);
                    leftLlAuthor.setText("专辑 " + ": " + author);
                    Picasso.with(getApplicationContext()).load(picAuthor).into(leftLlAuthorIv);

                    String picBig = mPlayBean.getSonginfo().getPic_premium();

                    /**
                     * 获取播放音乐的网址
                     */
                    String playUrl = mPlayBean.getBitrate().getFile_link();
                    /**
                     * 获取歌手头像
                     */
                    String pic = mPlayBean.getSonginfo().getPic_small();
                    Log.d("34353453", "pic");
                    Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
                    /**
                     * 将当前歌曲网址以key-value形式存入map集合中
                     */
                    netUrlList.put(currentIndex, playUrl);
                    /**
                     * 将当前歌曲的实体类以key-value形式存入map集合中
                     */
                    netDatasBean.put(currentIndex, mPlayBean);
                    /**
                     * 清除
                     */
                    mMyBinder.clearDatas();
                    /**
                     * 将当前歌曲的网址设置给服务,调用服务的方法
                     */
                    mMyBinder.setNetDatas(playUrl);
                    /**
                     * 设置当前的位置
                     */
                    mMyBinder.setCurrentIndex(0);
                    /**
                     * 设置当前歌曲的类型
                     */
                    isNetMusic = 1;
                    /**
                     * 从实体类中获取当期歌曲的网址
                     */
                    currentUrl = netDatasBean.get(currentIndex).getBitrate().getFile_link();
                    currentSinger = netDatasBean.get(currentIndex).getSonginfo().getAuthor();
                    currentTitle = netDatasBean.get(currentIndex).getSonginfo().getTitle();
                    currentTime = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                    currentMusicType = 1;
                    /**
                     * 利用EventBus传上一曲的位置
                     */
                    Bean bean = new Bean(currentIndex);
                    mEventBus.post(bean);
                    /**
                     * 格式化总时间
                     */
                    mTextViewAllTime.setText(syncDuration(netDatasBean.get(currentIndex).getBitrate().getFile_duration() * 1000) + "");
                    playNetMusic();
                    Log.d("音乐位置1", "currentIndex:" + currentIndex);
                    Log.d("音乐网址1", currentUrl + "-------");
                    showLeftList();
                    if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl);
                    } else {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal);
                    }


                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
    }

    /**
     * 播放网络歌曲下一首
     */
    private void nextNetMusic() {

        mLrcView.setLrc(null);
        searchLrc.setVisibility(View.VISIBLE);
        urlPlayDates.clear();
        Log.d("MainA", netDatasBean.size() + "");
        currentIndex++;
        Log.d("abcde", currentIndex + "");
        Log.d("MainAa", currentIndex + "");
        if (currentIndex == urlPlayDates.size()) {
            currentIndex = 0;
        }
        musicUrl = Tools.PLAY_MUSIC_BEFORE + songIdList.get(currentIndex) + Tools.PLAY_MUSIC_AFTER;
        Log.d("MainAaaaaa", musicUrl);
        Log.d("MainAa", songIdList.get(currentIndex));
        NetHelper.MyRequest(musicUrl, new NetListener<String>() {
            @Override
            public void successListener(String response) {
                /**
                 * 将请求结果格式化
                 */
                Log.d("MainAa", response);
                String newUrl = response.substring(1, response.length() - 2);
                /**
                 * 初始化gson
                 */
                Gson gson = new Gson();
                mPlayBean = gson.fromJson(newUrl, PlayBean.class);
                /**
                 * 获取歌曲信息
                 */
                String pic = mPlayBean.getSonginfo().getPic_small();
                Log.d("34353453", "pic");
                Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
                String title = mPlayBean.getSonginfo().getTitle();
                String author = mPlayBean.getSonginfo().getAuthor();
                mSingerTv.setText(title);
                mNameTv.setText(author);


                mTextViewTitle.setText(title);
                mTextViewAuthor.setText(author);
//                SharedPreferences sharedPreferences = getSharedPreferences("method",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("title",title);
//                editor.putString("author",author);
//                editor.putBoolean("isChecked",true);

                String playUrl = mPlayBean.getBitrate().getFile_link();


                /**
                 * 播放详情页内容
                 */
                leftLlTitle.setText("歌手 " + ": " + title);
                leftLlAuthor.setText("专辑 " + ": " + author);
                String picAuthor = mPlayBean.getSonginfo().getPic_big();
                Glide.with(getApplicationContext()).load(picAuthor).into(mImageViewAuthorPic);
                String picBig = mPlayBean.getSonginfo().getPic_premium();
                Picasso.with(getApplicationContext()).load(picBig).transform(new BlurTranformation(getApplicationContext())).into(mImageViewBg);
                /**
                 * 详情页左边页面的信息
                 */
                leftLlTitle.setText(title);
                leftLlAuthor.setText(author);
                Picasso.with(getApplicationContext()).load(picAuthor).into(leftLlAuthorIv);
                /**
                 * 将当前歌曲网址以key-value形式存入map集合中
                 */
                netUrlList.put(currentIndex, playUrl);
                /**
                 * 将当前歌曲的实体类以key-value形式存入map集合中
                 */
                netDatasBean.put(currentIndex, mPlayBean);
                /**
                 * 清除
                 */
                mMyBinder.clearDatas();
                /**
                 * 将当前歌曲的网址设置给服务,调用服务的方法
                 */
                mMyBinder.setNetDatas(playUrl);
                /**
                 * 设置当前的位置
                 */
                mMyBinder.setCurrentIndex(0);
                /**
                 * 设置当前歌曲的类型
                 */
                isNetMusic = 1;
                /**
                 * 从实体类中获取当期歌曲的网址
                 */
                currentUrl = netDatasBean.get(currentIndex).getBitrate().getFile_link();
                currentSinger = netDatasBean.get(currentIndex).getSonginfo().getAuthor();
                currentTitle = netDatasBean.get(currentIndex).getSonginfo().getTitle();
                currentTime = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                currentMusicType = 1;
                /**
                 * 利用EventBus传下一曲的位置
                 */
               Bean bean = new Bean(currentIndex);
                mEventBus.post(bean);
                /**
                 * 格式化总时间
                 */
                mTextViewAllTime.setText(syncDuration(netDatasBean.get(currentIndex).getBitrate().getFile_duration() * 1000) + "");



                playNetMusic();
                showLeftList();
                if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                    mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl);
                } else {
                    mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal);
                }
            }

            @Override
            public void errorListener(VolleyError error) {
                error.printStackTrace();
            }
        });

    }

    /**
     * 为seekBar开启一个线程
     */
    public class MusicRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (mMyBinder != null) {
                    /**
                     * 判断当前音乐是否在播放
                     */
                    if (mMyBinder.isPlayingingMusic()) {
                        /**
                         * 获取当前音乐播放的位置
                         */
                        int currentPositon = mMyBinder.getCurrentMusicPosition();
                        /**
                         * 当前音乐的时长
                         */
                        int musicDurration = mMyBinder.getCurrentMusicDuration();
                        Message msg = new Message();
                        msg.what = 101;
                        msg.arg1 = currentPositon;
                        msg.arg2 = musicDurration;
                        mHandler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 建一个广播接受者的内部类
     */
    public class MainBR extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MainBR", "接受广播");
            if (mMyBinder != null) {
                Log.d("BR", mMyBinder + "");
                String title = intent.getStringExtra("title");
                String singer = intent.getStringExtra("singer");
                Log.d("BR", title + " " + singer);
                mSingerTv.setText(singer);
                mNameTv.setText(title);
                mMSeekBar.setMax(mMyBinder.getCurrentMusicDuration());
                int a = intent.getIntExtra("music", 100);
                /**
                 * 这是关于通知栏的代码
                 */
                switch (a) {
                    case 1:
                        finish();
                        break;
                    case 2:
                        mMyBinder.nextMusic();
                        Toast.makeText(MainActivity.this, "下一首", Toast.LENGTH_SHORT).show();
                        pauseIv.setVisibility(View.INVISIBLE);
                        playIv.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mMyBinder.pastMusic();
                        Toast.makeText(MainActivity.this, "上一首", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        mMyBinder.playMusic();
                        mPlayBtnN.setVisibility(View.VISIBLE);
                        mPauseBtnN.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        mMyBinder.pauseMusic();
                        mPlayBtnN.setVisibility(View.INVISIBLE);
                        mPauseBtnN.setVisibility(View.VISIBLE);
                    case 6:
                        /**
                         * 播放榜单详情页歌曲
                         */
                        playMusicListNetMusic(intent);
                        mPauseSong.setVisibility(View.INVISIBLE);
                        mPlaySong.setVisibility(View.VISIBLE);

                        break;
                    case 7:
                        /**
                         * 播放歌单最热界面详情页歌曲
                         */
                        playSongMenuNetMusic(intent);
                        mPauseSong.setVisibility(View.INVISIBLE);
                        mPlaySong.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        /**
                         * 播放推荐界面第一个行布局的详情页的歌曲
                         */
                        playMenuRecommendDetailNetMusic(intent);
                        mPauseSong.setVisibility(View.INVISIBLE);
                        mPlaySong.setVisibility(View.VISIBLE);
                        break;

                }
            }
            /**
             * 从MusicListFagment中接受广播
             */
            int p = intent.getIntExtra("type", 90);
            Log.d("Sysout", "p:" + p);
            switch (p) {
                case 0:
                    mType = intent.getStringExtra("urlType");
                    Log.d("sdgfdfg", mType);
                    FragmentManager mMFragmentManager = getSupportFragmentManager();
                    FragmentTransaction mFragmentTransaction = mMFragmentManager.beginTransaction();

                    if (mMFragmentManager.findFragmentByTag("abc") == null) {
                        mFragmentTransaction.setCustomAnimations(R.anim.in, R.anim.out);
                        mFragmentTransaction.replace(R.id.main_fragment_fl, MusicListDetailFragment.newInstance(intent.getStringExtra("urlType")), "abc");
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();

                    }
                    break;
                case 1:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    if (fragmentManager.findFragmentByTag("bcd") == null) {
                        fragmentTransaction.setCustomAnimations(R.anim.in, R.anim.out);
                        fragmentTransaction.replace(R.id.main_fragment_fl, SongMenuDetailFragment.newInstance(intent.getStringExtra("songIdList")), "bcd");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    break;
                case 2:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    if (fragmentManager1.findFragmentByTag("efg") == null) {
                        fragmentTransaction1.setCustomAnimations(R.anim.in, R.anim.out);
                        fragmentTransaction1.replace(R.id.main_fragment_fl, SongMenuRecommendDetailFragment.newInstance(intent.getStringExtra("listId")), "efg");
                        Log.d("54321", intent.getStringExtra("listId"));
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();
                    }
                    break;
                case 3:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();

                    if (fragmentManager2.findFragmentByTag("789") == null) {
                        fragmentTransaction2.setCustomAnimations(R.anim.in, R.anim.out);
                        /**
                         * 通知fragment占位
                         */
                        fragmentTransaction2.replace(R.id.main_fragment_fl, LikeDetailFragment.newInstance(), "789");
                        fragmentTransaction2.addToBackStack(null);
                        fragmentTransaction2.commit();
                    }
                    break;
                case 4:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();

                    if (fragmentManager3.findFragmentByTag("cgq") == null) {
                        fragmentTransaction3.setCustomAnimations(R.anim.in, R.anim.out);
                        /**
                         * 通知fragment占位
                         */
                        fragmentTransaction3.replace(R.id.main_fragment_fl, ReuseFragment.newInstance(), "cgq");
                        fragmentTransaction3.addToBackStack(null);
                        fragmentTransaction3.commit();
                    }
                    break;

            }
        }

        /**
         * 播放推荐页面歌单推荐列表歌曲
         *
         * @param intent
         */
        private void playMenuRecommendDetailNetMusic(Intent intent) {
            /**
             * 下次重新搜索
             */
            mImageViewI.setVisibility(View.INVISIBLE);
            mImageViewII.setVisibility(View.INVISIBLE);
            mLrcView.setLrc(null);
            searchLrc.setVisibility(View.VISIBLE);
            if (!urlPlayDates.isEmpty()) {
                urlPlayDates.clear();
            }
            songIdList = intent.getStringArrayListExtra("SMDSongIdList");
            Log.d("999", "urlPlayDates.size():" + songIdList.size());
            currentIndex = intent.getIntExtra("position", 0);
            musicUrl = Tools.PLAY_MUSIC_BEFORE + songIdList.get(currentIndex) + Tools.PLAY_MUSIC_AFTER;
            Log.d("888", musicUrl);
            Log.d("888", currentIndex + "");
            NetHelper.MyRequest(musicUrl, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    /**
                     * 对解析后的网址格式化
                     */
                    String data = response.substring(1, response.length() - 2);
                    Gson gson = new Gson();
                    mPlayBean = gson.fromJson(data, PlayBean.class);
                    String musicUrl = mPlayBean.getBitrate().getFile_link();
                    String pic = mPlayBean.getSonginfo().getPic_small();
                    Log.d("34353453", "pic");
                    Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
                    String title = mPlayBean.getSonginfo().getTitle();
                    String author = mPlayBean.getSonginfo().getAuthor();
                    mSingerTv.setText(title);
                    mNameTv.setText(author);
//                    SharedPreferences sharedPreferences = getSharedPreferences("method",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("title",title);
//                    editor.putString("author",author);
//                    editor.putBoolean("isChecked",true);
                    /**
                     * 详情页文本
                     */
                    mTextViewTitle.setText(title);
                    mTextViewAuthor.setText(author);
                    String picAuthor = mPlayBean.getSonginfo().getPic_big();
                    Glide.with(getApplicationContext()).load(picAuthor).into(mImageViewAuthorPic);
                    /**
                     * 详情页左边页面的信息
                     */
                    leftLlTitle.setText("歌手 " + ": " + title);
                    leftLlAuthor.setText("专辑 " + ": " + author);
                    Picasso.with(getApplicationContext()).load(picAuthor).into(leftLlAuthorIv);


                    String picBig = mPlayBean.getSonginfo().getPic_premium();
                    Picasso.with(getApplicationContext()).load(picBig).transform(new BlurTranformation(getApplicationContext())).into(mImageViewBg);
                    /**
                     * 将当前歌曲网址以key-value形式存入map集合中
                     */
                    netUrlList.put(currentIndex, musicUrl);
                    /**
                     * 将当前歌曲的实体类以key-value形式存入map集合中
                     */
                    netDatasBean.put(currentIndex, mPlayBean);
                    /**
                     * 清除
                     */
                    mMyBinder.clearDatas();
                    /**
                     * 将当前歌曲的网址设置给服务,调用服务的方法
                     */
                    mMyBinder.setNetDatas(musicUrl);
                    /**
                     * 设置当前的位置
                     */
                    mMyBinder.setCurrentIndex(0);
                    /**
                     * 设置当前歌曲的类型
                     */
                    isNetMusic = 1;
                    /**
                     * 从实体类中获取当期歌曲的网址
                     */
                    currentUrl = netDatasBean.get(currentIndex).getBitrate().getFile_link();
                    Log.d("发生的房间看是否是", currentUrl);

                    currentSinger = netDatasBean.get(currentIndex).getSonginfo().getAuthor();
                    currentTitle = netDatasBean.get(currentIndex).getSonginfo().getTitle();
                    currentTime = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                    currentMusicType = 1;
                    /**
                     * 获取当前歌曲播放的总时间
                     */
                    currentSongDuration = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                    currentSongPosition = Long.valueOf(mMyBinder.getCurrentMusicPosition());
                    playNetMusic();
                    /**
                     * 格式化总时间
                     */
                    mTextViewAllTime.setText(syncDuration(netDatasBean.get(currentIndex).getBitrate().getFile_duration() * 1000) + "");
                    showLeftList();
                    if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl);
                    } else {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal);
                    }
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });

        }

        /**
         * 播放歌单页面歌曲
         *
         * @param intent
         */
        private void playSongMenuNetMusic(Intent intent) {
            mImageViewI.setVisibility(View.INVISIBLE);
            mImageViewII.setVisibility(View.INVISIBLE);
            /**
             * 下次重新搜索
             */
            mLrcView.setLrc(null);
            searchLrc.setVisibility(View.VISIBLE);
            if (!urlPlayDates.isEmpty()) {
                urlPlayDates.clear();
            }
            songIdList = intent.getStringArrayListExtra("SMDSongIdList");
            Log.d("999", "urlPlayDates.size():" + songIdList.size());
            currentIndex = intent.getIntExtra("position", 0);
            musicUrl = Tools.PLAY_MUSIC_BEFORE + songIdList.get(currentIndex) + Tools.PLAY_MUSIC_AFTER;
            Log.d("888", musicUrl);
            Log.d("888", currentIndex + "");
            NetHelper.MyRequest(musicUrl, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    /**
                     * 对解析后的网址格式化
                     */
                    String data = response.substring(1, response.length() - 2);
                    Log.d("abcdefg4545", data + "");
                    Gson gson = new Gson();
                    mPlayBean = gson.fromJson(data, PlayBean.class);
                    String musicUrl = mPlayBean.getBitrate().getFile_link();
                    String pic = mPlayBean.getSonginfo().getPic_small();
                    Log.d("34353453", "pic");
                    Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
                    String title = mPlayBean.getSonginfo().getTitle();
                    String author = mPlayBean.getSonginfo().getAuthor();
                    mSingerTv.setText(title);
                    mNameTv.setText(author);
//                    SharedPreferences sharedPreferences = getSharedPreferences("method",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("title",title);
//                    editor.putString("author",author);
//                    editor.putBoolean("isChecked",true);
                    /**
                     * 播放详情页内容
                     */
                    mTextViewTitle.setText(title);
                    mTextViewAuthor.setText(author);


                    String picAuthor = mPlayBean.getSonginfo().getPic_big();
                    Glide.with(getApplicationContext()).load(picAuthor).into(mImageViewAuthorPic);
                    String picBig = mPlayBean.getSonginfo().getPic_premium();
                    Picasso.with(getApplicationContext()).load(picBig).transform(new BlurTranformation(getApplicationContext())).into(mImageViewBg);
                    /**
                     * 详情页左边页面的信息
                     */
                    leftLlTitle.setText("歌手 " + ": " + title);
                    leftLlAuthor.setText("专辑 " + ": " + author);
                    Picasso.with(getApplicationContext()).load(picAuthor).into(leftLlAuthorIv);
                    /**
                     * 将当前歌曲网址以key-value形式存入map集合中
                     */
                    netUrlList.put(currentIndex, musicUrl);
                    /**
                     * 将当前歌曲的实体类以key-value形式存入map集合中
                     */
                    netDatasBean.put(currentIndex, mPlayBean);
                    /**
                     * 清除
                     */
                    mMyBinder.clearDatas();
                    /**
                     * 将当前歌曲的网址设置给服务,调用服务的方法
                     */
                    mMyBinder.setNetDatas(musicUrl);
                    /**
                     * 设置当前的位置
                     */
                    mMyBinder.setCurrentIndex(0);
                    /**
                     * 设置当前歌曲的类型
                     */
                    isNetMusic = 1;
                    /**
                     * 从实体类中获取当期歌曲的网址
                     */
                    currentUrl = netDatasBean.get(currentIndex).getBitrate().getFile_link();
                    currentSinger = netDatasBean.get(currentIndex).getSonginfo().getAuthor();
                    currentTitle = netDatasBean.get(currentIndex).getSonginfo().getTitle();
                    currentTime = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                    currentMusicType = 1;
                    /**
                     * 格式化总时间
                     */
                    mTextViewAllTime.setText(syncDuration(netDatasBean.get(currentIndex).getBitrate().getFile_duration() * 1000) + "");
                    playNetMusic();

                    showLeftList();
                    if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl);
                    } else {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal);
                    }
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }

        /**
         * 播放榜单页面歌曲
         *
         * @param intent
         */
        private void playMusicListNetMusic(Intent intent) {
            /**
             * 下次重新搜索
             */
            mImageViewI.setVisibility(View.INVISIBLE);
            mImageViewII.setVisibility(View.INVISIBLE);
            mLrcView.setLrc(null);
            searchLrc.setVisibility(View.VISIBLE);
            String pic = intent.getStringExtra("pic");
            Log.d("safsdf", pic);
            Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
            //每次获取播放列表的时候都要清空
            if (!urlPlayDates.isEmpty()) {
                urlPlayDates.clear();
            }
            songIdList = intent.getStringArrayListExtra("songList");
            currentIndex = intent.getIntExtra("position", 0);
            musicUrl = Tools.PLAY_MUSIC_BEFORE + songIdList.get(currentIndex) + Tools.PLAY_MUSIC_AFTER;
            Log.d("MainBR", musicUrl);
            NetHelper.MyRequest(musicUrl, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    Log.d("asd", response);
                    String data = response.substring(1, response.length() - 2);
                    Log.d("MainBR", data);
                    Gson gson = new Gson();
                    mPlayBean = gson.fromJson(data, PlayBean.class);
                    Log.d("MainBR", mPlayBean.getBitrate().getFile_link());
                    String musicLink = mPlayBean.getBitrate().getFile_link();
                    String pic = mPlayBean.getSonginfo().getPic_small();
                    Log.d("34353453", "pic");
                    Glide.with(getApplicationContext()).load(pic).into(mImageViewHead);
                    String title = mPlayBean.getSonginfo().getTitle();
                    String author = mPlayBean.getSonginfo().getAuthor();
                    mSingerTv.setText(title);
                    mNameTv.setText(author);
//                    SharedPreferences sharedPreferences = getSharedPreferences("method",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("title",title);
//                    editor.putString("author",author);
//                    editor.putBoolean("isChecked",true);
                    /**
                     * 播放详情页内容
                     */
                    mTextViewTitle.setText(title);
                    mTextViewAuthor.setText(author);
                    String picAuthor = mPlayBean.getSonginfo().getPic_big();
                    Glide.with(getApplicationContext()).load(picAuthor).into(mImageViewAuthorPic);
                    String picBig = mPlayBean.getSonginfo().getPic_premium();
                    Picasso.with(getApplicationContext()).load(picBig).transform(new BlurTranformation(getApplicationContext())).into(mImageViewBg);
                    /**
                     * 详情页左边页面的信息
                     */
                    leftLlTitle.setText("歌手 " + ": " + title);
                    leftLlAuthor.setText("专辑 " + ": " + author);
                    Picasso.with(getApplicationContext()).load(picAuthor).into(leftLlAuthorIv);
                    /**
                     * 将当前歌曲网址以key-value形式存入map集合中
                     */
                    netUrlList.put(currentIndex, musicLink);
                    /**
                     * 将当前歌曲的实体类以key-value形式存入map集合中
                     */
                    netDatasBean.put(currentIndex, mPlayBean);
                    /**
                     * 清除
                     */
                    mMyBinder.clearDatas();
                    /**
                     * 将当前歌曲的网址设置给服务,调用服务的方法
                     */
                    mMyBinder.setNetDatas(musicLink);
                    /**
                     * 设置当前的位置
                     */
                    mMyBinder.setCurrentIndex(0);
                    /**
                     * 设置当前歌曲的类型
                     */
                    isNetMusic = 1;
                    /**
                     * 从实体类中获取当期歌曲的网址
                     */
                    currentUrl = netDatasBean.get(currentIndex).getBitrate().getFile_link();
                    currentSinger = netDatasBean.get(currentIndex).getSonginfo().getAuthor();
                    currentTitle = netDatasBean.get(currentIndex).getSonginfo().getTitle();
                    currentTime = Long.valueOf(mMyBinder.getCurrentMusicDuration());
                    currentMusicType = 1;
                    /**
                     * 格式化总时间
                     */
                    mTextViewAllTime.setText(syncDuration(netDatasBean.get(currentIndex).getBitrate().getFile_duration() * 1000) + "");
                    playNetMusic();
                    Log.d("音乐位置3", "currentIndex:" + currentIndex);
                    Log.d("音乐网址3", currentUrl + "--------");
                    if (DBTools.getInstance().isHaveTheTitle(currentTitle)) {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_hl);
                    } else {
                        mImageViewLike.setImageResource(R.mipmap.bt_playpage_button_like_normal);
                    }
                }

                @Override
                public void errorListener(VolleyError error) {
                    Log.d("asd____cuowu", error.getMessage());

                }
            });
        }


    }

    /**
     * 同步音乐时间
     */
    private String syncDuration(long time) {
        if (mMyBinder != null) {
            long currentDuration = time;
            long duration = currentDuration;
            duration = duration / 1000;
            String minute = add0(String.valueOf(duration / 60));
            String second = add0(String.valueOf(duration % 60));
            return minute + ":" + second;
        }
        return "00:00";
    }

    private String add0(String l) {
        StringBuffer temp = new StringBuffer(l);
        if (temp.length() == 1) {
            temp = new StringBuffer();
            temp.append("0");
            temp.append(l);
        }
        return temp.toString();
    }

    /**
     * 播放网络音乐
     */
    private void playNetMusic() {

        if (mMyBinder != null) {
            pauseIv.setVisibility(View.INVISIBLE);
            playIv.setVisibility(View.VISIBLE);
            mMediaPlayer = mMyBinder.getMediaPlayer();
            if (!netUrlList.isEmpty()) {
                Log.d("4545", netUrlList.size() + "");
                mMyBinder.playNetMp3();
            } else {
                Toast.makeText(MainActivity.this, "列表中没有歌曲", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 音乐播放详情页ViewPager
     */
    public class ViewPagerAdapter extends PagerAdapter {
        private List<View> mLinearLayouts;

        public ViewPagerAdapter(List<View> LinearLayouts) {
            mLinearLayouts = LinearLayouts;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mLinearLayouts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mLinearLayouts.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = mLinearLayouts.get(position);
            container.removeView(view);

        }
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        unregisterReceiver(mMainBR);
        if (mMyBinder != null) {
            unbindService(mServiceConnection);
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
