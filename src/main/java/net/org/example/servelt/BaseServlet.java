package net.org.example.servelt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    /**
     *  子类的servlet被访问， 就会调用父类的service
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        //获取请求方法
        String method = req.getParameter("method");
        if(method != null){
            try {
                //利用的是反射机制
                Method targetMethod = this.getClass().getMethod(method,HttpServletRequest.class, HttpServletResponse.class);
                targetMethod.invoke(this,req,resp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
