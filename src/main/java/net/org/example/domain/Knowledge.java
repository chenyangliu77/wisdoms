package net.org.example.domain;

import java.sql.Date;

/**
 * 知识点表
 */
public class Knowledge {
    /**
     * 知识点id
     */
    private Integer id;
    /**
     * 知识点内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人名称
     */
    private String createPerson;

    /**
     * 绑定试题的id
     */
    private Integer questionId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Knowledge{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
