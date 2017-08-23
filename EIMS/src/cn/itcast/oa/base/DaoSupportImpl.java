package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.QueryHelper;

@SuppressWarnings("unchecked")
@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	// 通过反射技术获取T的真实类型
	public DaoSupportImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()//
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		// System.out.println("clazz---->"+clazz+"......"+pt);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);

	}

	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		// 判断，当ids为空的时候,返回一个空的集合
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " where id in(:ids)")//
					.setParameterList("ids", ids).list();
		}
	}

	@Override
	public List<T> getAll() {

		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	@Override
	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	@Override
	@Deprecated
	public PageBean getBean(int currentPage, int pageSize, String hql,
			List<Object> patatemeters) {
		Query listQuery = getSession().createQuery(hql);
		if (patatemeters != null) {
			for (int i = 0; i < patatemeters.size(); i++) {
				listQuery.setParameter(i, patatemeters.get(i));
			}
		}
		listQuery.setFirstResult((currentPage - 1) * pageSize);
		listQuery.setMaxResults(pageSize);

		List<Object> recordList = listQuery.list();

		Query countQuery = getSession().createQuery("SELECT COUNT(*) " + hql);
		if (patatemeters != null) {
			for (int i = 0; i < patatemeters.size(); i++) {
				countQuery.setParameter(i, patatemeters.get(i));
			}
		}

		Long recordCount = (Long) countQuery.uniqueResult();
		return new PageBean(currentPage, pageSize, recordCount.intValue(),
				recordList);
	}

	@Override
	public PageBean getBean(int currentPage, int pageSize,
			QueryHelper queryHelper) {
		Query listQuery = getSession().createQuery(queryHelper.getQueryList());
		List<Object> patatemeters = queryHelper.getParametes();
		if (patatemeters != null) {
			for (int i = 0; i < patatemeters.size(); i++) {
				listQuery.setParameter(i, patatemeters.get(i));
			}
		}
		listQuery.setFirstResult((currentPage - 1) * pageSize);
		listQuery.setMaxResults(pageSize);

		List<Object> recordList = listQuery.list();

		Query countQuery = getSession().createQuery(queryHelper.getCountList());
		if (patatemeters != null) {
			for (int i = 0; i < patatemeters.size(); i++) {
				countQuery.setParameter(i, patatemeters.get(i));
			}
		}

		Long recordCount = (Long) countQuery.uniqueResult();
		return new PageBean(currentPage, pageSize, recordCount.intValue(),
				recordList);
	}
}
