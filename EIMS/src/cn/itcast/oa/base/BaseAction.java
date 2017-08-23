package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.BaoxiaoService;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.FilesService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// ============对ModelDriven的声明==========//
	protected T model;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		// 使用反射技术
		try {
			// 获得T的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// ============对service的声明==========//
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected FilesService filesService;
	@Resource
	protected BaoxiaoService baoxiaoService;
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	// 分页数据
	protected int currentPage = 1;
	protected int pageSize = 10;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
