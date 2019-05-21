<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/8/008
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/add.css">
</head>
<body>
<%
    String code=(String)request.getAttribute("code");
    if(null!=code&&!"".equals(code))
    {
        if(code.equals("1"))
        {
            %>
<h3 align="center">该用户名已被注册过啦,换一个试试吧</h3>
<%
        }
        else if(code.equals("2"))
        {
            %>
<h3 align="center">用户信息格式错误,请重新输入</h3>
<%
        }
    }
%>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
           个人信息填写
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="page-header">
        <h3><small>个人信息填写</small></h3>
    </div>
    <form class="form-horizontal" action="/regUser.do" method="post">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">用户 ：</label>
            <div class="col-sm-6">
                <input name="name" class="form-control" required="required" id="name" placeholder="填写用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password"  class="col-sm-2 control-label">密码 ：</label>
            <div class="col-sm-6">
                <input name="password" required="required" pattern="\w{8,}" class="form-control" id="password" placeholder="填写密码(数字、字母、下划线至少输入八位)">
            </div>
        </div>
        <div class="form-group">
            <label for="realName" class="col-sm-2 control-label">姓名 ：</label>
            <div class="col-sm-8">
                <input name="realName" class="form-control" id="realName" placeholder="填写姓名(可选)">
            </div>
        </div>
        <div class="form-group">
            <label for="birthday" class="col-sm-2 control-label">生日 ：</label>
            <div class="col-sm-8">
                <input name="birthday" class="form-control" pattern="\d{4}-\d{2}-\d{2}" id="birthday" placeholder="填写生日(可选 格式:xxxx-xx-xx)">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">电话 ：</label>
            <div class="col-sm-8">
                <input name="phone"  class="form-control" id="phone" pattern="1[3578]\d{9}" placeholder="填写电话(可选)">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">地址 ：</label>
            <div class="col-sm-8">
                <input name="address"  class="form-control" id="address" placeholder="填写地址(可选)">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">注册</button>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
</div>
<footer class="text-center" >

</footer>
</body>
</html>

