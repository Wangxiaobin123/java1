package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	List<User> getAll();

	void save(User model);


	void update(User User);

	User getById(Long id);

	void delete(Long id);

	User getByLoginNameAndPassword(String name, String password);

}
