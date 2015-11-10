package com.summerhao.bs.all_list.frame_common.ormlite;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/10
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public abstract class BaseDao<T, Integer> {

    protected DatabaseHelper mDatabaseHelper;

    protected Context mContext;


    public BaseDao(Context context) {
        mContext = context;
        getHelper();
    }

    /**
     * 获取DatabaseHelper的实例
     *
     * @version 1.0
     * @createTime 2015/11/10,11:15
     * @updateTime 2015/11/10,11:15
     * @createAuthor xiahao
     * @updateAuthor
     * @updateInfo
     */
    public DatabaseHelper getHelper() {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        }
        return mDatabaseHelper;
    }

    /**
     * 获取Dao的操作类
     */
    public abstract Dao<T, Integer> getDao() throws SQLException;

    /**
     * 插入数据
     *
     * @version 1.0
     * @createTime 2015/11/10,11:16
     * @updateTime 2015/11/10,11:16
     * @createAuthor xiahao
     * @updateAuthor
     * @updateInfo
     */
    public int save(T t) throws SQLException {
        return getDao().create(t);
    }

    /**
     * 条件语句查询
     *
     * @param preparedQuery
     * @return
     * @throws SQLException
     */
    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.query(preparedQuery);
    }

    /**
     * 查询字段中的具体指
     *
     * @param attributeName
     * @param attributeValue
     * @return
     * @throws SQLException
     */
    public List<T> query(String attributeName, String attributeValue) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        queryBuilder.where().eq(attributeName, attributeValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 已数组的形式查询多个值，返回列表
     *
     * @param attributeNames
     * @param attributeValues
     * @return
     * @throws SQLException
     * @throws InvalidParameterException
     */
    public List<T> query(String[] attributeNames, String[] attributeValues) throws SQLException,
            InvalidParameterException {
        if (attributeNames.length != attributeValues.length) {
            throw new InvalidParameterException("params size is not equal");

        }
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        for (int i = 0; i < attributeNames.length; i++) {
            wheres.eq(attributeNames[i], attributeValues[i]);
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 查询所有数据
     *
     * @return
     * @throws SQLException
     */
    public List<T> queryAll() throws SQLException {

        // QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        // PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        // return query(preparedQuery);

        Dao<T, Integer> dao = getDao();
        return dao.queryForAll();
    }

    /**
     * 通过id查询
     *
     * @param idName
     * @param idValue
     * @return
     * @throws SQLException
     */
    public T queryById(String idName, String idValue) throws SQLException {
        List<T> lst = query(idName, idValue);
        if (null != lst && !lst.isEmpty()) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    /**
     * 条件语句删除
     *
     * @param preparedDelete
     * @return
     * @throws SQLException
     */
    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {

//        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
//        queryBuilder.where().eq(attributeName, attributeValue);
//        PreparedQuery<T> preparedQuery = queryBuilder.prepare();

        Dao<T, Integer> dao = getDao();
        return dao.delete(preparedDelete);
    }

    /**
     * 删除某个数据
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public int delete(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(t);
    }


    /**
     * 删除一个列表数据
     *
     * @param lst
     * @return
     * @throws SQLException
     */
    public int delete(List<T> lst) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(lst);
    }

    /**
     * 删除具体的值
     *
     * @param attributeNames
     * @param attributeValues
     * @return
     * @throws SQLException
     * @throws InvalidParameterException
     */
    public int delete(String[] attributeNames, String[] attributeValues) throws SQLException,
            InvalidParameterException {
        List<T> lst = query(attributeNames, attributeValues);
        if (null != lst && !lst.isEmpty()) {
            return delete(lst);
        }
        return 0;
    }

    /**
     * 通过值删除
     *
     * @param idName
     * @param idValue
     * @return
     * @throws SQLException
     */
    public int deleteById(String idName, String idValue) throws SQLException,
            InvalidParameterException {
        T t = queryById(idName, idValue);
        if (null != t) {
            return delete(t);
        }
        return 0;
    }

    /**
     * 更新某一条数据
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public int update(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.update(t);
    }


    /**
     * 是否存在表
     *
     * @return
     * @throws SQLException
     */
    public boolean isTableExsits() throws SQLException {
        return getDao().isTableExists();
    }

    /**
     * 数据size
     *
     * @return
     * @throws SQLException
     */
    public long countOf() throws SQLException {
        return getDao().countOf();
    }

    /**
     * map集合形式查询数据
     *
     * @param map
     * @return
     * @throws SQLException
     */
    public List<T> query(Map<String, Object> map) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        if (!map.isEmpty()) {
            Where<T, Integer> wheres = queryBuilder.where();
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 查询某一区间值 适合分页查询
     *
     * @param map
     * @param lowMap  id = 1
     * @param highMap id = 5
     * @return 返回的数据 为 id=2,3,4
     * @throws SQLException
     */
    public List<T> query(Map<String, Object> map, Map<String, Object> lowMap,
                         Map<String, Object> highMap) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        if (!map.isEmpty()) {
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        if (!lowMap.isEmpty()) {
            Set<String> keys = lowMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (map.isEmpty()) {
                    wheres.gt(keyss.get(i), lowMap.get(keyss.get(i)));
                } else {
                    wheres.and().gt(keyss.get(i), lowMap.get(keyss.get(i)));
                }
            }
        }

        if (!highMap.isEmpty()) {
            Set<String> keys = highMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                wheres.and().lt(keyss.get(i), highMap.get(keyss.get(i)));
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**********************以下为原声SQL语句问题**************************************/
    /**
     * 查询
     *
     * @throws SQLException
     */
    public void query() throws SQLException {
        //具体看  http://blog.csdn.net/oyangyujun/article/details/45938563

        // find out how many orders account-id #10 has
//        GenericRawResults<String[]> rawResults = getDao().queryRaw( "select count(*) from orders where
//                account_id = 10");
//                // there should be 1 result
//                List<String[]> results = rawResults.getResults();
//        // the results array should have 1 value
//        String[] resultArray = results.get(0);
//        // this should print the number of orders that have this account-id
//        System.out.println("Account-id 10 has " + resultArray[0] + " orders");

    }

    //原生语句更新
//    fooDao.updateRaw("INSERT INTO accountlog (account_id, total) " + "VALUES ((SELECT account_id,sum
//            (amount) FROM accounts))；


}
