package net.org.example.domain;

import java.sql.Date;

/**
 * 标签表
 */
public class Label {
    /**
     * 标签id
     */
    private Integer id;
    /**
     * 标签名
     */
    private String label;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                '}';
    }
}
