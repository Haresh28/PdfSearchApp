package com.searching;
import com.fasterxml.jackson.core.*; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.action.*;

import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class SearchForWord implements ServletRequestAware,ServletResponseAware {
	@JsonIgnore
	String sampleJsonString="{apple:a fruit,pen:an instrument used to write}";
	@JsonIgnore
	JSONObject obj=new JSONObject(sampleJsonString);
	HttpServletRequest httpServletReq=null;
	HttpServletResponse httpServletResp=null;
	@JsonIgnore
	@Override
	public void withServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		httpServletReq=arg0;
	}
	@JsonIgnore
	@Override
	public void withServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		httpServletResp=arg0;
		
	}
	
	
	@JsonIgnore
	public String wordSearch() throws Exception {
		
		BufferedReader reader= httpServletReq.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String searchJsonString = sb.toString();
		
		System.out.println(searchJsonString);
		
		JSONObject searchJson=new JSONObject(searchJsonString);
		String searchData=searchJson.getString("word").toLowerCase();
		try {
		
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "H@resh2810");
			ResultSet rs= conn.createStatement().executeQuery("SELECT meaning FROM "+searchData.charAt(0)+ " WHERE word='"+searchData+"'");
			Boolean result=rs.next();
			if(result) {
				String meaning=rs.getString("meaning");
				JSONObject json=new JSONObject();
				json.put("response",meaning );
				PrintWriter out =httpServletResp.getWriter();
				httpServletResp.setContentType("application/json");
				httpServletResp.setCharacterEncoding("UTF-8");
				out.print(json.toString());
				out.flush();
			}
		
			else {
				JSONObject json=new JSONObject();
				json.put("response","Data not found");
				PrintWriter out =httpServletResp.getWriter();
				httpServletResp.setContentType("application/json");
				httpServletResp.setCharacterEncoding("UTF-8");
				out.print(json.toString());
				out.flush();
			}
			
			/*if(obj.getString(searchData) != null) {
				JSONObject json=new JSONObject();
				json.put("response",obj.getString(searchData) );
				PrintWriter out =httpServletResp.getWriter();
				httpServletResp.setContentType("application/json");
				httpServletResp.setCharacterEncoding("UTF-8");
				out.print(json.toString());
				out.flush();
			}*/
			
		}
		catch(Exception e) {
			//e.printStackTrace();
			JSONObject json=new JSONObject();
			json.put("response","Data not found");
			PrintWriter out =httpServletResp.getWriter();
			httpServletResp.setContentType("application/json");
			httpServletResp.setCharacterEncoding("UTF-8");
			out.print(json.toString());
			out.flush();
			
		}
		return "success";
	}

}
