<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%  
response.setHeader("Cache-Control","no-store"); //HTTP 1.1  
response.setHeader("Pragma","no-cache"); //HTTP 1.0  
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>  
<%
    HttpSession sess = request.getSession(true);
    if (sess.getAttribute("username")!=null)
    {
    	response.setIntHeader("Refresh", 1);
    	sess.setAttribute("username", null);
    	
    	
    }
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
<meta http-equiv="Content-Security-Policy" content="default-src * self blob: data: gap:; style-src * self 'unsafe-inline' blob: data: gap:; script-src * 'self' 'unsafe-eval' 'unsafe-inline' blob: data: gap:; object-src * 'self' blob: data: gap:; img-src * self 'unsafe-inline' blob: data: gap:; connect-src self * 'unsafe-inline' blob: data: gap:; frame-src * self blob: data: gap:;">
<title>Login</title>
</head>
<script src="js/script.js">
	
</script>
<body>
	
	<s:form namespace="" method="POST">
    	<s:textfield id="userName" label = "Username" name="user" />
    	<s:password id="password" label = "Password" name="password" />
    	<s:submit onclick="userValidation(); return false;"/>
    	
	</s:form>
	
  		
	<p id="error-msg"></p>
	<p>Not a user?<a href="<s:url action='showRegister'/>">Click here</a></p>
</body>
</html>