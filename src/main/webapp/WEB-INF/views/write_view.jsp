<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WRITE VIEW</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="write" method="POST">
			<tr>
				<td>名前</td>
				<td><input type="text" name="bName" size="50"> </td>
			</tr>
			<tr>
				<td>タイトル</td>
				<td><input type="text" name="bTitle" size="50"> </td>
			</tr>
			<tr>
				<td>内容</td>
				<td><textarea rows="10" name="bContent" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" ><input type="submit" value="入力"> &nbsp;&nbsp; <a href="list">リスト</a></td>
			</tr>
		</form>
	</table>
</body>
</html>
