<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
    <META http-equiv=Content-Type CONTENT="text/html; charset=gbk" />
	<TITLE>企业内部管理系统</TITLE>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<LINK HREF="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
		$(function() {
			document.forms[0].loginName.focus();
		});
		// 在被嵌套时就刷新上级窗口
		if(window.parent!=window){
			window.parent.location.reload(true);
		}
	</script>
</HEAD>

<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >
<s:form action="user_login">
    <DIV ID="CenterAreaBg">
        <DIV ID="CenterArea">
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/logo.png" /></DIV>
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror/></font>
                		</td>
                	</tr>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></TD>
                        <TD>
                        	<s:textfield name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput" />
                        </TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;"><INPUT TYPE="image" SRC="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif"/></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0"
								SRC="${pageContext.request.contextPath}/style/blue/images/login/password.gif" />
							</TD>
                        <TD>
                        	<s:password name="password" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required" />
                        </TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2017版管理系统</A></DIV>
        </DIV>
    </DIV>
</s:form>
</BODY>

</HTML>

