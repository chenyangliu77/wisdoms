package net.org.example.dao;


import net.org.example.domain.Course;
import net.org.example.domain.Label;
import net.org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程表的数据库操作层
 */
public class CourseDao {
    //获得数据库的连接
    private Connection conn;
    //获得数据预编译的sql对象
    private PreparedStatement p;
    //接受数据的结果集
    private ResultSet rs;
    /**
     * 创建课程
     * @param course
     * @return
     */
    public int addCourse(Course course) {
        String sql = "insert into course(course_title,label,label_id,create_time,create_person) values(?,?,?,?,?)";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,course.getCourseTitle());
            p.setString(2,course.getLabel());
            p.setInt(3, course.getLabelId());
            p.setDate(4,new Date(System.currentTimeMillis()));
            p.setString(5,course.getCreatePerson());
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 查询是否有重复的课程名称
     * @param courseTitle
     * @return
     */
    public boolean findCourseByTitle(String courseTitle) {
        //sql语句
        String sql = "select course_title from course where course_title = ?";
        boolean flag = false;  //记录是否查询成功
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1, courseTitle);
            //执行sql语句
            rs = p.executeQuery();
            while (rs.next()){
                if (courseTitle.equals(rs.getString("course_title"))){
                    flag = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 查询标签是否存在,存在则返回
     * @param labelName
     * @return
     */
    public Label findLabelByName(String labelName) {
        String sql = "select id, label from label where label = ?";
        Label label = null;
        try {
            //获得数据的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,labelName);
            //执行sql语句
            rs = p.executeQuery();
            if (rs.next()){
                label = new Label();
                label.setLabel(rs.getString("label"));
                label.setId(rs.getInt("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return label;
        }
    }

    /**
     * 课程模块 -> 查询课程列表
     * @param createPerson
     * @return
     */
    public List<Course> listCourse(String createPerson) {
        //sql语句
        String sql = "select course_title,label,create_time,update_time from course where create_person = ?";
        List<Course> list = new ArrayList<>();
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //获得数据库预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,createPerson);
            //执行sql
            rs = p.executeQuery();
            //处理结果集
            while (rs.next()){
                Course course = new Course();
                course.setCourseTitle(rs.getString("course_title"));
                course.setLabel(rs.getString("label"));
                course.setCreateTime(rs.getDate("create_time"));
                course.setUpdateTime(rs.getDate("update_time"));
                list.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     * 课程模块 -> 修改课程
     * @param updatePerson
     * @param courseTitle
     * @param labelName
     * @param labelId
     * @return
     */
    public int modifyCourseInfo(String updatePerson, String courseTitle, String labelName, Integer labelId) {
        //sql语句
        String sql = "update course set label = ?, label_id =?,update_time = ?,update_person = ? where course_title = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //拿到预编译处理对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,labelName);
            p.setInt(2, labelId);
            p.setDate(3, new Date(System.currentTimeMillis()));
            p.setString(4,updatePerson);
            p.setString(5,courseTitle);
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 标签管理模块 -> 添加标签
     * @param labelName
     * @param createPerson
     * @return
     */
    public int addLabel(String labelName,String createPerson) {
        String sql = "insert into label(label,create_time,create_person) values(?,?,?)";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,labelName);
            p.setDate(2,new Date(System.currentTimeMillis()));
            p.setString(3,createPerson);
            //执行sql
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源,避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 标签管理模块 -> 标签列表展示
     * @return
     */
    public List<Label> listLabel() {
        String sql = "select label, create_time from label";
        List<Label> list = new ArrayList<>();
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //执行sql
            rs = p.executeQuery();
            while (rs.next()){
                Label label = new Label();
                label.setLabel(rs.getString("label"));
                label.setCreateTime(rs.getDate("create_time"));
                list.add(label);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     * 标签管理模块 -> 删除指定标签
     * @param labelName
     * @return
     */
    public int deleteLabel(String labelName) {
        //sql语句
        String sql = "delete from label where  label = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,labelName);
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }
}
