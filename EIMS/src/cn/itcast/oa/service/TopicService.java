package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	List<Topic> getByForum(Forum forum);

}
