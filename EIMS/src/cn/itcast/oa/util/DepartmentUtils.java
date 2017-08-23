package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		
		walkDepartmentTreeList(topList, "┠", list);
		return list;
	}
	private static void walkDepartmentTreeList(Collection<Department> topList,String prefix,List<Department> list){
		for(Department top : topList){
			//使用一个副本，避免在session中修改数据库中数据
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix+top.getName());
			//添加到List
			list.add(copy);
			walkDepartmentTreeList(top.getChildren(), "　"+prefix, list);
			
		}
	}

}
