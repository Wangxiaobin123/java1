<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<html>
<head>
	<title>查看主题：新手发帖</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("content");
			fck.Width = "90%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${pageContext.request.contextPath}/fckeditor/";
			fck.ReplaceTextarea();
		});
    </script>
    <script type="text/javascript">
		function comfirmAct(){
			if(confirm('要把本主题设为置顶吗？')){
				alert("置顶成功!");
            	self.location=document.referrer;
				return true;
			}
		}
    </script>
    <script type="text/javascript">
		function comfirmElite(){
			if(confirm('要把本主题设为精华吗？')){
				alert("设置成功!");
            	self.location=document.referrer;
				return true;
			}
		}
    </script>
    <script type="text/javascript">
		function comfirmComm(){
			if(confirm('要把本主题设为普通吗？')){
				alert("设置成功!");
            	self.location=document.referrer;
				return true;
			}
		}
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 查看主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name }</s:a>
			<font class="MenuPoint"> &gt;&gt; </font>
			帖子阅读
			<span style="margin-left:30px;"><s:a action="topic_addUI?forumId=%{#topic.forum.id}">
				<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/></s:a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder dataContainer" datakey="replyList">
		
			<!--显示主题标题等-->
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${topic.title }</b></td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
					<s:if test="#topic.type==1">
					<img border="0" src="${pageContext.request.contextPath}/style/images/forum_hot.gif" />精华
					</s:if>
					<s:else>
					<s:a action="topic_setElite?id=%{#topic.id}" onClick="return comfirmElite()"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_hot.gif" />精华</s:a>
					</s:else>
					<s:if test="#topic.type==2">
					<img border="0" src="${pageContext.request.contextPath}/style/images/forum_top.gif" />置顶
					</s:if><s:else>
					<s:a action="topic_setTop?id=%{#topic.id}" onClick="return comfirmAct()"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_top.gif" />置顶</s:a>
					</s:else>
					<s:if test="#topic.type==0">
					<img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />普通
					</s:if><s:else>
					<s:a action="topic_setCommon?id=%{#topic.id}" onClick="return confirmComm()"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />普通</s:a>
					</s:else>
						 		
						<s:a cssclass="detail" action="reply_addUI?topicId=%{#topic.id}">
						<!-- 
						<a href="moveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</a>
						-->
						<img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</s:a>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea">
			<s:if test="currentPage == 1">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
							</div>
							<!--作者名称-->
							<div class="AuthorName">${topic.author.name }</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
								<!--<s:a cssclass="detail" action="reply_addUI?topicId=%{#topic.id}">
									<img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</s:a>-->
									<!--<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>-->
									<s:a class="detail" action="topic_delete?id=%{#topic.id}&forumId=%{#topic.forum.id}" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</s:a>
								</li>
								<!-- 文章标题 -->
								<li class="TopicSubject">
									${topic.title }
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">${topic.content }</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;"><font color=#C30000>[楼主]</font>
									${topic.postTime }
								</li>
								<li style="float: right;"><a href="javascript:scroll(0,0)">
									<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
								</li>
							</ul>
						</td>
					</tr>
				</table>
				</s:if>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea template">
			<s:iterator value="recordList" status="status">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
							</div>
							<!--作者名称-->
							<div class="AuthorName">${topic.author.name }</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
									<!-- <a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>-->
									<s:a cssclass="detail" action="reply_addUI?topicId=%{#topic.id}">
						
									<!--<img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</s:a>
									<s:a class="detail" action="reply_delete?id=%{id}" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</s:a>
								-->
								</li>
								<!-- 文章标题 -->
								<li class="TopicSubject">
									<!--<img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/${reply.faceIcon}"/>-->
									${title}
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">${content}</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;"><font color=#C30000>[${(currentPage-1)*pageSize+status.count}楼]</font>
									${postTime }
								</li>
								<li style="float: right;"><a href="javascript:scroll(0,0)">
									<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
								</li>
							</ul>
						</td>
					</tr>
				</table>
				</s:iterator>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>

		<!--分页信息-->
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
		<s:form action="topic_show?id=%{id }"></s:form>
		
		
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
	</center>
			
	<!--快速回复-->
	<div class="QuictReply">
	<s:form action="reply_add?topicId=%{id }">
	
	<s:hidden name="id"></s:hidden>
		<div style="padding-left: 3px;">
			<table border="0" cellspacing="1" width="98%" cellpadding="5" class="TableStyle">
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>标题</b></td>
					<td class="no_color_bg">
						<s:textfield type="text" name="title" class="InputStyle" value="%{#session.user.name}回复：%{#topic.title }" style="width:90%"/>
					</td>
				</tr>
				
				<tr class="Tint" height="200">
					<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
					<td valign="top" class="no_color_bg">
						<s:textarea name="content" style="width: 95%; height: 300px"></s:textarea>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td class="no_color_bg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
	</div>
</div>

<div class="Description">
	说明：<br />
	1，主帖只在第一页显示。<br />
	<!-- 2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br />-->
	2，删除主帖，就会删除所有的跟帖（回复）。<br />
</div>

</body>
</html>

   