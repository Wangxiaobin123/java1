package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	
	
	private Long parentId;
	
	// 返回的列表
	public String list() {
		
		List<Department> departmentList = null;
		if(parentId == null){
			departmentList  = departmentService.getParentList();
			
		}else{
			//子列表
			departmentList = departmentService.getChildrenList(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
//		List<Department> departmentList = departmentService.getAll();
		
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	public String list2() {
		
		List<Department> departmentList = null;
		if(parentId == null){
			departmentList  = departmentService.getParentList();
			
		}else{
			//子列表
			departmentList = departmentService.getChildrenList(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
//		List<Department> departmentList = departmentService.getAll();
		
		ActionContext.getContext().put("departmentList", departmentList);
		return "list2";
	}
public String list3() {
		
		List<Department> departmentList = null;
		if(parentId == null){
			departmentList  = departmentService.getParentList();
			
		}else{
			//子列表
			departmentList = departmentService.getChildrenList(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
//		List<Department> departmentList = departmentService.getAll();
		
		ActionContext.getContext().put("departmentList", departmentList);
		return "list3";
	}
	// 添加
	public String add() {
		Department parent = departmentService.getById(parentId);
		
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	// 添加页面
	public String addUI() {
		
		List<Department> topList = departmentService.getParentList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	// 修改
	public String edit() {
		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		
		department.setParent(departmentService.getById(parentId));
		
		departmentService.update(department);
		return "toList";
	}

	// 修改页面
	public String editUI() {
		List<Department> topList = departmentService.getParentList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent() != null){
			parentId = department.getParent().getId();
		}
		
		return "saveUI";
	}

	// 删除
	public String delete() {
		departmentService.delete(model.getId());
		return "toList";
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String vote(){
		Department department = departmentService.getById(model.getId());
		department.setVoteNum((department.getVoteNum()+1));
		departmentService.update(department);
		return "vote";
	}
}
