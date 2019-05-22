<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的留言</title>
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
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/message/list.do">
                   返回主页
                </a>
            </div>
        </div>
    </nav>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>我的留言</h1>
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
                        <a href="/my/message/delete.do?time=${msg.createTime}">删除此条留言</a>
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
        <div id="pagefy">
            <ul>
                <form id="messageForm" action="/my/message/list.do" method="post">
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
