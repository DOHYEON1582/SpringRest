<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="false" contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			//버튼 클릭시, 데이터 생성+데이터 전송
			$('#btnSend').click(function(){
				alert('버튼 클릭!');
				
				//객체 생성
				var member = {
						"userid" : "itwill",
						"username" : "아이티윌",
						"userpw" : "12345",
						"useremail" : "itwill@itwill.com",
				};
				//console.log(member);
				
				// ajax를 사용 정보 전달
				$.ajax({
					type : "post",
					url : "/test/add",
					contentType : "application/json",
					data : JSON.stringify(member), //JSON객체 -> JSON 문자열로 변경
					success:function(){
						alert(" /test/add 페이지 다녀옴! ");
						
					}
				});
				
			});
		});
	
	</script>
	
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input id="btnSend" type="button" value="회원정보 전송하기">


</body>
</html>
