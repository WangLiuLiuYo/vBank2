<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工管理界面</title>
</head>
<body>
<h2><a href="/boss/service"> 返回服务主界面</a></h2>
<h2>
    <c:forEach items="${employees}" var="emp">
        <div style="border: black 1px solid">
            <h3>工号：${emp.workId}    姓名：${emp.name}</h3>
            <h3>毕业院校：${emp.university}   入职时间：${emp.init}</h3>
            <h3>当前薪水：${emp.salary}     <a href="/boss/upSalary?workId=${emp.workId}&salary=${emp.salary}">增加一级薪水</a></h3>
        </div>
    </c:forEach>
</h2>
</body>
</html>
