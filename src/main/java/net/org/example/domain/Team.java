package net.org.example.domain;

import java.sql.Date;

/**
 * 班级类
 */
public class Team {
    /**
     * 班级id
     */
    private Integer id;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 班级人数
     */
    private Integer num;
    /**
     * 创建班级的老师的id
     */
    private Integer teacherId;
    /**
     * 班级地址
     */
    private String address;
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
    /**
     * 0:结课 1:未结课
     */
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", num=" + num +
                ", teacherId=" + teacherId +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", updateTime=" + updateTime +
                ", updatePerson='" + updatePerson + '\'' +
                ", flag=" + flag +
                '}';
    }
}
