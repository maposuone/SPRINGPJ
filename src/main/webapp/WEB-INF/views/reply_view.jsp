<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reply View</title>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}"> 
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}"> 
			<input type="hidden" name="bStep" value="${reply_view.bStep}"> 
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<td>NO.</td>
				<td> ${reply_view.bId} </td>
			</tr>
			<tr>
				<td>Hit</td>
				<td> ${reply_view.bHit} </td>
			</tr>
			<tr>
				<td>名前</td>
				<td><input type="text" name="bName" >
				</td>
			</tr>
			<tr>
				<td>タイトル</td>
				<td><input type="text" name="bTitle" ></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><textarea rows="10" name="bContent" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="回答"><a href="list">LIST</a></td>
			</tr>
		</form>

	</table>
</body>
</html>