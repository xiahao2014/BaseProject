package com.summerhao.bs.all_list.frame_common.androideventBus;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xiahao
 * @Description User Bean
 * @date 2015/10/27
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */


public class User implements Parcelable {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected User(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
