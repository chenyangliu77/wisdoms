package net.org.example.service.Impl;


import net.org.example.dao.TeamDao;
import net.org.example.domain.Student;
import net.org.example.domain.Team;
import net.org.example.service.TeamService;

import java.util.List;

//Team的服务层
public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao = new TeamDao();

    /**
     * 创建班级
     * @param team
     * @return
     */
    @Override
    public String addTeam(Team team) {
        //人数不能少于0
        if (team.getNum() <= 0){
            return "人数不能少于0";
        }
        //通过班级名称判断是否班级存在
        Team t = findTeamByName(team.getClassName());
        if (t != null){
            return "班级名称已经被注册";
        }
        //将数据传入到数据中
        int i = teamDao.addTeam(team);
        if (i > 0){
            return "创建班级成功";
        }else{
            return "创建班级失败";
        }
    }

    /**
     * 通过班级名称判断数据库里面是否有该班级
     * @param className
     * @return
     */
    @Override
    public Team findTeamByName(String className) {
        return teamDao.findTeamByName(className);
    }

    /**
     * 结课 flag = 1 未结课 flag = 0 结课
     * @param teacherId
     * @param className
     * @return
     */
    @Override
    public int deleteTeam(int teacherId,String className) {
        return teamDao.delete(teacherId,className);
    }

    /**
     * 根据老师id查询班级列表
     * @param teacherId
     * @return
     */
    @Override
    public List<Team> listTeam(int teacherId) {
        return teamDao.listTeam(teacherId);
    }

    /**
     * 修改班级信息
     * @param num
     * @param address
     * @param className
     * @param updatePerson
     * @return
     */
    @Override
    public int modify(int num, String address, String className, String updatePerson) {
        return teamDao.modify(num, address, className,updatePerson);
    }

    /**
     * 班级模块 -> 添加学生
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        return teamDao.addStudent(student);
    }

    /**
     * 班级模块 -> 查看学生列表
     * @param teamId
     * @param teamName
     * @return
     */
    @Override
    public List<Student> listStudent(int teamId, String teamName) {
        return teamDao.listStudent(teamId,teamName);
    }

    /**
     * 班级模块 -> 删除学生
     * @param stuId
     * @param name
     * @param teamId
     * @return
     */
    @Override
    public int deleteStudent(String stuId, String name,int teamId) {
        return teamDao.deleteStudent(stuId,name,teamId);
    }

    /**
     * 班级模块 -> 查询目前学生表中的人数
     * @param teamId
     * @param teamName
     * @return
     */
    @Override
    public int countStudent(int teamId, String teamName) {
        return teamDao.countStudent(teamId,teamName);
    }
}
