<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>登录</title>
		<link rel="stylesheet" href="/css/login.css">
		<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript">
			$(function () {
                $("#changecode").click(function () {
                    $(this).attr("src","<%=request.getContextPath() %>/kaptcha.jpg?d=\"+new Date().getTime();");
                });
            })
		</script>
	</head>
	<body>
	<%
		if(null!=request.getAttribute("error")) {
			String code = (String) request.getAttribute("error");
		    if(code.equals("1"))
			{
			    %>
	<h3 align="center">验证码输入错误,请重新输入</h3>
	<%
			}else if(code.equals("2"))
			{
			    %>
	<h3 align="center">用户名密码错误,请重新输入</h3>
	<%
			}
		}
	%>
		<div class="login">
			<div class="header">
				<h1>
					<a href="/login.do">登录</a>
					<a href="/regPrompt.do">注册</a>
				</h1>
				<button></button>
			</div>
			<form action="/main.do" method="post">
				<div class="name">
					<input type="text" id="name" required="required" name="username" placeholder="请输入登录用户名">
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd" required="required" name="password" placeholder="请输入密码，区分大小写，不能用空格">
					<p></p>
				</div>
				<div class="idcode">
					<input type="text" name="code" placeholder="请输入验证码">
					<img id="changecode" src="kaptcha.jpg"/>
					<div class="clear"></div>
				</div>
				<div class="btn-red">
					<input type="submit" value="登录" id="login-btn">
				</div>
			</form>
		</div>
	</body>
</html>