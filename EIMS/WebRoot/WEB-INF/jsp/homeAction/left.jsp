<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>导航菜单</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
<script type="text/javascript">
	function menuClick(menu) {
		$(menu).next().toggle();
	}
</script>
</head>
<body style="margin: 0">
	<div id="Menu">
		<!-- 显示一级菜单 -->
		<ul id="MenuUl">
		<s:iterator value="#application.topPrivilegeList" status="st">
		<s:if test="#session.user.name=='超级管理员'">
	
		<s:if test="#st.index!=2">
		<s:if test="#session.user.hasPrivilegeByName(name)">
			<li class="level1">
				<div onClick="menuClick(this);" class="level1Style">
					<img src="style/images/${id}.gif" class="Icon" />
					${name }
				</div>
				<ul style="" class="MenuLevel2">
				<!-- 显示二级级菜单 -->
				<s:iterator value="children">
				<s:if test="#session.user.hasPrivilegeByName(name)">
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="${pageContext.request.contextPath }${url}.action">${name}</a>
						</div></li>
					</s:if>
				</s:iterator>
				</ul>
			</li>
		</s:if>
		</s:if>
		</s:if>
		<s:else>
		
		<s:if test="#session.user.hasPrivilegeByName(name)">
		
			<li class="level1">
				<div onClick="menuClick(this);" class="level1Style">
					<img src="style/images/${id}.gif" class="Icon" />
					${name }
				</div>
				<ul style="" class="MenuLevel2">
				<!-- 显示二级级菜单 -->
				<s:iterator value="children">
				<s:if test="#session.user.hasPrivilegeByName(name)">
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="${pageContext.request.contextPath }${url}.action">${name}</a>
						</div></li>
					</s:if>
				</s:iterator>
				</ul>
			</li>
		</s:if>
		
		</s:else>
		</s:iterator>
		</ul>
	</div>
</body>
</html>
