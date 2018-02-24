<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>chat 2.0</title>
	</head>
	<body>
		<pre>
<%= request.getAttribute("content") %>
		</pre>
	
		<form name="chatForm" action="chat" method="post">
			<input type="text" name="ligne" value="" /> 
			<input type="submit" name="action" value="submit" /> 
			<input type="submit" name="action" value="refresh" />
		</form>
		
		<form name="logout" action="logout" method="post">
			<input type="submit" name="action" value="logout" />
		</form>
	</body>
</html>