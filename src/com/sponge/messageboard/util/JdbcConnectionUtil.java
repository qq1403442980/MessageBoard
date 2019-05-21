package com.sponge.messageboard.util;
import java.sql.*;

/**
 * 数据库连接类
 */
public final class JdbcConnectionUtil {
    private static final String url;
    private static final String user;;
    private static final String password;
    static{
        url="jdbc:mysql://localhost:3306/hospital?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        user="root";
        password="root";
    }
    //获取连接
    public static Connection getConnection()
    {
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    //释放资源
    public static void release(Connection con, ResultSet rs, Statement statement)
    {
        if(con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con=null;
        }
        if(rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs=null;
        }
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement=null;
        }
    }
}
