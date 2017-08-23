package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> getByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		getSession().save(reply);
		//相关属性的维护
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();
		
		topic.setLastReply(reply);
		topic.setLastUpdateTime(topic.getPostTime());
		topic.setReplyCount(topic.getReplyCount()+1);
		
		forum.setArticleCount(forum.getArticleCount()+1);
		
		
		getSession().update(forum);
		getSession().update(topic);
		
		
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public PageBean getPageBeanByTopic(int currentPage, int pageSize,
//			Topic topic) {
//		List<Reply> recordList= getSession().createQuery(//
//				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime")//
//				.setParameter(0, topic)//
//				.setFirstResult((currentPage-1)*pageSize)//
//				.setMaxResults(pageSize)
//				.list();
//		
//		Long recordCount = (Long) getSession().createQuery(
//				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
//				.setParameter(0, topic)//
//				.uniqueResult();
//		return new PageBean(currentPage, pageSize, recordCount.intValue(), recordList);
//	}
//	

}
