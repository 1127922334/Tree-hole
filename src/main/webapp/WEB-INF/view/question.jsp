<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.luntan.demo.model.Users" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title><c:out value="${question.title}"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <script src="/js/jquery-3.4.1.js" type="application/javascript"></script>
    <script src="/js/jquery-3.4.1.min.js" type="application/javascript"></script>
    <script src="/js/bootstrap.js" type="application/javascript"></script>
    <link rel="stylesheet" href="/css/community.css">
    <script type="application/javascript" src="/js/community.js"></script>
    <script type="application/javascript" src="/js/comment.js"></script>
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
                <li><a href="/profile/repies">通知<span class="badge pull-right">14</span></a></li>
                <%
                    Users user = (Users) request.getSession().getAttribute("user");
                %>

                <c:if test="<%=user!=null%>">
                    <li role="presentation" class="dropdown" >
                        <a href="#"  class=" dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <img style="width: 30px;height: 30px;border-radius:5px 5px 5px 5px" src=<%= user.getAvatarUrl()%> alt=<%=user.getName()%>/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/Myquestions">我的问题</a></li>
                            <li><a href="/profile/repies">通知<span class="badge pull-right">14</span></a></li>
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
<div class="container-fluid main person" >
    <div class="row" style="margin:19px">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12" >
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
            <jsp:setProperty name="dateValue" property="time" value="${question.gmtCreate}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
            <h2> <c:out value="${question.title}"/></h2>
            <span class="test">
                <span>作者:<c:out value="${question.user.name}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span >回复:<c:out value="${question.commentCount}"/></span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;浏览数:<c:out value="${question.viewCount}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                </span> 发布时间&nbsp;&nbsp;&nbsp;&nbsp;<span><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/></span>
            </span>
            <hr/>
            <div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                <c:out value="${question.description}"/>
            </div>
            <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
            <c:forEach items="${question.tag.split(',')}" var="tag">
                <span class="label label-info"> <span class="glyphicon glyphicon-tags">&nbsp;</span>${tag}</span>
            </c:forEach>
            <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12" />
            <c:if test="${sessionScope.user.id == question.creator && sessionScope.user!=null}">
                <a href="/publish/${question.id}" class="col-lg-9 col-md-12 col-sm-12 col-xs-12 community-menu">
                <span class="glyphicon glyphicon-pencil " aria-hidden="true">编辑</span>
            </a>
            </c:if>
            <br> <br> <br> <br> <br> <br>
        <%--          回复列表--%>
            <h3>&nbsp;&nbsp;&nbsp;<c:out value="${question.commentCount}"></c:out>个回复</h3>
            <hr  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 0"/>
            <div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                <jsp:useBean id="dateValue1" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
                <c:forEach items="${comments}" var="comment">
                    <jsp:setProperty name="dateValue1" property="time" value="${comment.gmtCreate}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
                    <div class="media"  style="margin: 5px">
                        <div class="media-left" >
                            <a href="#">
                                <img class="media-object img-rounded" src="${comment.user.avatarUrl}" title="${comment.user.name}">
                            </a>
                        </div>
                        <div class="media-body"  >
                            <h5 style="margin: 0">
                                <span><c:out value="${comment.user.name}"/></span></h5>
                            <span><c:out value="${comment.comment}"/></span>
                            <div class="menu"> <span class="glyphicon glyphicon-thumbs-up icon"></span><c:out value="${comment.likeCount}"/>
                                <span class="glyphicon glyphicon-comment icon"   data-id="${comment.id}"  onclick="collapseComment(this)"></span><c:out value="${comment.commentCount}"/>
                                <span class="pull-right" style="font-size: 14px"> 发布时间:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${dateValue1}" pattern="yyyy-MM-dd"/></span>
                            </div>
                            <hr  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 8px"/>
<%--                            二级评论--%>
                            <div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comment collapse" id="comment-${comment.id}" >

<%--                                    <jsp:useBean id="dateValue2" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->--%>
<%--                                    <c:forEach items="${comments}" var="comment1">--%>
<%--                                        <jsp:setProperty name="dateValue2" property="time" value="${comment.gmtCreate}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->--%>
<%--                                        <div class="media"  style="margin: 5px">--%>
<%--                                            <div class="media-left" >--%>
<%--                                                <a href="#">--%>
<%--                                                    <img class="media-object img-rounded" src="${comment.user.avatarUrl}" title="${comment.user.name}">--%>
<%--                                                </a>--%>
<%--                                            </div>--%>
<%--                                            <div class="media-body"  >--%>
<%--                                                <h5 style="margin: 0">--%>
<%--                                                    <span><c:out value="${comment.user.name}"/></span></h5>--%>
<%--                                                <span><c:out value="${comment.comment}"/></span>--%>
<%--                                                <div class="menu"> <span class="glyphicon glyphicon-thumbs-up icon"></span><c:out value="${comment.likeCount}"/>--%>
<%--                                                    <span class="glyphicon glyphicon-comment icon"  data-id="${comment.id}" onclick="collapseComment(this)"></span>--%>
<%--                                                    <span class="pull-right" style="font-size: 14px"> 发布时间:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${dateValue2}" pattern="yyyy-MM-dd"/></span>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                            <hr  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 10px"/>--%>
<%--                                        </div>--%>
<%--                                    </c:forEach>--%>
                                    <input type="text" class="form-control" placeholder="请输入回复" id="input-${comment.id}" />
                                    <button type="button" class="btn btn-success my-publish" style="margin-top: 10px;" onclick="comment2(this)" data-id2="${comment.id}" >评论</button>
                                </div>
                        </div>

                    </div>
                </c:forEach>

            </div>
            <div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                <h4>新回复</h4>
                <div class="media"  style="margin: 5px">
                    <div class="media-left" >
                        <a href="#">
                            <img class="media-object img-rounded" style="margin-bottom: 5px" src="${question.user.avatarUrl}" title="${question.user.name}">
                        </a>
                    </div>
                    <div class="media-body"  >
                        <h4 >
                            <span><c:out value="${question.user.name}"/></span></h4>
                    </div>
                </div>

                <input type="hidden" id="question_id" value="${question.id}">
                <textarea class="form-control" rows="6" id="comment"></textarea>
                <br/>
                <button type="submit" class="btn btn-success my-publish" onclick="post()">回复</button>
            </div>
        </div>


        </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>发起人</h4>
                <div class="media"  style="margin: 50px">
                    <div class="media-left " >
                        <a href="#">
                            <img class="media-object img-rounded" src="${question.user.avatarUrl}" title="${question.user.name}">
                        </a>
                    </div>
                    <div class="media-body"  >
                        <h4 >
                            <span href="/question/${question.id}"><c:out value="${question.user.name}"/></span></h4>

                    </div>
                </div>
                <hr >
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>相关问题</h4>


                    <c:forEach items="${tags}" var="tag">
                        <div class="media"  style="margin: 50px">
                            <div class="media-left " >
                                <a href="#">
                                    <img class="media-object img-rounded" src="${tag.user.avatarUrl}" title="${tag.user.name}">
                                </a>
                            </div>
                            <div class="media-body"  >
                                <h4 class="media-heading">
                                    <a href="/question/${tag.id}">
                                        <c:out value="${tag.title}"/>
                                    </a></h4>
                                <span class="wenben"><c:out value="${tag.description}"/></span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br>
                                <span class="test"><span ><c:out value="${tag.commentCount}"/></span>个回复<span>&nbsp;&nbsp;&nbsp;&nbsp;浏览数:<c:out value="${tag.viewCount}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>
                            </div>
                        </div>
                    </c:forEach>


            </div>
        </div>
    </div>
</div>
</body>
</html>
