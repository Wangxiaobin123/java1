package cn.itcast.oa.view.action;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.runner.Request;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.ws.client.RequestContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	
	private Long topicId;
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/** 发表新回复页面 */
	public String addUI() throws Exception {
		// 准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** 发表新回复 */
	public String add() throws Exception {
		// 封装
		// >> 表单字段，已经封装了title, content
		model.setTopic(topicService.getById(topicId));
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		
		// 保存
		replyService.save(model);
		return "toTopicShow"; // 转到新回复所在主题的显示页面
	}
	
	public String delete() throws Exception{
		Topic topic = topicService.getById(topicId);
//		Reply reply = replyService.getById(model.getId());
//		reply.setId(null);
//		topic.setLastReply(reply);
//		topicService.save(topic);
		
		replyService.delete(model.getId());
		topicService.update(topic);
		return "toTopShow";
		
	}

}
