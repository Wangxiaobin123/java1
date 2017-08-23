<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>报销</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript"src="${pageContext.request.contextPath}/script/WdatePicker.js"
	charset="utf-8"></script>
	  <script language="javascript"src="${pageContext.request.contextPath}/script/Calendar.js"
	charset="utf-8"></script>
</head>
<body>
<s:form action="user_upload" enctype="multipart/form-data">
<!-- 标题显示 -->
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
            	<td width="70px">报销名称：</td>
                <td width="300px">
                	<s:textfield name="username"></s:textfield>
                </td>
                
            </tr>
            <tr align="left" >
            	<td width="70px">报销原因：</td>
                <td width="300px">
                	<s:textarea name="cause" cssclass="TextareaStyle"></s:textarea>
                </td>
                
            </tr>
            <tr align="left">
            	<td width="70px">单据上传：</td>
                <td width="300px">
                <input type="file" name="file">
                </td>
               
            </tr>
            <tr align="left">
            	<td></td>
            	<td width="300px">
            	<input type="submit" value="提交">
            	
            	</td>
                
            </tr>
        </thead>

    </table>
    
    </div>

</s:form>
</body>
</html>

