package net.org.example.service.Impl;


import net.org.example.dao.CourseDao;
import net.org.example.domain.Course;
import net.org.example.domain.Label;
import net.org.example.service.CourseService;

import java.util.List;

/**
 * 课程表服务层
 */
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = new CourseDao();

    /**
     * 课程模块 -> 创建课程 要求: 课程名不能重复, 且标签必须存在
     * @param course
     * @return
     */
    @Override
    public int addCourse(Course course) {
        if (course != null){
            //判断课程是否已经存在, 存在则不能创建
            boolean flag = findCourseByTitle(course.getCourseTitle());
            //判断标签是否已经存在,不存在则不能创建
            Label label = findLabelByName(course.getLabel());
            if (!flag && label != null){
                course.setLabelId(label.getId());  //查询数据库获取label的id
                return courseDao.addCourse(course);
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    /**
     * 课程模块 -> 查询课程是否存在
     * @param courseTitle
     * @return
     */
    @Override
    public boolean findCourseByTitle(String courseTitle) {
        if ("".equals(courseTitle) || courseTitle == null){ //判断传进来的是否为脏数据
            return false;
        }
        return courseDao.findCourseByTitle(courseTitle);
    }

    /**
     * 查询标签是否存在
     * @param labelName
     * @return
     */
    @Override
    public Label findLabelByName(String labelName) {
        return courseDao.findLabelByName(labelName);
    }

    /**
     * 课程模块 -> 查询课程列表 要求: 只展示该用户创建的课程列表；若修改时间为空，则不展示
     * @param createPerson
     * @return
     */
    @Override
    public List<Course> listCourse(String createPerson) {
        return courseDao.listCourse(createPerson);
    }

    /**
     * 课程模块 -> 修改课程 要求: 修改的标签必须存在
     * @param updatePerson
     * @param courseTitle
     * @param labelName
     * @return
     */
    @Override
    public int modifyCourseInfo(String updatePerson,String courseTitle, String labelName) {
        //先查询是否存在标签
        Label label = findLabelByName(labelName);
        if (label == null){ //如果标签为空则也不能修改
            return 0;
        }
        if (labelName.equals(label.getLabel())){  //如果存在该标签,才可以修改,否则不能修改
            //然后获得label的id
            Integer labelId = label.getId();
            return courseDao.modifyCourseInfo(updatePerson,courseTitle,labelName,labelId);
        }
        return 0;
    }

    /**
     * 标签管理模块 -> 添加标签
     * @param labelName
     * @param createPerson
     * @return
     */
    @Override
    public int addLabel(String labelName,String createPerson) {
        Label label = findLabelByName(labelName);
        //判断是否已经存在, 不存在,则可以添加该标签
        if (label == null){
            return courseDao.addLabel(labelName,createPerson);
        }
        return 0;
    }

    /**
     * 标签管理模块 -> 标签列表展示
     * @return
     */
    @Override
    public List<Label> listLabel() {
        return courseDao.listLabel();
    }

    /**
     * 标签管理模块 -> 删除指定标签
     * @param labelName
     * @return
     */
    @Override
    public int deleteLabel(String labelName) {
        //判断标签是否存在, 存在则可以删
        Label label = findLabelByName(labelName);
        if (label != null){
            return courseDao.deleteLabel(labelName);
        }else {
            return 0;
        }
    }
}
