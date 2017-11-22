<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>丫丫豆-都市信息平台</title>
<link href="/city_info/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link rel="stylesheet" href="../css/reset.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript">
	$(function() {

		$(".list").css({
			height : $(window).height() - 14
		});
		$(window).resize(function() {
			$(".list").css({
				height : $(window).height() - 14
			});
		});
		$(".list").find("li").click(function() {
			$(".list").find("li").removeClass();
			$(this).addClass("list-active");
		});

		/** 用户退出 */
		$("#exit").click(function() {
			if (confirm("您确定要退出系统吗？")) {
				parent.window.location = "${pageContext.request.contextPath}/user?method=loginOut";
			}
		});
	});
</script>
<style>
#left {
	width: 171px;
	padding: 0 0 0 9px;
	height: 100%;
}

.list {
	width: 146px;
	padding: 0 5px;
	border: #cbcbcb 1px solid;
	height: 100%;
	background: #f4f1e8;
}

.list li {
	height: 40px;
	cursor: pointer;
	padding-left: 18px;
	background: url(../images/leftBg.png) 130px center no-repeat;
	line-height: 40px;
	border-bottom: #c32c33 1px solid;
}

.list li:hover {
	background: url(../images/activeBg.png) left center no-repeat;
}

.list li:hover a {
	color: #f4f1e8;
}

.list a {
	color: #c32c33;
	display: block;
}

.list .list-active {
	background: url(../images/activeBg.png) left center no-repeat;
}

.list .list-active a {
	color: #f4f1e8;
}
</style>
</head>


<body>

	<div id="left">
		<div class="list">
			<ul>
				<li class="list-active">
					<a href="${pageContext.request.contextPath }/product?method=listAll" target="main">产品列表</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/product?method=toSave" target="main">产品添加</a>
				</li>
				<li>
					<a	href="${pageContext.request.contextPath }/news?method=listAll" target="main">新闻列表</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/news?method=toSave" target="main">新闻添加</a>
				</li>
				<li>
					<a href="javascript:void(0);" id="exit">系统退出</a>
				</li>
			</ul>
		</div>
	</div>

</body>
</html>
