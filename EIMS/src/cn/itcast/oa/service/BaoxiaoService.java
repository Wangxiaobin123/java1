package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Baoxiao;


public interface BaoxiaoService extends DaoSupport<Baoxiao> {
	List<Baoxiao> getBaoxiao(Long userId);
	
	
}
