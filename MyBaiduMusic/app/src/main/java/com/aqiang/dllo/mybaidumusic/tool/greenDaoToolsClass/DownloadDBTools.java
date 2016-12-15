package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 * 单例的形式
 */
public class DownloadDBTools {
    private static DownloadDBTools ourInstance = new DownloadDBTools();
    private static DownloadBeanDao mDownloadBeanDao;


    public static DownloadDBTools getInstance() {
        if (ourInstance == null){
            synchronized (DownloadDBTools.class){
                if (ourInstance == null){
                    ourInstance = new DownloadDBTools();
                }
            }
        }
        mDownloadBeanDao = MyApp.getDaoSession().getDownloadBeanDao();
        return ourInstance;
    }

    private DownloadDBTools() {
    }

    /**
     * 插入一条
     * @param downloadBean
     */
    public void insertDownloadBean(DownloadBean downloadBean){
        mDownloadBeanDao.insert(downloadBean);
    }

    /**
     * 插入集合
     * @param list
     */
    public void insertDownloadBeanList(List<DownloadBean> list){
        mDownloadBeanDao.insertInTx(list);
    }
    /**
     * 根据某一字段进行删除
     */
    public void deleteByTitle(String title){
        DeleteQuery<DownloadBean> deleteQuery = mDownloadBeanDao.queryBuilder().where(DownloadBeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    /**
     * 查询所有
     */
    public List<DownloadBean> queryAll(){
        List<DownloadBean> list = mDownloadBeanDao.loadAll();
        return list;
    }
    /**
     * 查重的方法
     */
    public boolean isHaveTheTitle(String title){
        QueryBuilder<DownloadBean> queryBuilder = mDownloadBeanDao.queryBuilder().where(DownloadBeanDao.Properties.Title.eq(title));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
