package com.sponge.messageboard.dao;

import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(String username,String password)
    {
        Connection con=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try{
            con= JdbcConnectionUtil.getConnection();
            String sql="select * from users where username=? and password=?;";
            statement=con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            rs=statement.executeQuery();
            while(rs.next())
            {
                User user=new User();
                user.setAddress(rs.getString("address"));
                user.setBrithday(rs.getDate("birthday"));
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(con,rs,statement);
        }
        return null;
    }

    /**
     * 根据Id返回User
     */
    public User getUserById(long id)
    {
        Connection con=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try{
            con= JdbcConnectionUtil.getConnection();
            String sql="select * from users where id=?;";
            statement=con.prepareStatement(sql);
            statement.setLong(1,id);
            rs=statement.executeQuery();
            while(rs.next())
            {
                User user=new User();
                user.setAddress(rs.getString("address"));
                user.setBrithday(rs.getDate("birthday"));
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(con,rs,statement);
        }
        return null;
    }

    /**
     * 修改用户信息
     */
    public boolean updateUser(User user)
    {
        Connection con=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        boolean flag=false;
        int count=0;
        try{
            con= JdbcConnectionUtil.getConnection();
            String sql="update users set password= ? ,address= ? ,phone= ? ,birthday= ? ,real_name= ? where id= ?;";
            statement=con.prepareStatement(sql);
            statement.setString(1,user.getPassword());
            statement.setString(2,user.getAddress());
            statement.setString(3,user.getPhone());
            statement.setDate(4,new Date(user.getBrithday().getTime()));
            statement.setString(5,user.getRealName());
            statement.setLong(6,user.getId());
            count=statement.executeUpdate();
            if(count>0)
            {
                flag=true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(con,rs,statement);
        }
        return flag;
    }

    /**
     * 添加用户
     */
    public boolean addUser(User user)
    {
        if(checkUsername(user.getUsername())==true) {
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            boolean flag = false;
            int count = 0;
            try {
                con = JdbcConnectionUtil.getConnection();
                String sql = "INSERT users (username,password,real_name,birthday,phone,address)" +
                        "VALUES(?,?,?,?,?,?);";
                statement = con.prepareStatement(sql);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                if (user.getRealName() != null && !"".equals(user.getRealName())) {
                    statement.setString(3, user.getRealName());
                } else {
                    statement.setString(3, null);
                }
                if (user.getBrithday() != null && !"".equals(user.getBrithday())) {
                    statement.setDate(4, new Date(user.getBrithday().getTime()));
                } else {
                    statement.setDate(4, null);
                }
                if (user.getPhone() != null && !"".equals(user.getPhone())) {
                    statement.setString(5, user.getPhone());
                } else {
                    statement.setString(5, null);
                }
                if (user.getAddress() != null && !"".equals(user.getAddress())) {
                    statement.setString(6, user.getAddress());
                } else {
                    statement.setString(6, null);
                }
                count = statement.executeUpdate();
                if (count > 0) {
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcConnectionUtil.release(con, rs, statement);
            }
            return flag;
        }else {
            return false;
        }

    }
    /**
     * 查询是否出现相同用户名
     */
    public boolean checkUsername(String username)
    {
        Connection con=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        boolean flag=true;
        int count=0;
        try{
            con= JdbcConnectionUtil.getConnection();
            String sql="select * from users where username= ?;";
            statement=con.prepareStatement(sql);
            statement.setString(1,username);
            rs=statement.executeQuery();
            if(rs.next())
            {
                flag=false;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            JdbcConnectionUtil.release(con,rs,statement);
        }
        return flag;
    }

}
