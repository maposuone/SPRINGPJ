<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>リスト</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>NO.</td>
			<td>名前</td>
			<td>タイトル</td>
			<td>日付</td>
			<td>HIT</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.bId}</td>
				<td>${dto.bName}</td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> 
					<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a>
				</td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
			</tr>
		</c:forEach >
		<tr>
			<td colspan="5"><a href="write_view">作成</a></td>
		</tr>
	</table>
</body>
</html>
