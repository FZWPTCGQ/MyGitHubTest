package com.aqiang.dllo.mybaidumusic.tool.volleyTools;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/29.
 */

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    //对外提供一个获取Context对象的方法
    public static Context getmContext() {
        return mContext;
    }

}
