package com.zhongruan.etc.bean;

public class UserRole {

    private int uid;

    private int rid;

    public UserRole() {
    }

    public int getUid() {
        return uid;
    }

    public UserRole setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public int getRid() {
        return rid;
    }

    public UserRole setRid(int rid) {
        this.rid = rid;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
