package com.summerhao.bs.activity.view_common.progressdialog;

import java.io.Serializable;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/4
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class Message  implements Serializable {


    private static final long serialVersionUID = 7491152915368949244L;

    /**
     * 消息ID
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
