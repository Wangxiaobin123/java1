<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>投票竞选</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				投票竞选
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="150px">部门名称</td>
					<td style="text-align: left">相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="departmentList">
				<s:iterator value="#departmentList" var="depart">
					<tr class="TableDetail1 template">
						<td><s:a action="department_list?parentId=%{id}">${name}</s:a>&nbsp;</td>
						<td>
							
	                	<s:if test="#depart.voteNum==0">
	                	<input name="vote" type="radio" value="${ name }"/>
	                	<s:a action="department_vote?id=%{id}">投票</s:a></s:if>
	                	<s:else>
	                	已投
	                	</s:else>
							
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		
	</div>

	

</body>
</html>
