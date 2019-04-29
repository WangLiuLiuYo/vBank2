<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/25
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>虚拟银行用户操作界面</title>
</head>
<body>
<h2><a href="/user/queryBalance">查询本卡余额</a> </h2>
<br>
<h2>转账服务</h2>
<form action="/user/transfer">
    对方用户名（必填）：<input type="text" name="username" required><br>
    对方卡号(必填）：<input type="text" name="cardId" required><br>
    金额（必填）:<input type="text" name="money" required><br>
    捎句话给对方吧<input type="text" name="comment" placeholder=" " maxlength="30">
    <input type="submit" value="转账">
</form>
</body>
<script src="/WEB-INF/statics/scripts/messageCheck.js">
$_("username").onblur=function () {
    check("username",valid_username_pat);
};
$_("cardId").onblur=function () {
    check("cardId",cardid_pat);
};
$_("money").onblur=function () {
    check("money",money_pat);
};
</script>
</html>
