<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page   import="org.apache.hadoop.fs.FileStatus"  %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URI" %>
<%@ page import="org.apache.hadoop.fs.FsUrlStreamHandlerFactory" %>
<%@ page import="java.net.URL" %>
<%@ page import="com.example.shifangdaxue.dao.HDFSDao" %>
<%@ page import="org.apache.http.client.utils.URLEncodedUtils" %>
<%@ page import="com.example.shifangdaxue.utils.Utils" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.example.shifangdaxue.entity.SearchFile" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<SearchFile> filelist =(ArrayList)request.getAttribute("searchList");
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>人力资源检索页面</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a  href="index.html"></a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">${user.username}</a></li>
                <li><a href="alter">修改密码</a></li>
                <li><a href="sign" target="_self">签到</a></li>
                <li><a href="userlist" target="_self">用户管理</a></li>
<%--                <li><a href="guanli" target="_self">用户管理系统</a></li>--%>
                <li><a href="/" target="_self">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h2>资源检索系统</h2>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="person"><i class="icon-font">&#xe008;</i>个人文件</a></li>
                        <li><a href="group"><i class="icon-font">&#xe005;</i>群组文件</a></li>
                        <li><a href="#"><i class="icon-font">&#xe006;</i>我的收藏</a></li>
                        <li><a href="#"><i class="icon-font">&#xe004;</i>本地同步</a></li>
                        <li><a href="#"><i class="icon-font">&#xe012;</i>我的分享</a></li>
                        <li><a href="#"><i class="icon-font">&#xe052;</i>我的订阅</a></li>
                        <li><a href="#"><i class="icon-font">&#xe033;</i>标签分类</a></li>
                        <li><a href="design.html"><i class="icon-font">&#xe033;</i>回收站</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>帮助中心</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>当前路径>><a href="readdir?url=/" >个人文件</a></span>

                <%--            搜索框--%>
                <div class="container" style="float: right ; padding-right: 10px" >
                    <form action="query" class="parent" method="post">
                        <input type="text" name="search">
                        <input type="submit" value="搜索一下">
                    </form>
                </div>
            </div>
        </div>

        <div class="result-wrap">
            <div class="result-title">
                <h1>系统基本信息</h1>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th><th>文件名</th><th>修改时间</th><th>大小</th><th>路径信息</th>  <th>操作</th>
                    </tr>
                    <%
                        if(filelist!=null){
                            for(SearchFile fs:filelist){

                    %>
                    <tr>
                        <td class="tc"><input name="id[]" value="59" type="checkbox"></td>
                        <td>
                            <img src="images/icon_file.png" /><%=fs.getName() %></td>
                        </td>

                        <td ><%=new SimpleDateFormat("yyyy-MM-dd HH::mm:ss").format(new Date(fs.getTime()))  %></td>

                        <td title="大小">
                                <% if(fs.getSize()>1024*1024){ %>
                                <%=fs.getSize()/1024/1024 %>MB<a target="_blank" href="#" title=""></a>
                                <% } else{%>
                                <%=fs.getSize()/1024%>KB<a target="_blank" href="#" title=""></a>
                                <%}%>
                        </td>
                        <td><%=fs.getPath()%></td>
                        <td>
                            <a class="link-update" href="download?fileName=<%=fs.getPath() %>">下载</a>
                            &nbsp;&nbsp;&nbsp;
                            <a class="link-del" href="delete?filePath=<%=fs.getPath().toString()%>" onclick="javascript:return confirm('确认删除：<%=fs.getPath().toString()%>文件吗？');">删除</a>
                            &nbsp;&nbsp;&nbsp;
                            <a class="link-del" id="preview3" href="#" onclick="javascript:{
                                <% String s = fs.getPath().toString();
                                String substring = s.replace("hdfs://8.142.31.231:9000/","");
//
                                %>
                            <%--var url='<%=url%>';--%>
                                    url='http://8.142.31.231:50070/webhdfs/v1/<%=substring%>?op=OPEN';

                                    window.open('http://8.142.31.231:8888/onlinePreview?url='+encodeURIComponent(url));
                                    }"> 预览</a>
                        </td>
                    </tr>
                    <% } }
                    %>

                </table>
                <div class="list-page"> 7 条 1/1 页</div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>使用帮助</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">更多功能</label><span class="res-info"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>