package com.sponge.messageboard.bean;

import java.sql.Timestamp;

/**
 * 留言信息实体类
 */
public class Message {
    private Long userId;
    private String username;
    private String title;
    private String content;
    private Timestamp createTime;

    public Message() {

    }

    public Message(String username, String title, String content, Timestamp createTime) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
    public Message(Long userId,String username, String title, String content) {
        this.userId=userId;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "username="+username+" title="+title+" content="+content+" createtime="+createTime;
    }
}
