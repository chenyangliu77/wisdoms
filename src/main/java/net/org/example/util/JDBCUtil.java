package net.org.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据库连接工具类
 */
public class JDBCUtil {
    /**
     * 数据库加载驱动
     */
    private static String driver;
    /**
     * 数据库连接地址
     */
    private static String url;
    /**
     * 数据库账号
     */
    private static String username;
    /**
     * 数据库密码
     */
    private static String pwd;
    //静态模块, 可以用来加载驱动
    static {
        InputStream in = null;
        try{
            //加载资源文件
//            in = JDBCUtil.class.getClassLoader().getResourceAsStream("/student.properties");
//            Properties p = new Properties();
//            p.load(in);
            //获取资源文件里面的值key-value的形式存储的
//            driver = p.getProperty("driver");
//            url = p.getProperty("url");
//            username = p.getProperty("username");
//            pwd = p.getProperty("pwd");
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://127.0.0.1:3306/wisdom?useUnicode=true&characterEncoding=utf-8&useSSL=false";
            username = "root";
            pwd = "123456";
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获得数据库连接的接口
     * @return
     */
    public static Connection getCollections(){
        Connection conn = null;
        try{
            conn =  DriverManager.getConnection(url,username,pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 修改数据库信息的方法: 包括增删改操作
     * @param sql
     * @param objects
     * @return
     */
    public static int executeUpdate(String sql,Object...  objects){
           //
        try{
            Connection conn=getCollections();
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
             for(int i=0;i<objects.length;i++){
                 preparedStatement.setObject(i+1,objects[i]);
             }
           return  preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally{

        }
    }

    /**
     * 查询数据库的方式
     * @param sql
     * @param objects
     * @return
     */
    public static  ResultSet executeQuery(String sql,Object...  objects){

        try{
            Connection conn=getCollections();
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            for(int i=0;i<objects.length;i++){
                preparedStatement.setObject(i+1,objects[i]);
            }
            return  preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放资源, 避免资源浪费
     * @param conn
     * @param p
     * @param rs
     */
    public static void release(Connection conn, PreparedStatement p, ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
            if (p != null){
                p.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
