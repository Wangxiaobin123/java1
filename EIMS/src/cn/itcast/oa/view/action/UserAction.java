package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.relation.RoleList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Baoxiao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Files;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;
import cn.itcast.oa.util.HttpClientUtil;
import cn.itcast.oa.util.QueryHelper;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private String smsContent;
	private String shenpi;
	

	private Long departmentId;
	private String repassword="";
	
	
	public String getShenpi() {
		return shenpi;
	}
	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	private Long[] ids;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	//列表
	public String list(){
//		List<User> userList = userService.getAll();
//		ActionContext.getContext().put("userList", userList);
		//User user = userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		new QueryHelper(User.class, "u").preparePageBean(userService, currentPage, pageSize);
		return "list";
		
	}
	public String list2(){
//		List<User> userList = userService.getAll();
//		ActionContext.getContext().put("userList", userList);
		//User user = userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		new QueryHelper(User.class, "u").preparePageBean(userService, currentPage, pageSize);
		List<User>  userList = userService.getAll();
		ActionContext.getContext().put("userList", userList);
		return "list2";
		
	}
	public String list3(){
//		List<User> userList = userService.getAll();
//		ActionContext.getContext().put("userList", userList);
		//User user = userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		new QueryHelper(User.class, "u").preparePageBean(userService, currentPage, pageSize);
		return "list3";
		
	}
	//删除
	public String delete(){
		userService.delete(model.getId());
		return "toList";
		
	}
	//修改
	@SuppressWarnings("unused")
	public String edit(){
		User user = userService.getById(model.getId());
		
		String password = user.getPassword();
		String pwd = model.getPassword();
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		if(!pwd.equals(repassword)){
			addFieldError("password", "您的用户名或密码错误！");
			new QueryHelper(User.class, "u").preparePageBean(userService, currentPage, pageSize);
			return "list";
		}else if(pwd == null ){
			user.setPassword(password);
		}else{
			pwd = DigestUtils.md5Hex(pwd);
			user.setPassword(pwd);
		}
		
		user.setDescription(model.getDescription());
		user.setPhone(model.getPhone());
		
		user.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(ids);
		user.setRoles(new HashSet<Role>(roleList));
		userService.update(user);
		return "toList";
	}
	
	@SuppressWarnings("unused")
	public String edit2(){
		User user = userService.getById(model.getId());
		
		String password = user.getPassword();
		String pwd = model.getPassword();
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		if(pwd != null ){
			if(!pwd.equals(repassword)){
				DigestUtils.md5(pwd);
				user.setPassword(pwd);
			}	
			
		}else{
			//DigestUtils.md5(pwd);
			user.setPassword(password);
		}
		user.setDescription(model.getDescription());
		user.setPhone(model.getPhone());
			
		user.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(ids);
		user.setRoles(new HashSet<Role>(roleList));
		userService.update(user);
		return "toList2";
		
		
	}
	public String editUI2(){
		List<Department> topList = departmentService.getParentList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);

		
		User user = userService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(user);
		
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles() != null){
			ids = new Long[user.getRoles().size()];
			int index = 0;
			for(Role roles : user.getRoles()){
				ids[index++] = roles.getId();
			}
		}
		
		return "saveUI2";
	}
	//修改页面
	public String editUI(){
		List<Department> topList = departmentService.getParentList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);

		
		User user = userService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(user);
		
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles() != null){
			ids = new Long[user.getRoles().size()];
			int index = 0;
			for(Role roles : user.getRoles()){
				ids[index++] = roles.getId();
			}
		}
		
		return "saveUI";
	}
	//添加
	public String add(){
		model.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(ids);
		
		model.setRoles(new HashSet<Role>(roleList));
		
		String md5Message = DigestUtils.md5Hex("1234");
		model.setPassword(md5Message);
		
		userService.save(model);
		return "toList";
	}
	//添加页面
	public String addUI(){
		List<Department> topList = departmentService.getParentList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);;
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

		
		private String username;
	    private String cause;
	    
	    public String getCause() {
			return cause;
		}
		public void setCause(String cause) {
			this.cause = cause;
		}

		//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	    private File file;
	    
	    //提交过来的file的名字
	    private String fileFileName;
	    
	    //提交过来的file的MIME类型
	    private String fileContentType;

	    public String getUsername()
	    {
	        return username;
	    }

	    public void setUsername(String username)
	    {
	        this.username = username;
	    }

	    public File getFile()
	    {
	        return file;
	    }

	    public void setFile(File file)
	    {
	        this.file = file;
	    }

	    public String getFileFileName()
	    {
	   
	        return fileFileName;
	    }

	    public void setFileFileName(String fileFileName)
	    {
	    	
	        this.fileFileName = fileFileName;
	    }

	    public String getFileContentType()
	    {
	        return fileContentType;
	    }

	    public void setFileContentType(String fileContentType)
	    {
	        this.fileContentType = fileContentType;
	    }
	    public String addUI2(){
			
			return "holiday";
		}
	    public String upload() throws Exception
	    {
	        String root = ServletActionContext.getServletContext().getRealPath("/upload");
	        
	        System.out.println("root: " + root);
	        InputStream is = new FileInputStream(file);
	        
	        OutputStream os = new FileOutputStream(new File(root, fileFileName));
	        
	        System.out.println("fileFileName: " + fileFileName);

	        // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
	        System.out.println("file: " + file.getName());
	        System.out.println("file: " + file.getPath());
	        
	        byte[] buffer = new byte[500];
	        int length = 0;
	        
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        User user = (User) ActionContext.getContext().getSession().get("user");
	        Baoxiao baoxiao = new Baoxiao();
			baoxiao.setName(username);
			baoxiao.setCause(cause);
			baoxiao.setPath("upload/"+fileFileName);
			baoxiao.setUserId(user.getId());
			
			baoxiao.setUsername(user.getName());
	        baoxiaoService.save(baoxiao);
	        os.close();
	        is.close();
	        
	        return "toBaoxiaoList";
	    }
	    public String baoxiao(){
	    	List<Baoxiao> baoxiaoList = baoxiaoService.getAll();
	    	ActionContext.getContext().put("baoxiaoList", baoxiaoList);
	    	return "baoxiaoList";
	    }
	    public String voteBaoxiao(){
	    	Baoxiao baoxiao = baoxiaoService.getById(model.getId());
	    	baoxiao.setShenpi(shenpi);
			
			baoxiaoService.update(baoxiao);
			return "voteBaoxiaoList";
		}
	    public String baoxiaoUser(){
	    	User user = (User) ActionContext.getContext().getSession().get("user");
	    	
	    	List<Baoxiao> baoxiaoList = baoxiaoService.getBaoxiao(user.getId());
	    	ActionContext.getContext().put("baoxiaoList", baoxiaoList);
			return "baoxiaoUser";
			
			
		}
	//初始化密码
	public String initPassword(){
		User user = userService.getById(model.getId());
		String md5Message = DigestUtils.md5Hex("1234");
		user.setPassword(md5Message);
		userService.update(user);
		return "toList";
	}
	//登录 
	public String login(){
		User user = userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user == null){
			addFieldError("login", "您的用户名或密码错误！");
			return "loginUI";
		}else{
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
		
	}
	//登录页面
	public String loginUI(){
		
		return "loginUI";
	}
	//注销
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	public String sendSMS(){
		HttpClientUtil client = HttpClientUtil.getInstance();
		User user = userService.getById(model.getId());

		//UTF发送
		int result = client.sendMsgUtf8("zwtwjn", "d81671d11b69d3adcea9", smsContent, user.getPhone());
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
		}else{
			System.out.println(client.getErrorMsg(result));
		}
		return "toList";
	}
	public String vote(){
		User user = userService.getById(model.getId());
		user.setVoteNum((user.getVoteNum()+1));
		userService.update(user);
		return "vote";
	}
}
