package net.org.example.dao;


import net.org.example.domain.Knowledge;
import net.org.example.domain.Question;
import net.org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 知识点操作层
 */
public class KnowledgeDao {
    //获得数据库的连接
    private Connection conn;
    //获得数据预编译的sql对象
    private PreparedStatement p;
    //接受数据的结果集
    private ResultSet rs;

    /**
     * 添加知识点 -> 知识点内容不能为空
     * @param content
     * @param createPerson
     * @return
     */
    public int addKnowledge(String content, String createPerson) {
        String sql = "insert into knowledge(content,create_time,create_person) values(?,?,?)";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,content);
            p.setDate(2,new Date(System.currentTimeMillis()));
            p.setString(3,createPerson);
            //执行sql
            flag = p.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源,避免浪费
            JDBCUtil.release(conn,p,rs);
            return flag;
        }
    }

    /**
     * 查询内容记录是否存在
     * @param content
     * @return
     */
    public Knowledge findKnowledgeByContent(String content) {
        String sql = "select content,question_id,create_person from knowledge where content = ?";
        Knowledge knowledge = null;
        try {
            //获得数据连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,content);
            //执行sql
            rs = p.executeQuery();
            if (rs.next()){
                knowledge = new Knowledge();
                knowledge.setContent(rs.getString("content"));
                knowledge.setQuestionId(rs.getInt("question_id"));
                knowledge.setCreatePerson(rs.getString("create_person"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,p,rs);
            return knowledge;
        }
    }

    /**
     * 查询所有知识点, 知识点共享
     * @return
     */
    public List<Knowledge> listKnowledge() {
        String sql = "select content,create_time from knowledge";
        List<Knowledge> list = new ArrayList<>();
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //执行sql语句
            rs = p.executeQuery();
            //处理结果集
            while (rs.next()){
                Knowledge knowledge = new Knowledge();
                knowledge.setContent(rs.getString("content"));
                knowledge.setCreateTime(rs.getDate("create_time"));
                list.add(knowledge);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源,避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     * 删除知识点 要求： 未绑定试题, 只能删除自己的
     * @param content
     * @return
     */
    public int delKnowledge(String content) {
        //sql语句
        String sql = "delete from knowledge where content = ?";
        int flag = 0;
        try {
            //获得数据连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,content);
            //执行sql
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
     * 试题资源模块 -> 添加试题 要求:绑定的知识点必须是已经存在的，不限于自己创建的知识点，可绑定他人创建的
     * @param question
     * @return
     */
    public int addQuestion(Question question) {
        String sql = "insert into question(stem,option_true,k_content,option_a,option_b,option_c,option_d,create_time,create_person) values(?,?,?,?,?,?,?,?,?)";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //设置预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,question.getStem());
            p.setString(2,question.getOptionTrue());
            p.setString(3,question.getkContent());
            p.setString(4,question.getOptionA());
            p.setString(5,question.getOptionB());
            p.setString(6,question.getOptionC());
            p.setString(7,question.getOptionD());
            p.setDate(8,new Date(System.currentTimeMillis()));
            p.setString(9,question.getCreatePerson());
            //执行sql语句
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
     * 删除时,则需要修改知识点的绑定状态,
     * @param content
     * @param createPerson
     * @return
     */
    public int updateBind(Integer questionId,String content, String createPerson) {
        String sql = "update knowledge set question_id = ? where content = ? and create_person = ?";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1,questionId);
            p.setString(2,content);
            p.setString(3,createPerson);
            //执行sql
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
     * 创建时候需要绑定,知识点
     * @param questionId
     * @param content
     * @return
     */
    public int questionBind(Integer questionId,String content) {
        String sql = "update knowledge set question_id = ? where content = ?";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //拿到预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setInt(1,questionId);
            p.setString(2,content);
            //执行sql
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
     * 创建试题, 修改绑定状态
     * @param content
     * @return
     */
    public Integer findIdByContent(String content) {
        String sql = "select id from question where k_content = ?";
        int flag = 0;
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,content);
            //执行sql语句
            rs = p.executeQuery();
            if (rs.next()){
                flag = rs.getInt("id");
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
     * 试题资源列表 -> 要求: 列表只展示自己创建的试题资源
     * @param createPerson
     * @return
     */
    public List<Question> listQuestion(String createPerson) {
        //sql语句
        String sql = "select * from question where create_person = ?";
        List<Question> list = new ArrayList<>();
        try {
            //获得数据库的连接
            conn = JDBCUtil.getCollections();
            //获得预编译对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,createPerson);
            //执行sql
            rs = p.executeQuery();
            //处理结果集
            while (rs.next()){
                Question question = new Question();
                question.setStem(rs.getString("stem"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                question.setOptionTrue(rs.getString("option_true"));
                question.setkContent(rs.getString("k_content"));
                question.setCreateTime(rs.getDate("create_time"));
                list.add(question);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源,避免浪费
            JDBCUtil.release(conn,p,rs);
            return list;
        }
    }

    /**
     *试题资源模块 -> 删除试题 ->
     *要求: 只能删除自己创建的试题资源, 删除试题后,对应的知识点解绑定,查看该知识点是否绑定了试题,没有则不删除
     * @param createPerson
     * @param content
     * @return
     */
    public int delQuestion(String createPerson, String content) {
        String sql = "delete from question where create_person = ? and k_content = ?";
        int flag = 0;
        try {
            //获得数据库连接
            conn = JDBCUtil.getCollections();
            //获得预编译处理对象
            p = conn.prepareStatement(sql);
            //设置参数
            p.setString(1,createPerson);
            p.setString(2,content);
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
