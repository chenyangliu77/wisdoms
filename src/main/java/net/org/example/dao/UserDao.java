package net.org.example.dao;


import net.org.example.domain.User;
import net.org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户类的数据库操作层
 */
public class UserDao {
    //获得数据库的连接
    private Connection conn;
    //获得数据预编译的sql对象
    private PreparedStatement p;
    //接受数据的结果集
    private ResultSet rs;
    /**
     * 用户登录功能, 通过账号和密码登录 md5加密
     * @param countName
     * @param pwd
     * @return
     */
    public User login(String countName, String pwd){
        //查询是否有这一个账户
        String sql = "select * from user where count_name=? and pwd=?";
        //用来存储查询结果的
        User user = null;
        try {
            //获得连接
            conn = JDBCUtil.getCollections();
            //拿到sql预编译对象
            p = conn.prepareStatement(sql);
            //设置查询条件
            p.setString(1, countName);
            p.setString(2, pwd);
            //查询结果
            rs = p.executeQuery();
            //判断phone和pwd是否在这个结果集合中
            while (rs.next()) {
                user = new User();
                //设置id
                user.setId(rs.getInt("id"));
                //设置账户名
                user.setCountName(rs.getString("count_name"));
                //设置密码
                user.setPwd(rs.getString("pwd"));
                //昵称
                user.setNickName(rs.getString("nick_name"));
                //设置电话号码
                user.setTel(rs.getString("tel"));
                //设置邮箱
                user.setEmail(rs.getString("eamil"));
                //设置年龄
                user.setYear(rs.getInt("year"));
                //设置老师的类型
                user.setType(rs.getInt("type"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源, 避免资源浪费
            JDBCUtil.release(conn,p,rs);
            return user;
        }
    }

    /**
     * 忘记密码 返回真实密码
     * @param countName
     * @param tel
     * @param email
     * @return
     */
    public String findTruePwd(String countName, String tel, String email){
        //查询是否真的存在这条记录
        String sql = "select pwd from user where count_name = ? and tel = ? and eamil = ?";
        //用来存储真实密码的,避免未关闭资源
        String truePwd = "";
        try {
            //连接数据库
            conn = JDBCUtil.getCollections();
            //创建sql的预编译环境
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,countName);
            p.setString(2,tel);
            p.setString(3,email);
            //将结果集合保存
            rs = p.executeQuery();
            if (rs.next()){
                truePwd = rs.getString("pwd");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
        }
        return truePwd;
    }

    /**
     * 用户注册 正则校验  昵称，密码，确认密码，手机号，邮箱
     * @param user
     * @return
     */
    public int register(User user){
        String sql = "insert into user(count_name, pwd, nick_name, tel, year, eamil, type, create_time) values(?,?,?,?,?,?,?,?)";
        Integer flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //获得预编译处理对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,user.getCountName());
            p.setString(2,user.getPwd());
            p.setString(3,user.getNickName());
            p.setString(4,user.getTel());
            p.setInt(5,user.getYear());
            p.setString(6,user.getEmail());
            p.setInt(7,user.getType());
            p.setDate(8, new Date(System.currentTimeMillis()));
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 判断生成的账号是否唯一
     * @param countName
     * @return
     */
    public boolean findUserByCountName(String countName) {
        String sql = "select count_name from user where count_name = ?";
        boolean flag = false;
        try {
            //获得数据库的连接
            conn =JDBCUtil.getCollections();
            //创建预编译环境
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,countName);
            //执行sql语句
            rs =  p.executeQuery();
            if (rs.next()){
                flag = true; //有此账号,应该重新生成
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }


    /**
     * 查询用户整体信息
     * @param countName
     * @param pwd
     * @return
     */
    public User findUser(String countName, String pwd) {
        String sql = "select * from user where count_name = ? and pwd = ?";
        User user = null;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,countName);
            p.setString(2,pwd);
            //执行sql
            rs = p.executeQuery();
            //处理结果集
            if (rs.next()){
                user = new User();
                user.setNickName(rs.getString("nick_name"));
                user.setCountName(rs.getString("count_name"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("eamil"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //避免资源浪费
            JDBCUtil.release(conn,p,rs);
            return user;
        }
    }

    /**
     * 更新密码
     * @param tel
     * @return
     */
    public int updatePwdByTel(String pwd, String countName,String email, String tel) {
        String sql = "update user set pwd = ? where tel = ? and count_name =  ? and eamil = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,pwd);
            p.setString(2,tel);
            p.setString(3,countName);
            p.setString(4,email);
            //执行sql
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //避免资源浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }
}
