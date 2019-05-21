<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/6/006
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404页面</title>
    <link rel="stylesheet" type="text/css" href="/css/error.css">
</head>
<body>
<div id="main"><span>4</span><span>0</span><span>4</span><br>
    <span class="font">页面找不到啦,稍后再来吧</span>
</div>
<script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#main>span").eq(0).css("color","pink");
        $("#main>span").eq(1).css("color","gray");
        $("#main>span").eq(2).css("color","lightgreen");
    })
</script>
</body>
</html>
