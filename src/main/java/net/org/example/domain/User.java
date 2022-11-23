package net.org.example.domain;

import java.sql.Date;

/**
 * 用户类
 */
public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账户
     */
    private String countName;
    /**
     * 用户密码 -> 密文存储
     */
    private String pwd;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户电话号码 -> 正则表达式
     */
    private String tel;
    /**
     * 年龄
     */
    private Integer year;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户的身份 0:管理员 1: 学生 2:  教师
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createPerson;
    /**
     * 最后一次修改的人
     */
    private String updatePerson;
    /**
     * 最后一次修改时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", countName='" + countName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", tel='" + tel + '\'' +
                ", year=" + year +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", updatePerson='" + updatePerson + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
