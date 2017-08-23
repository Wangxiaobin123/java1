package cn.itcast.oa.domain;

import java.io.Serializable;

public class Baoxiao implements Serializable {
	private Long id;
	private String name;
	private String path;
	private String types;
	private String cause;
	private Long userId;
	private String username;
	private String shenpi;
	
	
	public String getShenpi() {
		return shenpi;
	}
	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
