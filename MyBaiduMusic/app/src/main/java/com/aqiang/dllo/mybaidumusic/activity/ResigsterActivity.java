package com.aqiang.dllo.mybaidumusic.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.name;

public class ResigsterActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mMTextViewQuestion;
    private ImageView mImageViewQQ;
    private String name, icon;
    private PlatformActionListener platformActionListener;
    private SharedPreferences sharedPreferences;
    private boolean isHave = false;
    private static String name1;
    private static String icon1;
    private ImageView imageViewReturn;

    @Override
    int setLayout() {
        return R.layout.activity_resigster;
    }

    @Override
    void initViews() {
        login();
        imageViewReturn = (ImageView)findViewById(R.id.resigster_return);
        mMTextViewQuestion = (ImageView) findViewById(R.id.resigster_question);
        mImageViewQQ = (ImageView) findViewById(R.id.resigster_other);

    }

    @Override
    void initDatas() {
        mImageViewQQ.setOnClickListener(this);
        imageViewReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resigster_other:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息

                // 回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();

                        name1 = platformDb.getUserName();
                        icon1 = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name1);
                        intent.putExtra("icon", icon1);
                        setResult(0, intent);
                        finish();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.userNameTv.setText(name1);
                                Picasso.with(MyApp.getmContext()).load(icon1).into(MainActivity.userPicIv);
//                                SharedPreferences sharedPreferences = getSharedPreferences("useMessage",MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("useName", name1);
//                                editor.putString("useIcon", icon1);
//                                editor.putBoolean("isHave",true);
//                                editor.commit();
                            }
                        });


                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });


                break;
            case R.id.resigster_return:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        try {
            PlatformDb platformDb = qq.getDb();
            name = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                MainActivity.userNameTv.setText(name);
                Picasso.with(this).load(icon).into(MainActivity.userPicIv);
            }
        } catch (NullPointerException e) {

        }

        platformActionListener = new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                arg0.getDb().exportData();
                MainActivity.userNameTv.setText("登录");
                MainActivity.userPicIv.setImageResource(R.mipmap.ic_launcher);
            }
            @Override
            public void onCancel(Platform arg0, int arg1) {

            }
        };
    }
}
