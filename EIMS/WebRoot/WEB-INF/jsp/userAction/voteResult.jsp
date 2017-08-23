<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>投票结果</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>

<body>
 <s:form action="user_list">
 <s:hidden name="id"></s:hidden>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="../style/images/title_arrow.gif"/> 投票结果
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="100">岗位</td>
                <td>投票结果</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        
            <s:iterator value="recordList" status="st"> 
            
            	<tr class="TableDetail1 template">
            	<s:if test="#st.index >0">
            	
	                <td>${name}&nbsp;</td>
	                
	                <td>${department.name}&nbsp;</td>
	                <td>
	                	<s:iterator value="roles">
	                	 ${name}
	                	</s:iterator>
	               </td>
	                <td>
	               ${voteNum }
	               
	                
	                
	                </td>
	                </s:if>
	            </tr>  
	            </s:iterator>
	             
            	
             	
           
             
	            
            
        </tbody>
    </table>
  </div>  
   </s:form>
    <!-- 其他功能超链接 -->
     
   

<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
</html>
