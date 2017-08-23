package cn.itcast.oa.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.QueryHelper;


public interface DaoSupport<T> {
	/**
	 * 保存实体
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 修改实体信息
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 根据ID查询信息
	 * @param id
	 * @return
	 */
	public T getById(Long id);
	
	/**
	 * 根据给定的ID数组查询实体信息列表
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Long[] ids);
	
	/**
	 * 查询所有实体信息
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void delete(Long id );
	
	/**
	 * 分页的方法
	 * @param currentPage 当前页
	 * @param pageSize 每页的条数
	 * @param forum 分页的对象
	 * @return
	 */
	@Deprecated
	PageBean getBean(int currentPage, int pageSize,String hql,List<Object> patatemeters);

	public PageBean getBean(int currentPage, int pageSize, QueryHelper queryHelper);
}
