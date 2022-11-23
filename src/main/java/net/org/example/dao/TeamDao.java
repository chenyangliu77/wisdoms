
package net.org.example.dao;


import net.org.example.domain.Student;
import net.org.example.domain.Team;
import net.org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级的数据库操作层
 */
public class TeamDao {

    //获得数据库的连接
    private Connection conn;
    //获得数据预编译的sql对象
    private PreparedStatement p;
    //接受数据的结果集
    private ResultSet rs;

    /**
     * 添加班级
     * @param team
     * @return
     */
    public int addTeam(Team team) {
        String sql = "insert into team(class_name,num,teacher_id,address,create_time,create_person) values(?,?,?,?,?,?)";
        int flag = 0;
        try {
            //连接数据库
            conn = JDBCUtil.getCollections();
            //获取预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,team.getClassName());
            p.setInt(2,team.getNum());
            p.setInt(3,team.getTeacherId());
            p.setString(4,team.getAddress());
            p.setDate(5,new Date(System.currentTimeMillis()));
            p.setString(6,team.getCreatePerson());
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源,避免资源浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 根据老师的id去查询班级列表
     * @param teacherId
     * @return
     */
    public List<Team> listTeam(int teacherId) {
        String sql = "select id,class_name,num,address from team where teacher_id = ? and flag = 1";
        List<Team> list = new ArrayList<>();
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //创建预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1,teacherId);
            //执行sql语句
            rs = p.executeQuery();
            while (rs.next()){
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setClassName(rs.getString("class_name"));
                team.setNum(rs.getInt("num"));
                team.setAddress(rs.getString("address"));
                list.add(team);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     * 结课, 并没真正意义上的删除,保留信息
     * @param teacherId
     * @param className
     * @return
     */
    public int delete(int teacherId, String className) {
        //sql语句
        String sql = "update team set flag = 0 where teacher_id = ? and class_name = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //创建预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1,teacherId);
            p.setString(2,className);
            //执行sql语句
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 班级模块 -> 添加学生
     * @param student
     * @return
     */
    public int addStudent(Student student) {
        //sql语句
        String sql = "insert into student(student_id,name,team_id,team_name,create_time,create_person) values(?,?,?,?,?,?)";
        int flag = 0;
        try{
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //获得数据库预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,student.getStudentId());
            p.setString(2,student.getName());
            p.setInt(3,student.getTeamId());
            p.setString(4,student.getTeamName());
            p.setDate(5,new Date(System.currentTimeMillis()));
            p.setString(6,student.getCreatePerson());
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
     * 班级模块 -> 查看学生列表
     * @param teamId
     * @param teamName
     * @return
     */
    public List<Student> listStudent(int teamId, String teamName) {
        //sql语句
        String sql = "select student_id, name from student where team_id = ? and team_name = ?";
        //查看结果
        List<Student> list = new ArrayList<>();
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1,teamId);
            p.setString(2,teamName);
            //执行SQL语句
            rs = p.executeQuery();
            //存储查询的数据
            Student student;
            while (rs.next()){
                student = new Student();
                student.setName(rs.getString("name"));
                student.setStudentId(rs.getString("student_id"));
                list.add(student); //添加到集合中
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     * 班级模块 -> 删除学生
     * @param stuId
     * @param name
     * @param teamId
     * @return
     */
    public int deleteStudent(String stuId, String name, int teamId) {
        //执行sql语句
        String sql = "delete from student where student_id = ? and name = ? and team_id = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,stuId);
            p.setString(2,name);
            p.setInt(3,teamId);
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
     * 班级管理模块 -> 修改班级信息
     * @param num
     * @param address
     * @param className
     * @param updatePerson
     * @return
     */
    public int modify(int num, String address, String className, String updatePerson) {
        //执行sql语句
        String sql = "update team set num = ?, address = ?,update_time = ?, update_person = ? where class_name = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1, num);
            p.setString(2,address);
            p.setDate(3, new Date(System.currentTimeMillis()));
            p.setString(4, updatePerson);
            p.setString(5,className);
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
     * 班级模块 -> 查询班级目前有多少人
     * @param teamId
     * @param teamName
     * @return
     */
    public int countStudent(int teamId, String teamName) {
        String sql = "select count(*) from student where team_id = ? and team_name = ?";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1, teamId);
            p.setString(2, teamName);
            //执行sql语句
            rs = p.executeQuery();
            if (rs.next()){
                flag = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 通过班级名称判断数据库里面是否有该班级
     * @param className
     * @return
     */
    public Team findTeamByName(String className) {
        String sql = "select class_name,teacher_id,flag from team where class_name = ?";
        Team team = null;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1, className);
            //执行sql语句
            rs = p.executeQuery();
            if (rs.next()){
                team = new Team();
                team.setClassName(rs.getString("class_name"));
                team.setTeacherId(rs.getInt("teacher_id"));
                team.setFlag(rs.getInt("flag"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源, 避免浪费
            JDBCUtil.release(conn,p,rs);
            return team;
        }
    }
}
