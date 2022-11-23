package net.org.example.servelt;


import com.alibaba.fastjson.JSON;
import net.org.example.domain.Team;
import net.org.example.domain.User;
import net.org.example.service.Impl.TeamServiceImpl;
import net.org.example.service.TeamService;
import net.org.example.util.JsonData;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "teamServlet", urlPatterns = "/team")
public class TeamServlet extends BaseServlet{

    //类似于springboot的注解一样
    private TeamService teamService = new TeamServiceImpl();

    /**
     * 添加班级
     * http://localhost:8080/wisdoms_war_exploded/team?method=addTeam
     * @param request
     * @param response
     */
    public void addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从前端获取数据
        String className = request.getParameter("className");   //班级名称
        Integer num = Integer.parseInt(request.getParameter("num"));  //班级人数
        String address = request.getParameter("address");

        //创建ServletContext对象
        ServletContext context=getServletContext();
        //得到ServletContext对象中的属性名，用Object接收
        User user = (User)context.getAttribute("userInfo");

        System.out.println(user);

        User user1 = (User)request.getSession().getAttribute("userInfo");

        //传递给前端的数据
        Team team = new Team();
        team.setTeacherId(user.getId());
        team.setCreatePerson(user.getNickName());
        team.setNum(num);
        team.setAddress(address);
        team.setClassName(className);

        System.out.println(team);

        //存储插入的结果
        String result = teamService.addTeam(team);

        System.out.println(result);
        //存储传递给前端的数据
        String s = "";
        if ("创建班级成功".equals(result)){  //表示创建数据成功
            s = JSON.toJSONString(new JsonData(0,null,result));
        }else{  //创建失败的例子
            s = JSON.toJSONString(new JsonData(-1, null, result));
        }
        //传递数据给前端
        response.getWriter().write(s);
    }

    /**
     * 班级列表展示
     * http://localhost:8080/wisdoms_war_exploded/team?method=listTeam
     * @param request
     * @param response
     */
    public void listTeam(HttpServletRequest request, HttpServletResponse response){
        //创建ServletContext对象
        ServletContext context=getServletContext();
        //得到ServletContext对象中的属性名，用Object接收
        User user = (User)context.getAttribute("userInfo");
        //获取创建班级的id
        Integer teacherId = user.getId();

        //查询数据库列表
        List<Team> teamList = teamService.listTeam(teacherId);
        //存储传给前端的数据
        String s = "";
        System.out.println(teamList);

        if (teamList != null){
            s = JSON.toJSONString(new JsonData(0,teamList,"查询成功"));
        }else{
            s = JSON.toJSONString(new JsonData(-1,null,"查询失败"));

        }
    }

    /**
     * 删除班级
     * http://localhost:8080/wisdoms_war_exploded/team?method=deleteTeam
     * @param request
     * @param response
     */
    public void deleteTeam(HttpServletRequest request, HttpServletResponse response){

    }

}
