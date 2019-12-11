<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.luntan.demo.model.Users" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>树洞</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <script src="js/jquery-3.4.1.js" type="/application/javascript"></script>
    <script src="js/jquery-3.4.1.min.js" type="/application/javascript"></script>
    <script src="js/bootstrap.js" type="/application/javascript"></script>
    <link rel="stylesheet" href="/css/community.css">
</head>
<body>
<div>
    <div class="row" style="position: absolute; top:30%; left: 45%;">
        <div class="jumbotron">
            <h1>出错啦</h1>
            <c:if test="${message !=null}">
                <p><c:out value="${message}"/> </p>
            </c:if>

            <c:if test="${message == null}">
                <p> 太热啦,等会请试试</p>
            </c:if>
            <p><a class="btn btn-primary btn-lg" href="/" role="button">回到主页</a></p>
        </div>

    </div>
</div>
</body>
</html>