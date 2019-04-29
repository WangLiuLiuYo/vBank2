
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>银行卡管理列表</title>
</head>
<body>
    <h2>银行卡信息如下</h2>
    <a href="/emp/work">返回工作界面</a>
    <c:forEach items="${cardList}" var="card">
        <div>
            <h3>卡号：${card.cardId}  余额：${card.balance}</h3>
            <c:if test="${card.ok==true}">
                <h3>状态：正常 <a href="/emp/frozeCard?cardId=${card.cardId}">冻结</a></h3>
            </c:if>
            <c:if test="${card.ok==false}">
                <h3>状态：已冻结 <a href="/emp/unfrozeCard?cardId=${card.cardId}">解冻</a></h3>
            </c:if>
        </div>
    </c:forEach>
</body>
</html>
