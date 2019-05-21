package com.sponge.messageboard.bean;


import com.sun.org.apache.xerces.internal.impl.dv.xs.DateDV;

import java.util.Date;


public class User {
    private Long id;
    private String username;
    private String password;
    private String realName;
    private Date brithday;
    private String phone;
    private String address;
    public User()
    {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "id="+id+" username="+username+" password="+password+" realName="+realName+" " +
                " birthday="+brithday+" phone="+phone+" address="+address;
    }
}
