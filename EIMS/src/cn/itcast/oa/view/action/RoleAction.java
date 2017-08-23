package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Long[] privilegeIds;

	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/** 返回列表方法 */
	public String list() throws Exception {
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除方法 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 增加方法 */
	public String add() throws Exception {
		roleService.save(model);
		return "toList";
	}

	/** 增加页面方法 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 修改方法 */
	public String edit() throws Exception {
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}

	/** 修改页面方法 */
	public String editUI() throws Exception {
		// 回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	// 设置权限
	public String setPrivilege() {
		// 1.从数据库取出对象
		Role role = roleService.getById(model.getId());
		// 2. 修改权限
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		// 3.更新到数据库
		roleService.update(role);
		return "toList";

	}

	// 设置权限页面
	public String setPrivilegeUI() {
		// 回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}

		}

		// 准备数据
		List<Privilege> privilegeList = privilegeService.getAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		

		return "setPrivilegeUI";
	}

}
