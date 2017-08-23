package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.User;

@Service
public class TestService {
	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void saveTwoUser(){
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
		//int a = 1/0;
		session.save(new User());
		
	}
	@Transactional
	public void saveMoreUser(){
		Session session = sessionFactory.getCurrentSession();
		for(int i = 0;i<20;i++){
			User user = new User();
			user.setLoginName("test"+i);
			user.setName("test"+i);
			session.save(user);
		}
		
	}
}
