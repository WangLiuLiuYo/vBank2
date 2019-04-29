<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/28
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>转账结果</title>
</head>
<body>
<h1>转账结果如下</h1>
<h1>
    <c:if test="${errorMessage==null}">
        <c:out value="${cardId} 转账成功!"/>
    </c:if>
    <c:if test="${errorMessage!=null}">
        <c:out value="转账出错，错误原因为${errorMessage}。"/>
    </c:if>
</h1>
<h3><a href="/user/service">点击此处回到服务界面</a> </h3>
</body>
</html>
