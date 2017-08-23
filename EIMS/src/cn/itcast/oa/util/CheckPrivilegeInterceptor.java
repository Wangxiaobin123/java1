package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) ActionContext.getContext().getSession()
				.get("user");

		String namespace = invocation.getProxy().getNamespace();
		String actionname = invocation.getProxy().getActionName();
		String privUrl = namespace + actionname;
		// 用户未登录
		if (user == null) {
			if (privUrl.startsWith("/user_login")) {
				// 如果是去登录，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，跳转到登录页面
				return "loginUI";

			}
		}else {// 用户登录，就判断是否有权限
			if (user.hasPrivilegeByUrl(privUrl)) {
				// 有权限，就放行
				return invocation.invoke();
			} else {
				// 没有权限，跳转到提示页面
				return "noPrivilegeError";
			}
		}
	}

}
