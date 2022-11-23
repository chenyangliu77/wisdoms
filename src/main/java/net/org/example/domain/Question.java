package net.org.example.domain;

import java.sql.Date;

/**
 * 试题类
*/
public class Question {
    /**
     * 试题id
     */
    private Integer id;
    /**
     * 题干
     */
    private String stem;
    /**
     * 正确选项
     */
    private String optionTrue;
    /**
     * 选项A
     */
    private String optionA;
    /**
     * 选项B
     */
    private String optionB;
    /**
     * 选项C
     */
    private String optionC;
    /**
     * 选项D
     */
    private String optionD;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人名称
     */
    private String createPerson;
    /**
     * 绑定试题内容
     */
    private String kContent;

    public String getkContent() {
        return kContent;
    }

    public void setkContent(String kContent) {
        this.kContent = kContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
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

    public String getOptionTrue() {
        return optionTrue;
    }

    public void setOptionTrue(String optionTrue) {
        this.optionTrue = optionTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", stem='" + stem + '\'' +
                ", optionTrue='" + optionTrue + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                ", kContent='" + kContent + '\'' +
                '}';
    }
}
