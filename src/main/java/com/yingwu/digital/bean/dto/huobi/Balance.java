package com.yingwu.digital.bean.dto.huobi;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class Balance<T> {
    private String id;
    private String type;
    private String state;
    private String userid;
    private T list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}