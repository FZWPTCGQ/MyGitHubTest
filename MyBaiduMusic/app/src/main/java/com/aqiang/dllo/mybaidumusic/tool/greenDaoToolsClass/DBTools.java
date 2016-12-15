package com.aqiang.dllo.mybaidumusic.tool.greenDaoToolsClass;

import com.aqiang.dllo.mybaidumusic.tool.volleyTools.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/7.
 */
public class DBTools {
private static DBTools ourInstance = new DBTools();
    private static CollectionBeanDao mCollectionBeanDao;
    private Long mSize;

    public static DBTools getInstance() {
        if (ourInstance == null){
            synchronized (DBTools.class){
                if (ourInstance == null){
                    ourInstance = new DBTools();
                }
            }
        }
        mCollectionBeanDao = MyApp.getDaoSession().getCollectionBeanDao();
        return ourInstance;
    }

    private DBTools() {
    }
    //增加单一对象方法
    public void insertCollectionBean(CollectionBean collectionBean){
        mCollectionBeanDao.insert(collectionBean);
    }
    /**
     * 增加集合的方法
     * @param list
     */
    public void insertList(List<CollectionBean> list){
        mCollectionBeanDao.insertInTx(list);
    }

    /**
     * 删除方法
     */
    /**
     * 删除单一对象
     * @param person
     */
    public void deletePerson(CollectionBean person){
        mCollectionBeanDao.delete(person);
    }
    /**
     * 删除所有内容
     */
    public void deleteAll(){
        mCollectionBeanDao.deleteAll();
    }
    /**
     * 根据Id删
     */
    public void deleteById(Long id){
        mCollectionBeanDao.deleteByKey(id);
    }
    /**
     * 根据某一个字段进行删除操作
     */
    public void deleteByTitle(String title){
        DeleteQuery<CollectionBean> deleteQuery = mCollectionBeanDao.queryBuilder().where(CollectionBeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 根据性别姓名年龄进行删除
     * @param name
     * @param sex
     * @param age
     */
//    public void deleteBy(String name,String sex,int age){
//        DeleteQuery<CollectionBean> deleteQuery = mCollectionBeanDao.queryBuilder().where(CollectionBeanDao.Properties.SongName.eq(name),CollectionBeanDao.Properties.Singer.eq(sex),CollectionBeanDao.Properties.Url.eq(age)).buildDelete();
//        if (deleteQuery != null){
//            deleteQuery.executeDeleteWithoutDetachingEntities();
//
//
//        }
//    }
    /**
     * 查询所有的方法
     */
    public List<CollectionBean> queryAll(){
        /**
         * 下面两种方法都可以
         */
        List<CollectionBean> lists = mCollectionBeanDao.loadAll();
        List<CollectionBean> personList = mCollectionBeanDao.queryBuilder().list();
        return lists;
    }
    /**
     * 查重的方法
     */
    //根据姓名来查询
    public boolean isHaveTheTitle(String title){
        /**
         * 属性:Properties
         * PersonDao.Properties.Name.eq(name)判断是否含有该属性
         *
         */
        QueryBuilder<CollectionBean> queryBuilder = mCollectionBeanDao.queryBuilder().where(CollectionBeanDao.Properties.Title.eq(title));
        /**
         * 获取到我们要查询的内容的size
         */
        if(queryBuilder != null){

            mSize = queryBuilder.buildCount().count();
        }
        return mSize > 0 ? true: false;
    }
    //根据title查询


/**
 * 根据实体类来判断
 * @param person
 * @return
 */
//    public boolean isSave(CollectionBean person){
//        QueryBuilder<CollectionBean> queryBuilder = mCollectionBeanDao.queryBuilder().where(PersonDao.Properties.Name.eq(person.getName()),PersonDao.Properties.Age.eq(person.getAge()),PersonDao.Properties.Sex.eq(person.getSex()));
//        Long size = queryBuilder.buildCount().count();
//        return size > 0 ? true : false;
//    }
}
