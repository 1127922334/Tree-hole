<%@ page import="com.luntan.demo.model.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yue
  Date: 2019/12/8
  Time: 下午7:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的问题</title>
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
                            <img style="wid 30px;height: 30px;border-radius:5px 5px 5px 5px" src=<%= user.getAvatarUrl()%> alt=<%=user.getName()%>/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/Myquestions">我的问题</a></li>
                            <li><a href="/profile/repies">退出登录</a></li>
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
<div class="container-fluid main person" >
    <div class="row" style="margin:19px">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12" >
            <h2> <c:out value="${sectionName}"/></h2>
            <hr/>
            <c:if test="${section.equals('questions')}">
                <jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
                <c:forEach items="${mypage.questionDTOS}"  var="question">
                    <jsp:setProperty name="dateValue" property="time" value="${question.gmtcreate}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
                    <div class="media"  style="margin: 50px">
                        <div class="media-left " >
                            <a href="#">
                                <img class="media-object img-rounded" src="${question.user.avatarUrl}" title="${question.user.name}">
                            </a>
                        </div>
                        <div class="media-body"  >
                            <h4 class="media-heading"> <c:out value="${question.title}"/></h4>
                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br>
                            <span class="test"><span ><c:out value="${question.commentCount}"/></span>个回复 <span>
                <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/></span> </span>
                        </div>
                    </div>
                </c:forEach>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <c:if test="${mypage.firstPage}">
                            <li >
                                <a href="?page=1" aria-label="Previous">
                                    <span aria-hidden="true">&lt;&lt;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${mypage.previous}">
                            <li >
                                <a href="?page=${mypage.now_page-1}" aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items="${mypage.page_number}" var="page">
                            <c:if test="${mypage.now_page==page}">
                                <li class="active">
                                    <a  href="?page=${page}" ><c:out value="${page}"/></a></li>
                            </c:if>

                            <c:if test="${mypage.now_page!=page}">
                                <li >
                                    <a  href="?page=${page}" ><c:out value="${page}"/></a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${mypage.showNext}">
                            <li>
                                <a href="?page=${mypage.now_page+1}" aria-label="Next">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${mypage.showEndPage}">
                            <li >
                                <a href="?page=${mypage.total_Page}" aria-label="Next">
                                    <span aria-hidden="true">&gt;&gt;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>

        </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <div class="list-group section">
                <c:if test="${section =='questions'}">
                    <a href="/profile/Myquestions" class="list-group-item active">我的问题</a>
                </c:if>
                <c:if test="${section !='questions'}">
                    <a href="/profile/Myquestions" class="list-group-item ">我的问题</a>
                </c:if>

                <c:if test="${section=='repies'}">
                    <a href="/profile/repies"  class="active list-group-item">
                        最新回复
                        <span class="badge">14</span>
                    </a>
                </c:if>
                <c:if test="${section !='repies'}">
                    <a href="/profile/repies"  class="list-group-item">
                        最新回复
                        <span class="badge">14</span>
                    </a>
                </c:if>
                 </div>
            </div>
    </div>
    </div>
</body>
</html>
