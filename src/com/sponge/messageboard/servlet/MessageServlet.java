package com.sponge.messageboard.servlet;

import com.sponge.messageboard.bean.Message;
import com.sponge.messageboard.bean.User;
import com.sponge.messageboard.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MessageServlet extends HttpServlet {
    private MessageService service;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (Objects.equals("/addMessagePrompt.do", path)) {
            //跳转到留言页
            request.getRequestDispatcher("/WEB-INF/views/correct/add_message.jsp").forward(request, response);
        } else if (Objects.equals("/addMessage.do", path)) {
            //开始留言
            User user = (User) request.getSession().getAttribute("user");
            if (null != user) {
                //留言
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                String username = ((User) request.getSession().getAttribute("user")).getUsername();
                Long userId = ((User) request.getSession().getAttribute("user")).getId();
                Message message = new Message(userId, username, title, content);
                boolean flag = service.addMessage(message);
                if (flag == true) {
                    //留言成功
                    request.getRequestDispatcher("/message/list.do").forward(request, response);
                } else {
                    //留言失败
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/correct/login.jsp").forward(request, response);
            }
        } else if (Objects.equals("/my/message/list.do", path)) {
            //显示我的留言
            int page = 1;
            int count = 0;
            int last;
            String pages = request.getParameter("page");
            {
                if (null != pages && !"".equals(pages)) {
                    page = Integer.parseInt(pages);
                }
            }
            List<Message> messages;
            String username = ((User) request.getSession().getAttribute("user")).getUsername();
            messages = service.showMyMessage(page, 5, username);
            if (null != messages) {
                //获取成功
                count = service.myMessageCount(username);
                last = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
                request.setAttribute("last", last);
                request.setAttribute("page", page);
                request.setAttribute("messages", messages);
                request.getRequestDispatcher("/WEB-INF/views/correct/my_message.jsp").forward(request, response);
            } else {
                //获取失败
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
            }
        } else if (Objects.equals("/my/message/delete.do", path)) {
            //删除留言
            String time = request.getParameter("time");
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("时间格式转换失败");
            }
            if (null != date) {
                Timestamp timestamp = new Timestamp(date.getTime());
                boolean result = service.deleteMessageByTime(timestamp);
                if (result == true) {
                    //删除成功
                    request.getRequestDispatcher("/my/message/list.do").forward(request, response);
                } else {
                    //删除失败
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                }
            }

        }
    }

    @Override
    public void destroy() {
        service = null;
    }

    @Override
    public void init() throws ServletException {
        service = new MessageService();
    }
}
