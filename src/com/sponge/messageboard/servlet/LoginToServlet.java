package com.sponge.messageboard.servlet;

import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginToServlet extends HttpServlet {
    private LoginService service;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String usercode=request.getParameter("code");
        String code=(String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        PrintWriter writer=response.getWriter();
        //验证
        if(usercode!=null&&code!=null)
        {
            if(usercode.equalsIgnoreCase(code))
            {
                //处理登录逻辑
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                User user=service.login(username,password);
                if(null!=user)
                {
                    //登录成功
                    request.getSession().setAttribute("user",user);
                    request.getRequestDispatcher("/message/list.do").forward(request,response);
                }else {
                    //登录失败
                    request.setAttribute("error","2");
                    request.getRequestDispatcher("/login.do").forward(request,response);
                }
            }else {
                request.setAttribute("error","1");
                request.getRequestDispatcher("/login.do").forward(request,response);
            }
        }else {
            request.setAttribute("error","1");
            request.getRequestDispatcher("/login.do").forward(request,response);
        }
    }

    @Override
    public void destroy() {
       service=null;
    }

    @Override
    public void init() throws ServletException {
        service=new LoginService();
    }
}
