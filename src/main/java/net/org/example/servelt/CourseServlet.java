package net.org.example.servelt;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "courseServlet", urlPatterns = "/course")
public class CourseServlet extends BaseServlet{

    /**
     * 添加课程接口
     * http://localhost:8080/wisdoms_war_exploded/course?method=addCourse
     * @param request
     * @param response
     */
    public void addCourse(HttpServletRequest request, HttpServletResponse response){

    }


}
