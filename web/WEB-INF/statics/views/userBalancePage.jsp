<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/25
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>用户余额</title>
</head>
<body>
<h1>
    截止到<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")%>,
    您的卡号 ${balance.cardId} 余额为 ：${balanc.balance}。
</h1>
<h2><a href="userService.jsp">点击此处回到服务页面</a> </h2>
</body>
</html>
