package com.sponge.messageboard.servlet;

import com.sponge.messageboard.bean.Message;
import com.sponge.messageboard.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageListServlet extends HttpServlet {
    private MessageService service;

    @Override
    public void init() throws ServletException {
        service = new MessageService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count;
        int last;
        String pages = request.getParameter("page");
        List<Message> messages = null;
        int page = 1;
        //获取有效的页面值
        if (null != pages && !"".equals(pages)) {
            page = Integer.parseInt(pages);
        }
        messages = service.getMessage(page, 5);
        count = service.getCount();
        last = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
        request.setAttribute("messages", messages);
        request.setAttribute("page", page);
        request.setAttribute("last", last);
        //跳转到留言列表页
        request.getRequestDispatcher("/WEB-INF/views/correct/message_list.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        service = null;
    }
}
