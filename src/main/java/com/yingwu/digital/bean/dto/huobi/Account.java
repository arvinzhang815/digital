package com.yingwu.digital.bean.dto.huobi;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class Account {
    private String id;//账户id
    private String type;//账户状态：working：正常, lock：账户被锁定
    private String state;//账户类型：spot：现货账户， margin：杠杆账户，otc：OTC账户，point：点卡账户
    private String userid;

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

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
