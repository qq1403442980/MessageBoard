package com.sponge.messageboard.servlet;

import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class UserServlet extends HttpServlet {
    UserService service;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path;
        path=request.getServletPath();
        if(Objects.equals("/userInfo.do",path))
        {
            //用户信息
            User user= (User) request.getSession().getAttribute("user");
            request.setAttribute("user",user);
            request.getRequestDispatcher("/WEB-INF/views/correct/user.jsp").forward(request,response);
        }else if(Objects.equals("/editUserPrompt.do",path))
        {
            //进入修改界面
            long id=Long.valueOf(request.getParameter("id"));
            User user=service.getUserById(id);
            if(null!=user)
            {
               request.setAttribute("user",user);
               request.getRequestDispatcher("/WEB-INF/views/correct/edit_user.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/WEB-INF/views/correct/user.jsp").forward(request,response);
            }
        }else if(Objects.equals("/editUser.do",path))
        {
            //修改用户信息
            String username=request.getParameter("name");
            String password=request.getParameter("password");
            String realName=request.getParameter("realName");
            String birthday=request.getParameter("birthday");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            Long id=Long.valueOf(request.getParameter("id"));
            User user=new User();
            user.setPhone(phone);
            user.setPassword(password);
            user.setRealName(realName);
            user.setAddress(address);
            user.setUsername(username);
            user.setId(id);
            try {
                user.setBrithday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("birthDay格式转换失败");
            }
            boolean result=service.updateUser(user);
            if(result==true)
            {
                //更新成功
                Date date=new Date(user.getBrithday().getTime());
                user.setBrithday(date);
                request.getSession().setAttribute("user",user);
                request.setAttribute("user",user);
                request.getRequestDispatcher("/WEB-INF/views/correct/user.jsp").forward(request,response);
            }else {
                //更新失败
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
            }
        }else if(Objects.equals("/regPrompt.do",path))
        {
            //跳转用户注册界面
            request.getRequestDispatcher("/WEB-INF/views/correct/reg.jsp").forward(request,response);
        }else if(Objects.equals("/regUser.do",path))
        {
            if(regexTest(request)==true) {
                //开始用户注册
                boolean flag = false;
                String username = request.getParameter("name");
                String password = request.getParameter("password");
                String realName = request.getParameter("realName");
                String birthday = request.getParameter("birthday");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                User user = new User();
                try {
                    if (null != birthday && !"".equals(birthday)) {
                        user.setBrithday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("生日格式转换错误");
                }
                user.setUsername(username);
                user.setRealName(realName);
                user.setPhone(phone);
                user.setAddress(address);
                user.setPassword(password);
                flag = service.addUser(user);
                if (flag == true) {
                    //注册成功
                    request.setAttribute("user", null);
                    request.getSession().setAttribute("user", null);
                    request.getRequestDispatcher("/login.do").forward(request, response);
                } else {
                    //注册失败
                    request.setAttribute("code", "1");
                    request.getRequestDispatcher("/WEB-INF/views/correct/reg.jsp").forward(request, response);
                }
            }
            else {
                //注册信息格式错误,重新注册
                request.setAttribute("code","2");
                request.getRequestDispatcher("/WEB-INF/views/correct/reg.jsp").forward(request,response);
            }
        }
    }

    private boolean regexTest(HttpServletRequest request)
    {
        String password=request.getParameter("password");
        String phone=request.getParameter("phone");
        String birthday=request.getParameter("birthday");
        String passwordRegex="\\w{8,}";
        boolean flag1=password.matches(passwordRegex);
        boolean flag2=true;
        boolean flag3=true;
        if(null!=phone&&!"".equals(phone))
        {
            String phoneRegex="1[3578]\\d{9}";
            flag2=phone.matches(phoneRegex);
        }
        if(null!=birthday&&!"".equals(birthday))
        {
            String birthdayRegex="\\d{4}-\\d{2}-\\d{2}";
            flag3=birthday.matches(birthdayRegex);
        }
        return flag1&&flag2&&flag3;
    }

    @Override
    public void destroy() {
        service=null;
    }

    @Override
    public void init() throws ServletException {
        service=new UserService();
    }
}
