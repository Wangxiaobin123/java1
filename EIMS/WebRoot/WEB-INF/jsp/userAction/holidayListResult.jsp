<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>报销</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 报销
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" >
       
        <!-- 表头-->
        <thead>
        <tr align="left" >
            	<td width="150px">姓名：</td>
            	<td width="150px">报销原因：</td>
            	<td width="150px">审批结果：</td>
               
                
            </tr>
        <s:iterator value="#baoxiaoList" var="baoxiao">
        <s:form action="user_voteBaoxiao?id=%{userId}">
         <tr align="left" >
            	<td width="50px">
            	<a href="${pageContext.request.contextPath}/${ path }" target="_blank">
            	${ username }
            	</a>
            	</td>
            	<td width="50px">${ cause }</td>
            	<td width="50px">${ shenpi }</td>
                <td width="300px">
                <s:if test="#baoxiao.shenpi == null">
                 	待审批
                </s:if>
                <s:else>
              	  已审批
                </s:else>
                	
                </td>
                
            </tr>
            </s:form>
        </s:iterator>
           
        </thead>

    </table>
    
    </div>

</body>
</html>
