<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>资料下载</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <style type="text/css">
    	.disabled{
    		color: gray;
    		cursor: pointer;
    	}
    </style>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 资料上传
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" >
       
        <!-- 表头-->
        <thead>
        <tr align="left" >
            	<td width="150px">名称：</td>
                <td width="300px">
                	操作
                </td>
                
            </tr>
        <s:iterator value="#filesList">
         <tr align="left" >
            	<td width="50px">${ name }</td>
                <td width="300px">
                	<a href="${pageContext.request.contextPath}/${ path }" target="_blank">
                            点击下载</a>
                </td>
                
            </tr>
        </s:iterator>
           
        </thead>

    </table>
    
    </div>
</body>
</html>
