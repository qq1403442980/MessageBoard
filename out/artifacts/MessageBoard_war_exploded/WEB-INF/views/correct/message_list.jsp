<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>留言板</title>
        <link rel="stylesheet" href="/css/index.css">
        <script type="text/javascript">
            function submitMessageForm(flag) {
                if ('first' == flag) {
                    document.getElementById('page').value = 1;
                } else if ('pre' == flag) {
                    var current = Number(document.getElementById('page').value);
                    if (current > 1) {
                        document.getElementById('page').value = current - 1;
                    }
                } else if ('next' == flag) {
                    var current = Number(document.getElementById('page').value);
                    var last = Number(document.getElementById('last').value);
                    if (current < last) {
                        document.getElementById('page').value = current + 1;
                    }
                } else if ('last' == flag) {
                    var last = Number(document.getElementById('last').value);
                    document.getElementById('page').value = last < 1 ? 1 : last;
                }
                document.getElementById('messageForm').submit();
            }
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <nav>
                        <a href="/my/message/list.do">我的留言</a>
                    </nav>
                    <nav>
                        <a href="/userInfo.do">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="/login.do">登录</a>
                        <a href="/regPrompt.do">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>留言板</h1>
                    <p>留下你的脚印👣 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach items="${messages}" var="msg">
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>作者：<a href="">${msg.username}</a></span>
                                <span>时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${msg.createTime}"/></span>
                            </div>
                        </div>
                        <div class="alt-content">
                            <h3>${msg.title}</h3>
                            <p>${msg.content}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <div id="fatie">
                        <a href="/addMessagePrompt.do"><button>点我留言</button></a>
                    </div>
                <%} else { %>
                    <div id="fatie">
                        请<a href="/login.do"><button>登录</button></a>后留言
                    </div>
                <% } %>


                <div id="pagefy">
                    <ul>
                        <form id="messageForm" action="/message/list.do" method="post">
                            <input type="hidden" id="page" name="page" value="${page}">
                            <input type="hidden" id="last" name="last" value="${last}">
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('first')">首页</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('pre')">上一页</a></li>
                            <li><a href="javascript:void(0)">当前第${page}页</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('next')">下一页</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('last')">尾页</a></li>
                        </form>
                    </ul>
                </div>
            </div>
        </section>
        <footer>

        </footer>
    </body>
</html>