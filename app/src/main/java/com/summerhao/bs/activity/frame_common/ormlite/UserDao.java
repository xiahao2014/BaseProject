package com.summerhao.bs.activity.frame_common.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/10/28
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class UserDao {

    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     */
    public void add(User user) {
        try {
            userDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除所有
     */
    public void deleteData() {
        try {
            userDaoOpe.delete(userDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过name删除
     * @param name
     */
    public void deleteDataByName(String name){
        try {
            userDaoOpe.deleteBuilder().where().eq("desc", name);
            //userDaoOpe.delete(name)
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 更新表
     */
    public void updateData(int id) {
        try {
            //User u1 = new User("xiahao2", "更新数据");
            //userDaoOpe.updateRaw("update ", arguments);
            userDaoOpe.updateBuilder().updateColumnValue("name","zzz").where().eq("id",id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询表所有数据
     */
    public List<User> queryData() {
        try {
            return userDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public User get(int id) {
        User user = null;
        try {
            user = userDaoOpe.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过名字查找数据
     *
     * @param name
     * @return
     */
    public List<User> queryByName(String name) {
        try {
            return userDaoOpe.queryBuilder().where().eq("name", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
