package com.interceptors;
import org.apache.struts2.dispatcher.SessionMap;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
public class AuthorizationInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=inv.getInvocationContext();
		SessionMap map = (SessionMap) inv.getInvocationContext().getSession();
		if(context.getActionName().equalsIgnoreCase("loginpage")|| context.getActionName().equalsIgnoreCase("showRegister")||context.getActionName().equalsIgnoreCase("loginpage.action")
				||context.getActionName().equalsIgnoreCase("login")||context.getActionName().equalsIgnoreCase("register"))
	    {
	        return inv.invoke();
	    }
		if(map==null)
			return "login";
		
		Object user = map.get("username");      
	    if((user==null)||user.equals("") || map.isEmpty() || map == null   ){                  
	        return "login";     
	    }

	    return inv.invoke();
	}
	

}
