package cn.itcast.oa.service;

import java.util.Collection;
import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Privilege;


public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> getTopList();

	Collection<String> getALLPrivilegeUrlList();

	

}
