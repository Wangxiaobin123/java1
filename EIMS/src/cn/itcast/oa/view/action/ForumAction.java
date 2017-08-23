package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ognl.accessor.ObjectAccessor;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	/** 版块列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.getAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 显示单个版块（主题列表） */
	public String show() throws Exception {
		// 准备数据：forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		// 准备数据：topicList
		// List<Topic> topicList = topicService.getByForum(forum);
		// ActionContext.getContext().put("topicList", topicList);

		// 分页准备信息的数据
		// PageBean pageBean =
		// topicService.getBeanByForum(currentPage,pageSize,forum);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// 分页准备二
		// String hql =
		// "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime";
		// List<Object> patatemeters = new ArrayList<Object>();
		// patatemeters.add(forum);

		// 分页准备三
//		String hql = "FROM Topic t WHERE t.forum=?";
//		List<Object> patatemeters = new ArrayList<Object>();
//		patatemeters.add(forum);
//
//		if (viewType == 1) {
//			hql += " AND t.type=?";
//			patatemeters.add(Topic.TYPE_BEST);
//		}
//		if (orderBy == 0) {
//			hql += " ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime";
//		} else if (orderBy == 1) {
//			hql += " ORDER BY t.lastUpdateTime " + (reverse ? "ASC" : "DESC");
//		} else if (orderBy == 2) {
//			hql += " ORDER BY t.postTime " + (reverse ? "ASC" : "DESC");
//		} else {
//			hql += " ORDER BY t.replyCount " + (reverse ? "ASC" : "DESC");
//		}

		// 分页准备四

		new QueryHelper(Topic.class, "t")//
		.addWhereCondition("t.forum=?", forum)//
		.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST)//
		.addOrderCondition((orderBy == 1), "t.lastUpdateTime", reverse)//
		.addOrderCondition((orderBy == 2), "t.postTime", reverse)//
		.addOrderCondition((orderBy == 3), "t.replyCount", reverse)//
		.addOrderCondition((orderBy == 0), "t.lastUpdateTime", false)//
		.addOrderCondition((orderBy == 0),"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
		.preparePageBean(topicService, currentPage, pageSize);
		
		
		return "show";
	}

	/**
	 * 0 全部主题 
	 * 1 全部精华贴
	 */
	private int viewType = 0;
	/**
	 * 0 默认排序（按最后更新时间排序，但所有置顶帖都在前面） 
	 * 1 按最后更新时间排序 
	 * 2 按主题发表时间排序 
	 * 3 按回复数量排序
	 */
	private int orderBy = 0;
	/**
	 * true 升序 false 降序
	 */
	private boolean reverse = false;

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

}
