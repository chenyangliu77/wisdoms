package net.org.example.domain;

import java.sql.Date;

/**
 * 学生类
 */
public class Student {
    /**
     * 学生id
     */
    private Integer id;
    /**
     * 学生学号
     */
    private String studentId;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 所在班级id
     */
    private Integer teamId;
    /**
     * 所在班级名称
     */
    private String teamName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人姓名
     */
    private String createPerson;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人姓名
     */
    private String updatePerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", updateTime=" + updateTime +
                ", updatePerson='" + updatePerson + '\'' +
                '}';
    }
}
