package cn.itcast.oa.service;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;


public interface ForumService extends DaoSupport<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);

}
