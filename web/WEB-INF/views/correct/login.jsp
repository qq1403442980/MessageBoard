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
            function getCookie(cookie_name) {
                var allCookies = document.cookie;
                var cookie_pos = allCookies.indexOf(cookie_name);   //如果找到了索引，就代表cookie存在
                if (cookie_pos != -1) {
                    cookie_pos += cookie_name.length + 1;
                    var cookie_end = allCookies.indexOf(";", cookie_pos);
                    if (cookie_end == -1) {
                        cookie_end = allCookies.length;
                    }
                    return unescape(allCookies.substring(cookie_pos, cookie_end));
                }
                return null;
            }
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
				<div class="autoLogin">
					<label for="">
						<input type="checkbox" checked="checked">
						下次自动登录
					</label>
					<a href="">忘记密码</a>
				</div>
				<div class="btn-red">
					<input type="submit" value="登录" id="login-btn">
				</div>
			</form>
		</div>
	</body>
</html>