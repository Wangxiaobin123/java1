<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>部门列表</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 <s:form action="user_list">
 <s:hidden name="id"></s:hidden>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="../style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">电话</td>
                <td width="100">所属部门</td>
                <td width="100">岗位</td>
                <td>备注</td>
                <td>内容</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
            <s:iterator value="recordList" status="st"> 
            <s:form action="user_list">
            	<tr class="TableDetail1 template">
            	<s:if test="#st.index >0">
            	<td>${loginName}&nbsp;</td>
	                <td>${name}&nbsp;</td>
	                <td>${phone}&nbsp;</td>
	                <td>${department.name}&nbsp;</td>
	                <td>
	                	<s:iterator value="roles">
	                	 ${name}
	                	</s:iterator>
	               </td>
	                <td>${description}&nbsp;</td>
	                <td>
	                
 						<s:hidden name="id"></s:hidden>
	                	<s:textarea name="smsContent"></s:textarea>
	                	<s:submit action="user_sendSMS" value="发送"></s:submit>
	               	
	                </td>
	                <td>
	                	<s:a action="user_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>
	                    <s:a action="user_editUI?id=%{id}">修改</s:a>
						<s:a action="user_initPassword?id=%{id}" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
	                </td>
	                </s:if>
	            </tr>  
	             </s:form>	
            	</s:iterator>
             	
           
             
	            
            
        </tbody>
    </table>
  </div>  
   </s:form>
    <!-- 其他功能超链接 -->
     
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="user_addUI"><img src="${pageContext.request.contextPath }/style/images/createNew.png" /></s:a>
        <s:a action="user_editUI2?id=%{#session.user.id}">
					<img border="0" width="13" height="14" src="style/images/top/user_setup.gif" /> 个人修改
				</s:a>
        </div>
        
        
    </div>

<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
</html>
