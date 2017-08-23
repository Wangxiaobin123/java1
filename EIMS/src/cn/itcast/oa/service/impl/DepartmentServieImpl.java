package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServieImpl extends DaoSupportImpl<Department> implements DepartmentService{
	@Resource
	private SessionFactory sessionFactory;
//	@Resource
//	private DepartmentDao departmentDao;
//	
//	@Override
//	public List<Department> getAll() {
//		return departmentDao.getAll();
//	}
//
//	@Override
//	public void save(Department model) {
//		departmentDao.save(model);
//	}
//
//	@Override
//	public void update(Department department) {
//		departmentDao.update(department);
//		
//	}
//
//	@Override
//	public Department getById(Long id) {
//		return departmentDao.getById(id);
//	}
//
//	@Override
//	public void delete(Long id) {
//		departmentDao.delete(id);
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getChildrenList(Long parentId) {
		
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d where d.parent.id = ?")//
				.setParameter(0, parentId)//
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getParentList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d where d.parent IS NULL")//
				.list();
	}

}
