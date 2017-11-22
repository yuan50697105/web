<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base target="main">
<meta charset="utf-8">
<title>丫丫豆-都市信息平台</title>
<link href="/city_info/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/reset.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/commons.js"></script>
<style>
#wrapper {
	width: 98%;
	border: #cbcbcb 1px solid;
	padding-top: 17px;
	padding-left: 14px;
	background: #f4f1e8;
}

#header {
	padding-bottom: 18px;
}

.box {
	width: 99%;
	border: #cbcbcb 1px solid;
	background: #ffffff;
}

.tab {
	color: #c32c33;
	margin: 0 auto;
}

.tab td {
	text-align: center;
	height: 40px;
	padding: 0 10px;
}

.tab th {
	background: url('${pageContext.request.contextPath}/images/tabThBg.png')
		right center no-repeat;
	padding: 13px 10px;
}

.field {
	cursor: pointer;
}

#header .search {
	float: left;
}

.select {
	float: left;
	vertical-align: middle;
}

.select select {
	height: 32px;
	line-height: 32px;
	vertical-align: middle;
}
</style>
<script>

			/** 当前文档加载完 */
			$(function(){
				/** 为全选绑定点击事件 */
				$(".checked").click(function(){
					$(".checkbox").attr("checked", this.checked);
				});
				/** 获取发布按钮绑定点击事件 */
				$("#publishBtn").click(function(){
					/** 获取下面所有选中的checkbox */
					var boxs = $("input[id^='box_']:checked");
					if (boxs.length == 0){
						alert("请选择要发布的信息！");
					}else{
						if (confirm("您确定要发布选中的信息？")){
							var ids = boxs.map(function(){
								return this.value;
							});
							window.location = "${pageContext.request.contextPath}/news?method=publish&ids=" + ids.get();
						}
					}
				});
				
				/** 定义提示信息 */
				var tip = "${tip}";
				if (tip != ""){
					alert(tip);
					${tip=null}
				}
			});
			
			/** 删除信息 */
			var deleteFn = function(id){
				if (confirm("您确定要删除吗？")){
					window.location = "${pageContext.request.contextPath}/news?method=delete&id=" + id;
				}
			};
			/** 修改信息 */
			var updateFn = function(id){
				window.location = "${pageContext.request.contextPath}/news?method=toUpdate&id=" + id;
			};
			var allPublish=function(){
				if(confirm("您确定要发布所有的信息？")){
					 window.location="${pageContext.request.contextPath}/news?method=allPublish";
				}
			};
		</script>
</head>
<body>

	<div id="wrapper">
		<div id="header" class="clear">
			<div class="search">
				<form action="${pageContext.request.contextPath }/news" method="post" id="searchForm">
					<input type="hidden" name="method" value="listByName" /> 
					<input type="text" value="${title }" id="title" name="title" class="text">
					<br>
					<input type="submit" value="" class="submit">
				</form>
			</div>
			<div class="export">
				<input type="button" value="发布" id="publishBtn" class="selectBtn">
				<input type="button" value="全部发布" id="allPublish" class="selectBtn" onclick="allPublish()">
			</div>
		</div>

		<div class="box">
			<table width="98%" class="tab">
				<tr style="border-bottom: #c32c33 1px solid;">
					<th><input type="checkbox" name="" class="checked"></th>
					<th>产品类型</th>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>发布时间</th>
					<th>发布状态</th>
					<th>发布人</th>
					<th style="background: none;" colspan="2">操作</th>
				</tr>
				<!-- 迭代数据 -->

				<c:forEach items="${news}" var="n">

					<tr class="field" style="border-bottom: #f3d5d6 1px dashed;">
						<td><input class="checkbox" value="${n.id}" id="box_${n.id }"
							type="checkbox" /></td>
						<td>${n.newsType.name }</td>
						<td>${n.id }</td>
						<td>${n.title}</td>
						<td>${n.createTime}</td>
						<td>${n.publishTime }</td>
						<td>
							<!-- 相当于java if else if --> <c:choose>
								<c:when test="${n.publishStatus == 0}">
									<font color="blue">未发布</font>
								</c:when>
								<c:when test="${n.publishStatus == 1}">
									<font color="red">已发布</font>
								</c:when>
								<c:when test="${n.publishStatus == 2}">
									<font color="green">停止发布</font>
								</c:when>
							</c:choose>
						</td>
						<td>${n.user.name}</td>
						<td><a href="javascript:void(0);"
							onclick="deleteFn(${n.id});" style="color: blue;">删除</a></td>
						<td><a href="javascript:void(0);"
							onclick="updateFn(${n.id});" style="color: blue;">修改</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 判断总页数不等于1时，显示分页信息 -->

		<div align="center">
			<!-- 控制首页 与 上一页  -->
			<c:if test="${ps != 1 }">
				【<a
						href="${pageContext.request.contextPath }/news?method=listByName&page=1&title=${title}"
						style="text-decoration: underline; color: blue;">首页</a>】
					【<a
						href="${pageContext.request.contextPath }/news?method=listByName&page=${page-1 le 1?1:page-1 }&title=${title}"
						style="text-decoration: underline; color: blue;">上一页</a>】
				<!-- 控制下一页 与 尾页-->
					【<a
						href="${pageContext.request.contextPath }/news?method=listByName&page=${page+1 ge ps?ps:page+1 }&title=${title}"
						style="text-decoration: underline; color: blue;">下一页</a>】 
					【<a
						href="${pageContext.request.contextPath }/news?method=listByName&page=${ps}&title=${title}"
						style="text-decoration: underline; color: blue;">尾页</a>】		
			</c:if>
		</div>

	</div>
	
</body>
</html>