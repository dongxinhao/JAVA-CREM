<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/sys/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/login.css">
    <script type="text/javascript" src="<%=path%>/static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=path%>/bizjs/sys/login.js"></script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>登录</h1>
        <form method="post" action="index.html">
            <p>&nbsp;&nbsp;&nbsp;<input type="text" name="username" value="" placeholder="用户名"></p>
            <p>&nbsp;&nbsp;&nbsp;<input type="password" name="password" value="" placeholder="密码"></p>
            <p class="remember_me">
                <label id="rem">
                    <input type="checkbox" name="remember_me" id="remember_me">
                    记住密码
                </label>
            </p>
            <p class="button"><input id="sub1" type="button" name="commit" value="登录"></p>
        </form>
    </div>

    <div class="login-help">
        <p>忘记密码？ <a href="index.html">→点我</a>.</p>
    </div>
</section>
</body>
</html>
