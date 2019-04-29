<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/26
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工作人员登录</title>
</head>
<body>
<form method="post" action="/employee/login">
    工号：<input type="text" name="wid"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="提交"><br>
    <input type="reset" value="清空">
</form>
</body>
</html>
