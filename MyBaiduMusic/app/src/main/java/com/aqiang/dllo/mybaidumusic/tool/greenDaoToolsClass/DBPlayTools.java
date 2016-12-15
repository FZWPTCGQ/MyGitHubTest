package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.List;

/**
 * Created by dllo on 16/12/10.
 */
public class DBPlayTools {
    private static DBPlayTools ourInstance = new DBPlayTools();
    private static PlayBeanDao mPlayBeanDao;
    public static DBPlayTools getInstance() {
        if (ourInstance == null){
            synchronized (DBPlayTools.class){
                if (ourInstance == null){
                    ourInstance = new DBPlayTools();
                }
            }
        }
        mPlayBeanDao = MyApp.getDaoSession().getPlayBeanDao();
        return ourInstance;
    }

    private DBPlayTools() {
    }

    /**
     * 插入集合
     */
    public void insertList(List<PlayBean> list){
        mPlayBeanDao.insertInTx(list);
    }
    /**
     * 删除单一对象
     */
    public void deletePlayBean(PlayBean playBean){
        mPlayBeanDao.delete(playBean);
    }
    /**
     * 根据位置删除
     */
    public void deletePositionBean(String title){

        DeleteQuery<PlayBean> deleteQuery = mPlayBeanDao.queryBuilder().where(PlayBeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
//        mPlayBeanDao.deleteByKey((long)position);
    }
    /**
     * 删除全部
     */
    public void deleteAll(){
        mPlayBeanDao.deleteAll();
    }
    /**
     * 查询所有的方法
     */
    public List<PlayBean> queryAll(){
        List<PlayBean> list = mPlayBeanDao.loadAll();
        return list;
    }
}
