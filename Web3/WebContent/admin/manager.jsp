<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>后台管理</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
</head>
<frameset rows="52,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/admin/top.jsp" name="title" scrolling="no" noresize="noresize">
  <frameset cols="180,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/admin/left.jsp" name="tree" scrolling="no" marginheight="0" marginwidth="0">
    <frame src="${pageContext.request.contextPath }/product?method=listProduct" name="main" scrolling="yes" frameborder="0" marginwidth="0" marginheight="0" noresize="noresize">
  </frameset>
</frameset>
<noframes></noframes>
<body>
</body>
</html>