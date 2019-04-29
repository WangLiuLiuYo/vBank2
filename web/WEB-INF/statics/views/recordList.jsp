<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/28
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.math.BigDecimal" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>账单列表</title>
</head>
<body>
<h1>您的${title}账单如下</h1>
<br><br>
<h2><a href="/user/service">点击回到工作界面</a> </h2>
<c:forEach items="${records}" var="record">
    <% BigDecimal zero=new BigDecimal("0");%>
    <div style="border: black 1px">
       <h3>
           交易卡号：${record.cardId} 金额：${record.money}<br>
           类型：
            <c:if test="${record.money.doubleValue() <0 }">
                <c:out value="支出"/>
            </c:if>
           <c:if test="${record.money.doubleValue()>=0}">
               <c:out value="存入"/>
           </c:if>
           日期：${record.date}<br>
           备注：${record.comment}

       </h3>
    </div>
</c:forEach>

</body>
</html>
