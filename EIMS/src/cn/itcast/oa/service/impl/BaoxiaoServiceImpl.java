package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Baoxiao;
import cn.itcast.oa.domain.Files;
import cn.itcast.oa.service.BaoxiaoService;


@Service
@Transactional
public class BaoxiaoServiceImpl extends DaoSupportImpl<Baoxiao> implements BaoxiaoService {
	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Baoxiao> getBaoxiao(Long userId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Baoxiao d where d.userId = ?")//
				.setParameter(0, userId)//
				.list();
	}
	

	

}
