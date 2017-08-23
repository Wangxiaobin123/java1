<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Bottom</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="style/blue/statusbar.css" />
</head>
<body style="margin:0"> 

<div id="StatusBar">
    <div id="Online">
    	<!-- 在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人
        <span class="OnlineView"><a href="javascript:void(0)">[查看在线名单]</a></span>-->
    </div>
    <div id="Info">
    	<!--<a href="http://localhost:8080/ItcastOA/home_index.action" title = "企业内部管理系统" >企业内部管理系统</a> |
        --><!-- <a href="http://bbs.itcast.cn" title = "传智播客BBS" target="_blank">传智播客BBS</a> -->
    </div>
    <div id="DesktopText">
        <!-- <a href="javascript:void(0)"><img border="0" src="style/images/top/text.gif"/>便笺</a>-->
        <span id=TryoutInfo></span>
        <span id="Version">
            <a href="javascript:void(0)">
            	<!-- <img border="0" width="11" height="11" src="style/images/top/help.gif" /> 
                <img border="0" width="40" height="11" src="style/blue/images/top/version.gif" />
            	-->
            </a>
        </span>
    </div>
</div>

</body>
</html>
