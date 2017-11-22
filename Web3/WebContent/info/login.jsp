<!doctype html>
<html lang="zh-cn">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/images/yydou.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/controls.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<!-- jquery-migrate-1.2.1.js 迁移辅助包 -->
<script
	src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript">
	//页面加载完毕，执行下面的js代码
	$(function() {
		//将id为loginForm的表单绑定一个提交事件
		$("#loginForm").submit(function() {
			//根据元素的id获取页面元素对象
			var name = $("#userName");
			var password = $("#userPass");
			var msg = "";
			//$.trim(name.val) 去除元素值的前后空格
			if ($.trim(name.val()) == "") {
				msg = "亲,请输入登录名！";
				//让元素获取焦点
				name.focus();
				//!/^\w{5,20}$/.test($.trim(name.val)) 正则表达式判断元素值是否满足条件
			} else if (!/^\w{5,20}$/.test($.trim(name.val()))) {
				msg = "亲,请输入合法的登录名！";
				name.focus();
			} else if ($.trim(password.val()) == "") {
				msg = "亲,请输入登录密码！";
				password.focus();
			} else if (!/^\w{6,20}$/.test($.trim(password.val()))) {
				msg = "亲,请输入合法的登录密码！";
				password.focus();
			}

			if (msg != "") {
				alert(msg);
				//如果返回false将 不会 提交表单
				return false;
			} else {
				//如果返回true 将会 提交表单
				return true;
			}
		});
	});
</script>

</head>
<body>

	<!-- content -->
	<div class="content bg-f2f2f2">
		<div class="container">
			<!-- section1 -->
			<div class="section section1 clear t-p40 ">
				<!-- content_main -->
				<div class="bg-white">
					<div class="title">
						<h2>登录</h2>
					</div>
					<style type="text/css">
.section_body {
	padding-right: 100px;
	background: url('../images/login_bg.jpg') no-repeat left center;
}

.section_body form {
	*width: 340px;
	padding-top: 60px;
	padding-bottom: 20px;
}

.section_body form p>input {
	width: 280px;
	line-height: 26px;
	height: 26px;
	padding-left: 10px;
}

.section_body form p {
	line-height: 28px;
	padding-bottom: 20px;
}

.section_body label {
	color: #666666;
}

.section_body label input {
	margin-right: 10px;
}

.section_body form p>a {
	margin-left: 10px;
	color: #666666;
}

.section_body form p>a:hover {
	color: red;
}

.section_body .btn {
	color: #333333;
	padding: 0;
	width: 78px;
	border: 1px solid #cccccc;
	background: #f1eeee;
}
</style>
					<div class="section_body">
						<form id="loginForm" class="right"
							action="${pageContext.request.contextPath}/user" method="POST">
							<input type="hidden" name="method" value="login">
							<p class="f14 c-666666">
								账号：<input id="userName" type="text" name="name" value="tomcat" placeholder="请输入账号">
							</p>
							<p class="f14 c-666666">
								密码：<input id="userPass" type="password" name="password" value="123456789" placeholder="请输入密码">
							</p>
							<p class="clear">
								<label style="margin-left: 40px" class="left" for="">
								<input class="text-middle" type="checkbox">自动登录</label>
								<a class="right" href="#">[注册新账号]</a>
								<a class="right" href="#">[忘记密码]</a>
							</p>
							<p class="text-center">
								<input class="btn" type="submit" value="登录">
							</p>
						</form>
					</div>
				</div>
				<!-- content_main -->
				<!-- content_sidebar -->
				<!-- content_sidebar end-->
			</div>
			<!-- section1 end-->
			<!-- section2 -->
			<!-- section2 end-->

		</div>
	</div>
	<!-- content end-->
	<!-- footer-->
	<div class="footer">
		<div class="footer_body">
			<span class="rightcopy"> Copyright ©360.cn. All Rights
				Reserved. 京ICP证080047号 京公网安备110000000006号</span>
		</div>
	</div>
	<!-- footer end-->
</body>
<!--[if IE 6]>
    <script src="js/DD_belatedPNG_0.0.8a.js"></script>
    <script>
    DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
</html>