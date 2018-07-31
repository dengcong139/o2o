<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>


<form id="form" action="${pageContext.request.contextPath}/area" method="get">
<input type="text" name="name" value=""/>
<input type="date" name="createtime" id="createtime" value=""/><br/>
<button id="my-submit" type="button" >111111</button>
<h1>111111</h1>

</form>


<script type="text/javascript">
window.onload=function(){
	 
	
	
	$('#my-submit').click(function(){
		alert(1111);
		$('#form').submit();
		
		
	});
}

</script>

</body>
</html>