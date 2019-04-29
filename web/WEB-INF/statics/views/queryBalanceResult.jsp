<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/28
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>余额查询结果</title>
</head>
<body>
<h1>截止到 ${current} :</h1>
<h2>您的余额为：${balance} (RMB).</h2>
<h3><a href="/user/service">点击此处回到服务页面</a></h3>
<h3>或者，查看账单</h3>
<form action="/user/queryRecord">
    <h3>查看您银行卡的账单（3个选项）</h3>
    <input type="radio" name="gap" value="1" checked>本月账单
    <input type="radio" name="gap" value="12">本年度账单
    <input type="radio" name="gap" value="all">全部账单
    <input type="submit" value="查询">
</form>
</body>
</html>
