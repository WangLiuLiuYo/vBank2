<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/25
  Time: 6:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>用户登录界面</title>
</head>
<body>
<h1>由于权限限制，请先登录！</h1>
<form method="post" action="/j_spring_security_check">

    个人信息*(工号/用户邮箱)：<input type="text" name="username" required placeholder="邮箱/工号" maxlength="30" minlength="4"><br>
    密码*：<input type="password" name="password" required maxlength="18"><br>
    <input type="reset" value="清空">
    <input type="submit" value="提交">
</form>
</body>
</html>
