package com.sponge.messageboard.dao;

import com.sponge.messageboard.bean.Message;
import com.sponge.messageboard.util.JdbcConnectionUtil;
import com.sun.xml.internal.ws.api.message.MessageWritable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    /**
     * 获取所有留言
     * @param page
     * @param count
     * @return
     */
    public List<Message> getMessage(int page,int count)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        List<Message> messages;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="select * from board order by createtime desc limit ?,?";
            statement=connection.prepareStatement(sql);
            //根据分页返回条数
            statement.setInt(1,(page-1)*count);
            statement.setInt(2,count);
            rs=statement.executeQuery();
            messages=new ArrayList<>();
            while(rs.next())
            {
                Message message=new Message(rs.getString("username"),rs.getString("title"),
                        rs.getString("content"),rs.getTimestamp("createtime"));
                messages.add(message);
            }
            return messages;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return null;
    }

    //计算留言个数
    public int messageCount()
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="select count(*) total from board;";
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next())
            {
                return rs.getInt("total");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return 0;
    }

    /**
     * 添加留言
     */
    public boolean addMessage(Message message)
    {

        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        int count=0;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="INSERT board(user_id,username,title,content,createtime)" +
                    "VALUES(?,?,?,?,?);";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,message.getUserId());
            statement.setString(2,message.getUsername());
            statement.setString(3,message.getTitle());
            statement.setString(4,message.getContent());
            statement.setTimestamp(5,message.getCreateTime());
            count=statement.executeUpdate();
            if(count>0)
            {
                return true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return false;
    }


    /**
     * 显示我的留言
     */
    public List<Message> showMyMessage(int page,int count,String username)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        List<Message> messages;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="select * from board where username= ? order by createtime desc limit ?,?";
            statement=connection.prepareStatement(sql);
            //根据分页返回条数
            statement.setString(1,username);
            statement.setInt(2,(page-1)*count);
            statement.setInt(3,count);
            rs=statement.executeQuery();
            messages=new ArrayList<>();
            while(rs.next())
            {
                Message message=new Message(rs.getString("username"),rs.getString("title"),
                        rs.getString("content"),rs.getTimestamp("createtime"));
                messages.add(message);
            }
            return messages;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return null;
    }

    //计算我的留言个数
    public int myMessageCount(String username)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="select count(*) total from board where username=?;";
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            rs=statement.executeQuery();
            while(rs.next())
            {
                return rs.getInt("total");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return 0;
    }

    /**
     * 根据时间删除留言
     */
    public boolean deleteMessagByTime(Timestamp timestamp)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        int count=0;
        try {
            connection = JdbcConnectionUtil.getConnection();
            String sql="delete from board where createtime= ?;";
            statement=connection.prepareStatement(sql);
            statement.setTimestamp(1,timestamp);
            count=statement.executeUpdate();
            if(count>0)
            {
                return true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(connection,rs,statement);
        }
        return false;
    }
}
