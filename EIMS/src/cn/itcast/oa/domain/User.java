package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable {
	private Long id;
	private String name;
	private String description;
	private String loginName;
	private String password;
	private String gender;
	private String phone;
	private String email;
	private Department department;
	private Set<Role> roles;
	private Long voteNum;
	private String vote;
	
	public String getVote() {
		return vote;
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	public Long getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(Long voteNum) {
		this.voteNum = voteNum;
	}
	//判断用户拥有的权限
	public boolean hasPrivilegeByName(String name){
		//判断超级管理员
		if(isAdmin()){
			return true;
		}
		//判断普通管理员
		for(Role role :roles){
			for(Privilege privileges : role.getPrivileges()){
				if(privileges.getName().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isAdmin() {
		
		return "admin".equals(loginName);
	}
	
	//判断用户拥有的权限链接
		public boolean hasPrivilegeByUrl(String privUrl){
			if(isAdmin()){
				return true;
			}
			//去掉后面的参数
	    	int index = privUrl.indexOf("?");
	    	if(index > -1){
	    		privUrl = privUrl.substring(0, index);
	    	}
	    	//去掉UI
	    	if(privUrl.endsWith("UI")){
	    		privUrl = privUrl.substring(0, privUrl.length()-2);
	    	}
	    	
			Collection<String> allPrivilegeUrlList = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrlList");
			//判断本身不需要控制的URL
			if(!allPrivilegeUrlList.contains(privUrl)){
				return true;
			}else{
				//判断普通管理员
				for(Role role :roles){
					for(Privilege privileges : role.getPrivileges()){
						if(privUrl.equals(privileges.getUrl())){
							return true;
						}
					}
				}
				return false;
			}
			
		}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
