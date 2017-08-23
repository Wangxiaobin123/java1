package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	List<Department> getAll();

	void save(Department model);


	void update(Department department);

	Department getById(Long id);

	void delete(Long id);
	
	
	
	/**
	 * 获取子列表
	 * @param parentId
	 * @return
	 */
	List<Department> getChildrenList(Long parentId);
	/**
	 * 返回顶级列表
	 * @return
	 */
	List<Department> getParentList();

}
