package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	private Long forumId;
	
	
	/** 显示单个主题（主帖+回帖列表） */
	public String show() throws Exception {
		// 准备数据：topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		//准备数据：reply
//		List<Reply> replyList = replyService.getByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
//		String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime";
//		List<Object> patatemeters = new ArrayList<Object>();
//		patatemeters.add(topic);
//		//准备分页信息
//		PageBean pageBean = replyService.getBean(currentPage, pageSize, hql, patatemeters);
//		ActionContext.getContext().getValueStack().push(pageBean);
		
		new QueryHelper(Reply.class, "r")//
		.addWhereCondition("r.topic=?", topic)//
		.addOrderCondition("r.postTime", true)//
		.preparePageBean(replyService, currentPage, pageSize);
		return "show";
	}

	/** 发表新主题页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}


	/** 发表新主题 */
	public String add() throws Exception {
		// 封装
		// >> 表单参数，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(forumService.getById(forumId));
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setAuthor(getCurrentUser());
		
		model.setPostTime(new Date());
		
		
		// 保存
		topicService.save(model);
		

		return "toShow"; // 转到新主题的显示页面
	}
	/**删除本帖*/
	public String delete() throws Exception{
		topicService.delete(model.getId());
		return "toShowForum";
		
	}
	/**
	 * 设置精华
	 * @return
	 * @throws Exception
	 */
	public String setElite() throws Exception{
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_BEST);
		topicService.update(topic);
		return "toShow";
	}
	public String setTop() throws Exception{
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_TOP);
		topicService.update(topic);
		return "toShow";
	}
	public String setCommon() throws Exception{
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_NORMAL);
		topicService.update(topic);
		return "toShow";
	}
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
