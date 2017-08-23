package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.PageBean;

public class QueryHelper {
	private String fromClause;// from语句
	private String orderByClause = "";// order by 语句
	private String whereClause = "";// where语句

	//参数集合
	List<Object> parametes = new ArrayList<Object>();

	public QueryHelper(Class clazz, String alias) {
		// FROM User u
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 *  where 的添加
	 * @param condition where后的条件
	 * @param param 参数对象
	 * @return
	 */
	public QueryHelper addWhereCondition(String condition, Object... param) {
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		if (param != null) {
			for (Object p : param) {
				parametes.add(p);
			}
		}
		return this;
	}
	
	/**
	 *  where的添加条件
	 * @param append 分页时候的过滤条件
	 * @param condition where 后的条件
	 * @param param 参数对象
	 * @return
	 */
	public QueryHelper addWhereCondition(boolean append ,String condition, Object... param) {
		if(append){
			addWhereCondition(condition, param);
		}
		return this;
	}

	/**
	 * 排序
	 * @param properName order by 的属性
	 * @param reverse 根据返回的false或者true 判断升序或者是降序
	 * @return
	 */
	public QueryHelper addOrderCondition(String properName, boolean reverse) {
		if (orderByClause != null) {
			orderByClause = " ORDER BY " + properName//
					+ " " + (reverse ? "ASC" : "DESC");
		} else {
			orderByClause += ", " + properName //
					+ " "+ (reverse ? "ASC" : "DESC");
		}
		return this;
	}
	/**
	 * order by 的添加条件
	 * @param append 分页时候的过滤条件
	 * @param properName order by 的属性
	 * @param reverse 根据返回的false或者true 判断降序或者是升序
	 * @return
	 */
	public QueryHelper addOrderCondition(boolean append , String properName, boolean reverse) {
		if (append) {
			addOrderCondition(properName, reverse);
		}
		return this;
	}

	/**
	 * 返回查询语句 hql
	 * 
	 * @return
	 */
	public String getQueryList() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 返回记录总数语句 hql
	 * 
	 * @return
	 */
	public String getCountList() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	/**
	 * 返回pageBean 
	 * @param service 调用的业务
	 * @param currentPage 当前页
	 * @param pageSize 每页的条数
	 */
	public void preparePageBean(DaoSupport<?> service,int currentPage,int pageSize){
		PageBean pageBean = service.getBean(currentPage, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	public String getFromClause() {
		return fromClause;
	}

	public void setFromClause(String fromClause) {
		this.fromClause = fromClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public List<Object> getParametes() {
		return parametes;
	}

	public void setParametes(List<Object> parametes) {
		this.parametes = parametes;
	}
	
}
