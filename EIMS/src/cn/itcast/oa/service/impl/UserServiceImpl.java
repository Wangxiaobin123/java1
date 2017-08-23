package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	@Override
	public User getByLoginNameAndPassword(String name, String password) {
		String MD5Message = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u where u.loginName=? AND u.password=?")//
				.setParameter(0, name)//
				.setParameter(1, MD5Message)//
				.uniqueResult();
	}

}
