package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 根据topic获取回复情况
	 * @param topic
	 * @return
	 */
	List<Reply> getByTopic(Topic topic);

}
