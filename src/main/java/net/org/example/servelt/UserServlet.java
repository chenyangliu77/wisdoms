package net.org.example.servelt;

import com.alibaba.fastjson.JSON;
import net.org.example.domain.User;
import net.org.example.service.Impl.UserServiceImpl;
import net.org.example.service.UserService;
import net.org.example.util.JsonData;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();

    /**
     * 用户登录功能
     * http://localhost:8080/wisdoms_war_exploded/user?method=login
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String countName = request.getParameter("countName");
        String pwd = request.getParameter("password");
        System.out.println(countName);
        //登录, 判断flag是否
        User user = userService.login(countName,pwd);

        System.out.println(user.toString());
        if(user != null){  //登录成功
            //将user信息存储到session中
            request.getSession().setAttribute("userInfo",user);

            //获取ServletConfig对象
            ServletConfig config=getServletConfig();
            //根据ServletConfig对象创建ServletContext对象
            ServletContext context=config.getServletContext();
            //向ServletContext对象中添加属性和属性值
            context.setAttribute("userInfo", user);

            //转化为json格式的数据
            String s = JSON.toJSONString(new JsonData(0,null,"登录成功"));
            System.out.println(s);
            //传递给前端数据
            response.getWriter().write(s);

        }else {     //登录失败
            String s = JSON.toJSONString(new JsonData(-1,null,"登录失败"));
            System.out.println(s);
            response.getWriter().write(s);
        }
    }

    /**
     *  用户注销功能
     *  http://localhost:8080/wisdoms_war_exploded/user?method=logout
     * @param request
     * @param response
     */

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 用于清除session的所有信息,通常用于注销用户
        request.getSession().invalidate();
    }

    /**
     *  用户注册功能
     *  http://localhost:8080/wisdoms_war_exploded/user?method=register
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端传递过来的数据
        String nickName = request.getParameter("nickName");
        String pwd = request.getParameter("pwd");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        //将数据传递给后端
        User user = new User();
        user.setNickName(nickName);
        user.setTel(tel);
        user.setEmail(email);
        user.setPwd(pwd);

        int i = userService.register(user);
        //用于存储json字符串的
        String result = "";
        if(i > 0){
            //注册成功
            result = JSON.toJSONString(new JsonData(0,null, "注册成功"));
        }else{
            //注册失败
            result = JSON.toJSONString(new JsonData(-1,null,"注册失败"));
        }
        //将数据传递给前端
        response.getWriter().write(result);
    }

    /**
     * 修改密码, 账号，手机号，邮箱,然后填一个密码进行重置
     * http://localhost:8080/wisdoms_war_exploded/user?method=changePwd
     * @param request
     * @param response
     */
    public void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接受前端传递过来的数据
        String countName = request.getParameter("countName");  //账号
        String tel = request.getParameter("tel");   //电话
        String pwd = request.getParameter("pwd");   //密码
        String email = request.getParameter("email");   //邮箱
        //将前端数据传递给数据库
        int i = userService.updatePwdByTel(pwd,countName,email,tel);
        //存储传给前端的数据
        String s = "";
        if (i > 0){ //修改密码成功
            s = JSON.toJSONString(new JsonData(0, null, "修改成功"));

        }else {  //修改密码失败
            s = JSON.toJSONString(new JsonData(-1,null, "修改失败"));
        }
        //将数据传递给前端
        System.out.println(s);
        response.getWriter().write(s);
    }

    /**
     * 个人信息展示, 通过session获取昵称, 账号, 手机, 邮箱
     * http://localhost:8080/wisdoms_war_exploded/user?method=listUserInfo
     * @param request
     * @param response
     */
    public void listUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //创建ServletContext对象
        ServletContext context=getServletContext();
        //得到ServletContext对象中的属性名，用Object接收
        User user = (User)context.getAttribute("userInfo");

        //存储传递给前端的数据
        String s = "";
        if (user != null){  //判断是否登录
            //查询数据库, 看是否存在该用户
            String countName = user.getCountName();
            String pwd = user.getPwd();
            User user1 = userService.findUser(countName,pwd);
            System.out.println(user1.toString());
            if(user1 != null){
                System.out.println(user1.toString());
                //将数据传递给前端
                s = JSON.toJSONString(new JsonData(0,user1,"查询成功"));
            }else{
                //将数据传递给前端
                s = JSON.toJSONString(new JsonData(-1, null, "查询失败"));
            }
        }else {
            //将数据传递给前端
            s = JSON.toJSONString(new JsonData(-1, null, "查询失败"));
        }
        //将后端的数据传递给前端
        System.out.println(s);
        response.getWriter().write(s);
    }
}
