<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/2/18
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>

    <form>
        用户名： <input type="text" name="username" /><br>
        密&emsp;码： <input type="password" name="password" /><br>
        验证码： <input type="text" name="code" />
        <input type="submit" value="登录" />
    </form>
</body>
<script>
    $(function(){
        $("form").on("submit", function(){
            $.getJSON('login', $(this).serialize(), function(data){
                console.log(data);
                if(data.code == 200) {
                    location.href = '/main.jsp';
                }
            });
            return false;
        });
    });
</script>
</html>
