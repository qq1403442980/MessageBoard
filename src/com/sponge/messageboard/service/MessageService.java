package com.sponge.messageboard.service;

import com.sponge.messageboard.bean.Message;
import com.sponge.messageboard.dao.MessageDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class MessageService {
    MessageDao dao=null;
    public MessageService()
    {
        dao=new MessageDao();
    }
    public List<Message> getMessage(int page,int  count)
    {
        List<Message> messages=dao.getMessage(page,count);
        return messages;
    }
    public int getCount()
    {
        return dao.messageCount();
    }
    public boolean addMessage(Message message)
    {
        Date date=new Date();
        message.setCreateTime(new Timestamp(date.getTime()));
        return dao.addMessage(message);
    }
    public List<Message> showMyMessage(int page,int count,String usesrname)
    {
        return dao.showMyMessage(page,count,usesrname);
    }
    public int myMessageCount(String username)
    {
        return dao.myMessageCount(username);
    }
    public boolean deleteMessageByTime(Timestamp timestamp)
    {
        return dao.deleteMessagByTime(timestamp);
    }
}
