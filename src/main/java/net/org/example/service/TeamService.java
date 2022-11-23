package net.org.example.service;


import net.org.example.domain.Student;
import net.org.example.domain.Team;

import java.util.List;

/**
 * Team的服务层
 */
public interface TeamService {

    /**
     *  创建班级
     * @param team
     * @return
     */
    String addTeam(Team team);

    /**
     * 通过班级名称判断班级是否已经存在
     * @param className
     * @return
     */
    Team findTeamByName(String className);

    /**
     * 结课功能 -> flag = 0 为结课 flag = 1 表示为结课
     * @param teacherId
     * @param className
     * @return
     */
    int deleteTeam(int teacherId, String className);

    /**
     * 查询自己创建班级全部列表
     * @param teacherId
     * @return
     */
    List<Team> listTeam(int teacherId);

    /**
     * 修改班级功能
     * @param num
     * @param address
     * @param className
     * @return
     */
    int modify(int num, String address, String className, String updatePerson);
    /**
     * 班级模块 -> 添加学生
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 查看班级里的学生列表 -> 只能看班范围以内的学生
     * @param teamId
     * @param teamName
     * @return
     */
    List<Student> listStudent(int teamId, String teamName);

    /**
     * 班级模块 -> 删除学生
     * @param stuId
     * @param name
     * @param teamId
     * @return
     */
    int deleteStudent(String stuId, String name, int teamId);

    /**
     * 查看班级表中总人数 (要求不能大于班级预设总人数)
     * @param teamId
     * @param teamName
     * @return
     */
    int countStudent(int teamId, String teamName);
}
