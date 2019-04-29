<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/26
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎来到银行系统工作台</h1>
<h2><a href="/emp/addUser">新增用户</a></h2>
<h2><a href="/emp/destroy">注销用户</a></h2>
<h2>为用户充值</h2>
<form action="/emp/recharge">
    卡号：<input type="text" name="cardId">
    金额：<input type="text" name="money" placeholder="充值金额">
    <input type="submit" value="充值">
</form>
<h2>修改用户信息</h2>
<form method="post" action="/emp/resetMessage">
    用户姓名*：<input type="text" name="username" required><br>
    身份证号码*：<input type="text" name="uid" required><br>
    邮箱*：<input type="text" name="email" required>
    <input type="submit" value="提交">
    <input type="reset" value="清空">
</form>
<h2>银行卡模糊搜索（前缀匹配）</h2>
<form action="/emp/query">
    卡号：<input type="text" required name="cardIdPrefix"/><br>
    <input type="submit" value="搜索">
    <input type="reset" value="清空">
</form>
</body>
<script src="/WEB-INF/statics/scripts/messageCheck.js">
    $_("cardId").onblur=function () {
        check("cardId",cardid_pat);
    };
    $_("money").onblur=function () {
        check("money",money_pat);
    };
    $_("username").onblur=function () {
        check("username",valid_username_pat);
    };
    $_("uid").onblur=function () {
        check("uid",uid_pat);
    };
    $_("email").onblur=function () {
        check("email",valid_mail_pat);
    };
    var cardid_prefix_pat=/\d{1,20}/g;
    $_("cardIdPrefix").onblur=function () {
        check("cardIdPrefix",cardid_prefix_pat);
    }
</script>
</html>
