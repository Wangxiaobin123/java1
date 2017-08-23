package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getByForum(Forum forum) {
		return getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		
		//特殊属性
		topic.setType(Topic.TYPE_NORMAL);
		topic.setLastReply(null);
		topic.setReplyCount(0);
		
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic);
		
		//维护相关属性
		Forum forum  = topic.getForum();
		forum.setArticleCount(forum.getArticleCount()+1);
		forum.setTopicCount(forum.getTopicCount()+1);
		forum.setLastTopic(topic);
		
		getSession().update(forum);
	}

	
	
	//@Override
//	public PageBean getBeanByForum(int currentPage, int pageSize, Forum forum) {
//		List<Topic> recordList = getSession().createQuery(//
//				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
//				.setParameter(0, forum)//
//				.setFirstResult((currentPage-1)*pageSize)//
//				.setMaxResults(pageSize)//
//				.list();
//		Long recordCount = (Long) getSession().createQuery(//
//				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
//				.setParameter(0, forum)//
//				.uniqueResult();
//		return new PageBean(currentPage, pageSize, recordCount.intValue(), recordList);
//	
//	}
	

}
