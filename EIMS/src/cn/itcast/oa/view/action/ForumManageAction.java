package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Files;
import cn.itcast.oa.domain.Forum;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {
	/** 列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.getAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	public String list2() throws Exception {
		
		return "file";
	}
	/** 删除 */
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {

		forumService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 回显数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Forum forum = forumService.getById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumService.update(forum);

		return "toList";
	}

	// 上移
	public String moveUp() {
		forumService.moveUp(model.getId());
		return "toList";
	}

	// 下移
	public String moveDown() {
		forumService.moveDown(model.getId());
		return "toList";
	}
	private String username;
    
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
        Files files = new Files();
        files.setName(username);
     
        
        files.setPath("upload/"+fileFileName);
        filesService.save(files);
        os.close();
        is.close();
        
        return "toFileList";
    }
    public String ziliao(){
    	List<Files> filesList =  filesService.getAll();
    	ActionContext.getContext().put("filesList", filesList);
    	return "fileList";
    }
}
