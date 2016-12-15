package com.aqiang.dllo.mybaidumusic.tool.volleyTools;

import android.app.Application;
import android.content.Context;

import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DaoMaster;
import com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass.DaoSession;


/**
 *
 */

public class MyApp extends Application {
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 对外提供获取DaoMaster对象
     */
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getmContext(), "CollectionBean.db", null);
        /**
         * 初始话DaoMaster对象
         */
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    /**
     * 对外提供获取DaoSession方法
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            /**
             *初始化daoSession对象
             */
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

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
