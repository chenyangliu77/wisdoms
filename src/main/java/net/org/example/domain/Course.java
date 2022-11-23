package net.org.example.domain;

import java.sql.Date;

/**
 * 课程表的类
 */
public class Course {
    /**
     * 标签id
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String courseTitle;
    /**
     * 标签 必须添加已经存在的标签
     */
    private String label;
    /**
     * 标签id
     */
    private Integer labelId;

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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
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
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", label='" + label + '\'' +
                ", labelId=" + labelId +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", updatePerson='" + updatePerson + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
