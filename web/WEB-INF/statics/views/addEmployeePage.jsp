<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/28
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工雇佣界面</title>
</head>
<body>
   <h1>请填写下列员工信息</h1>
   <form method="post" action="/boss/add">
       员工姓名*：<input type="text" name="name" required id="name"><br>
       员工薪水*：<input type="text" name="salary" required id="salary"><br>
       毕业院校：<input type="text" name="university" id="unv"><br>
       <input type="submit" value="雇佣ta">
       <input type="reset" value="清空">
   </form>
</body>
<script src="/WEB-INF/statics/scripts/messageCheck.js">
$_("name").onblur=function () {
    check("name",valid_username_pat);
};
$_("salary").onblur=function () {
    check("salary",money_pat);
};
$_("unv").onblur=function () {
    check("unv",unveristy_pat);

};

</script>
</html>
