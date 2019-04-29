<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>删除结果</title>
</head>
<body>
<c:if test="${deletedRow==1}">
    <h1>删除${uid} 成功，现已删除。</h1>
</c:if>
<c:if test="${deletedRow==0}">
    <h1>${uid}删除失败。</h1>
</c:if>
<br><br>
<a href="/boss/service">返回界面</a>
</body>
</html>
