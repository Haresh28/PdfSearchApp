package com.fetch;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import org.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.action.*;

import com.opensymphony.xwork2.ActionContext;
import com.validate.ValidateUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class FetchDataFromApi implements ServletRequestAware,ServletResponseAware {
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
	public void loginResult() throws IOException, ServletException  {
		/*PrintWriter writer = httpServletResponse.getWriter();
        writer.println();*/
		BufferedReader reader= httpServletRequest.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String incomingData = sb.toString();
		
		
		System.out.print(incomingData);
		
		JSONObject json=new JSONObject(incomingData);
		String userName=json.getString("user");
		String userPassword=json.getString("password");
		
		
		
		ValidateUser validateuser=new ValidateUser();
		
		if(validateuser.validateAccount(userName,userPassword)) {
			Map<String,Object> session=ActionContext.getContext().getSession();
			session.put("username", userName);
			
			JSONObject object=new JSONObject();
			object.put("message", "success");
			PrintWriter out =httpServletResponse.getWriter();
			httpServletResponse.setContentType("application/json");
			httpServletResponse.setCharacterEncoding("UTF-8");
			out.print(object.toString());
			out.flush();
			//httpServletResponse.sendRedirect("success");
			
			
		}
		else {
			JSONObject object=new JSONObject();
			object.put("message", "error");
			PrintWriter out =httpServletResponse.getWriter();
			httpServletResponse.setContentType("application/json");
			httpServletResponse.setCharacterEncoding("UTF-8");
			
			out.print(object.toString());
			out.flush();
		}
		
	}
}
