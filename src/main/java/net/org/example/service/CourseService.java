package net.org.example.service;


import net.org.example.domain.Course;
import net.org.example.domain.Label;

import java.util.List;

/**
 * 课程表的服务层
 */
public interface CourseService {
    /**
     * 创建课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 查看课程是否存在
     * @param courseTitle
     * @return
     */
    boolean findCourseByTitle(String courseTitle);

    /**
     * 查询是否有该标签
     * @param labelName
     * @return
     */
    Label findLabelByName(String labelName);

    /**
     * 根据创建人来查询课程列表
     * @param createPerson
     * @return
     */
    List<Course> listCourse(String createPerson);

    /**
     * 通过原课程, 修改标签值
     * @param labelName
     * @return
     */
    int modifyCourseInfo(String updatePerson, String courseTitle, String labelName);

    /**
     * 标签管理模块 -> 添加标签
     * @param labelName
     * @return
     */
    int addLabel(String labelName, String createPerson);

    /**
     * 标签管理模块 -> 标签列表展示
     * @return
     */
    List<Label> listLabel();

    /**
     * 标签管理模块 -> 删除标签
     * @param labelName
     * @return
     */
    int deleteLabel(String labelName);
}
