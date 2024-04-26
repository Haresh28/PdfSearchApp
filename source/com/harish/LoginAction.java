package com.harish;
import org.json.simple.*;
import com.searching.BuildTables;
import java.net.*;
import java.io.*;
import javax.servlet.http.*;
import org.apache.struts2.action.*;
import com.opensymphony.xwork2.ActionSupport;
import com.validate.*;
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private String user;
	private String password;
	private String message;
	HttpServletRequest httpServletRequest=null;
	HttpServletResponse httpServletResponse=null;
	@Override
	public void withServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		httpServletRequest=arg0;
	}
	@Override
	public void withServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		httpServletResponse=arg0;
		
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String initLoginPage () throws Exception {
		BuildTables obj= new BuildTables();
		obj.pdfExtraction();
		return SUCCESS;
	}
	public String successfulLogin() {
		return SUCCESS;
	}
	/*public String loginResult() throws IOException {
		/*PrintWriter writer = httpServletResponse.getWriter();
        writer.println();
		BufferedReader reader= httpServletRequest.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String data = sb.toString();
		
		
		System.out.print(data);
		ValidateUser validateuser=new ValidateUser();
		return "success";
		/*if(validateuser.validateAccount(newUser,newPassword)) {
			JSONObject object=new JSONObject();
			object.put("message", "success");
			return "success";
			
		}
		else {
			JSONObject object=new JSONObject();
			object.put("message", "error");
			return "success";
		}
	}*/
	


	
}
