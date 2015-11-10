package com.summerhao.bs.all_list.frame_common.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/10/28
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class UserDao extends BaseDao<User, Integer> {

    public UserDao(Context context) {
        super(context);
    }

    @Override
    public Dao<User, Integer> getDao() throws SQLException {
        return getHelper().getDao(User.class);
    }
}
