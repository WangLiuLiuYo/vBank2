<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息修改成功</title>
</head>
<body>
<h1>当前用户信息：</h1>
<h3>用户名：${message.username}</h3>
<h3>用户身份证：${message.uid}</h3>
<h3>用户邮箱：${message.email}</h3>
<br>
<a href="/emp/work">点此回到服务台</a>
</body>
</html>
