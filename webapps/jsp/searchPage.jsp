<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%  
response.setHeader("Cache-Control","no-store"); //HTTP 1.1  
response.setHeader("Pragma","no-cache"); //HTTP 1.0  
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>  
<%
    HttpSession sess = request.getSession(true);
	
	
	if (sess.getAttribute("username")==null)
    {
    	
    %>
        <jsp:forward page="/jsp/login.jsp?msg=You will have to login first in order to access other pages"></jsp:forward>
    <%
    }
  
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>SuccessLogin</title>
<script src="js/script2.js"></script>
</head>
<body>
	<s:form namespace="" method="POST">
    	<s:textfield id="search-tab" label = "Search for a word" name="searchWord" />
    	
    	<s:submit onclick="searchInFile(); return false;"/>
    	
	</s:form>
	<p id="searchResult"></p>
</body>
</html>