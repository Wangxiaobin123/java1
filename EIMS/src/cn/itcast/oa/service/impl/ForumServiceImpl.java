package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements
		ForumService {

	@Override
	public void save(Forum forum) {
		// 保存的时候设置position的值，重写方法
		super.save(forum);
		forum.setPosition(forum.getId().intValue());
	}

	@Override
	public List<Forum> getAll() {
		// 根据position获取
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	}

	@Override
	public void moveUp(Long id) {
		// 向上移
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		// 最上面的不移动
		if (other == null) {
			return;
		}
		// 修改position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新
		getSession().update(forum);
		getSession().update(other);
	}

	@Override
	public void moveDown(Long id) {
		// 向下移
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		// 最下面的不移动
		if (other == null) {
			return;
		}
		// 修改position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新
		getSession().update(forum);
		getSession().update(other);

	}

}
