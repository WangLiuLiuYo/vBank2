<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户删除操作</title>
</head>
<body>
<h1>此操作极为危险！请仔细操作！</h1>
<form method="post" action="/emp/destroy">
    用户身份证号码：<input type="text" minlength="18" maxlength="18" name="uid" required><br>
    重复输入：<input type="text" minlength="18" maxlength="18" name="uid2" required><br>
    <div id="hint"></div>
    <input type="submit" value="确定注销" id="sub">
</form>
</body>
<script src="/WEB-INF/statics/scripts/messageCheck.js">
    $_("sub").onmouseover=function () {
        var t1=$_("uid").value;
        var t2=$_("uid2").value;
        if(t1!=t2){
            $_("hint").value="输入不一致！";
        }
    }
    $_("uid").onblur=function () {
        check("uid",uid_pat);
    }
</script>
</html>
