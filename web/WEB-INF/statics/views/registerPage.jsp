<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/24
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请您注册</title>
</head>
<body>
<h2>请填写用户信息！</h2>
<form method="post" action="/emp/registerUser">
    用户姓名*：<input type="text" name="username" required  id="username" minlength="2" maxlength="20"><br>
    用户身份证号*：<input type="text" name="uid" required  id="uid"><br>
    邮箱*：<input type="text" name="email" required id="email" minlength="6" maxlength="30"><br>
    初始充值金额：<input type="text" name="money" placeholder="0.00" min="0.00" id="money"><br>
    <input type="submit" value="注册">
    <input type="reset" value="清空">
</form>
</body>
<script src="/WEB-INF/statics/scripts/messageCheck.js">
$_("username").onblur=function () {
    check("username",valid_username_pat);
};
$_("uid").onblur=function () {
    check("uid",uid_pat);
};
$_("email").onblur=function () {
    check("email",valid_mail_pat);
};
$_("money").onblur=function () {
    check("money",money_pat);
}

</script>
</html>
