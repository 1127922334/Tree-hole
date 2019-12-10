<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.luntan.demo.model.Users" %><%--
  Created by IntelliJ IDEA.
  User: yue
  Date: 2019/12/8
  Time: 下午6:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html >
<head>
    <title>发布 - 树洞</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <script src="/js/jquery-3.4.1.js" type="application/javascript"></script>
    <script src="/js/jquery-3.4.1.min.js" type="application/javascript"></script>
    <script src="/js/bootstrap.js" type="application/javascript"></script>
    <link rel="stylesheet" href="/css/community.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">锦江树洞</span>

            </button>
            <a class="navbar-brand" href="/">锦江树洞</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="搜索话题">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>

            <ul class="nav nav-tabs navbar-right">
                <li ><a href="/publish">提问</a></li>

                <%
                    Users user = (Users) request.getSession().getAttribute("user");
                %>

                <c:if test="${user !=null}">
                    <li role="presentation" class="dropdown" >
                        <a href="#"  class=" dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <img style="wid 30px;height: 30px;border-radius:5px 5px 5px 5px" src=<%= user.getAvatarUrl()%> alt=<%=user.getName()%> />
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/Myquestions">我的问题</a></li>
                            <li><a href="/logout">退出登录</a></li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${sessionScope.user == null}">
                    <li  ><a href="https://github.com/login/oauth/authorize?client_id=c3a0ea411738c50b7499&redirect_uri=http://localhost:8080/gitlogin&scope=user&state=1">登录</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid main">
    <div class="row" style="margin:19px">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12" >
            <h2> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 发布</h2>
            <hr/>
            <form action="/publish" method="post"><!--通过设置访问的请求方法达到不同的效果-->
                <input  type="hidden" name="id" value="${id}"/>
                <div class="form-group">
                    <label for="title">标题</label>
                    <br><br>

                    <input type="text" class="form-control" id="title" name="title" placeholder="标题" value="${title}">
                </div>
                <div class="form-group">
                    <label for="title">问题补充</label>
                    <br><br>
                    <textarea name="description" id="description" class="form-control"  rows="24"><c:out value="${description}"/></textarea>
                </div>
                <div class="form-group">
                    <label for="title">添加标签</label>
                    <br><br>
                    <input type="text" class="form-control" id="tag" name="tag" value="${tag}" placeholder="输入标签 以,号隔开">
                </div>
                <div class="container-fluid main">
                    <div class="row">
                        <c:if test="${error !=null}">
                        <div  class="alert alert-danger col-lg-9 col-md-12 col-sm-12 col-xs-12" >
                            <c:out value="${error}"/>
                        </div>
                        </c:if>
                            <button type="submit" class="btn btn-success my-publish">发布</button>
                    </div>



                </div>

            </form>

        </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <h3> 问题发起指南</h3>
            • 问题标题: 请用精简的语言描述您发布的问题，不超过25字<br/><br/>
            • 问题补充: 详细补充您的问题内容，并确保问题描述清晰直观, 并提供一些相关的资料：
            <br/><br/>
            • 选择标签: 选择一个或者多个合适的标签，不超过10个字,每个标签用逗号隔开<br/><br/>
            • 关于积分： 发起一个问题会消耗您 20 个积分, 每多一个回复你将获得 5 个积分的奖励 ,为了您的利益, 在发起问题的时候希望能够更好的描述您的问题以及多使用站内搜索功能.<br/><br/>
        </div>
    </div>
</div>
</body>
</html>