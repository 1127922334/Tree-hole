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
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script src="js/jquery-3.4.1.js" type="application/javascript"></script>
    <script src="js/jquery-3.4.1.min.js" type="application/javascript"></script>
    <script src="js/bootstrap.js" type="application/javascript"></script>
    <link rel="stylesheet" href="css/community.css">
</head>
<body>
<div>
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

                    <c:if test="${sessionScope.user != null}">
                    <li role="presentation" class="dropdown" >
                        <a href="#"  class=" dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <img style="wid 30px;height: 30px;border-radius:5px 5px 5px 5px" src=<%= user.getAvatarUrl()%> alt=<%=user.getName()%>/>
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
    <div class="container-fluid main"><!--遍历的书写方式-->
        <div class="row" style="margin:19px">
            <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12" >
                <h2> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 发现</h2>
                <hr/>
                <jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
                <c:forEach items="${questions.questionDTOS}"  var="question">
                    <jsp:setProperty name="dateValue" property="time" value="${question.gmtCreate}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
                <div class="media"  style="margin: 50px">
                    <div class="media-left " >
                        <a href="#">
                            <img class="media-object img-rounded" src="${question.user.avatarUrl}" title="${question.user.name}">
                        </a>
                    </div>
                    <div class="media-body"  >
                        <h4 class="media-heading">
                            <a href="/question/${question.id}"><c:out value="${question.title}"/></a></h4>
                        <span><c:out value="${question.description}"/></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br>
                        <span class="test"><span ><c:out value="${question.commentCount}"/></span>个回复<span>&nbsp;&nbsp;&nbsp;&nbsp;浏览数:<c:out value="${question.viewCount}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span> 发布时间<span>
                <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd "/></span> </span>
                    </div>
                </div>
                </c:forEach>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <c:if test="${questions.firstPage}">
                        <li >
                            <a href="?page=1" aria-label="Previous">
                                <span aria-hidden="true">&lt;&lt;</span>
                            </a>
                        </li>
                        </c:if>
                        <c:if test="${questions.previous}">
                        <li >
                            <a href="?page=${questions.now_page-1}" aria-label="Previous">
                                <span aria-hidden="true">&lt;</span>
                            </a>
                        </li>
                        </c:if>
                        <c:forEach items="${questions.page_number}" var="page">
                            <c:if test="${questions.now_page==page}">
                                <li class="active">
                                    <a  href="?page=${page}" ><c:out value="${page}"/></a></li>
                            </c:if>

                            <c:if test="${questions.now_page!=page}">
                                <li >
                                    <a  href="?page=${page}" ><c:out value="${page}"/></a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${questions.showNext}">
                            <li>
                            <a href="?page=${questions.now_page+1}" aria-label="Next">
                                <span aria-hidden="true">&gt;</span>
                            </a>
                        </li>
                        </c:if>

                        <c:if test="${questions.showEndPage}">
                        <li >
                            <a href="?page=${questions.total_Page}" aria-label="Next">
                                <span aria-hidden="true">&gt;&gt;</span>
                            </a>
                        </li>
                            </c:if>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                <h3> 热门话题</h3>
            </div>
            <h3></h3>
        </div>
    </div>
</div>
</body>
</html>