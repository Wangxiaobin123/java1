package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sc) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilegeList = privilegeService.getTopList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		
		System.out.println("已经加载数据");
		Collection<String> allPrivilegeUrlList = privilegeService.getALLPrivilegeUrlList();
		sce.getServletContext().setAttribute("allPrivilegeUrlList", allPrivilegeUrlList);
		System.out.println("已经加载所有权限URL");
	}

}
