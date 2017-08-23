package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService{
//	@Resource
//	private RoleDao roleDao;
//	
//	public List<Role> findAll() {
//		return roleDao.getAll();
//	}
//	public void delete(long id) {
//		roleDao.delete(id);
//	}
//	public void save(Role role) {
//		roleDao.save(role);
//	}
//	@Override
//	public Role getById(long id) {
//		return roleDao.getById(id);
//	}
//	@Override
//	public void update(Role role) {
//		roleDao.update(role);
//		
//	}

}
