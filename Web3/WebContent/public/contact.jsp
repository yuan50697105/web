<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }">
<meta charset="utf-8" />
<title>联系我们</title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/public.css" />
<link rel="stylesheet" type="text/css" href="/public/css/contact.css" />
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=6vvqoVROdWsq64FDVM0kaVO1F65oGOOR"></script>
<script type="text/javascript" src="/public/js/map.js"></script>
</head>
<body>
	<div class="header">
		<a href="index.html"><img src="/public/img/logo.png" alt="logo" /></a>
		<ul>
			<li><a href="index.html">首&emsp;&emsp;页</a></li>
			<li><a href="about.html">关于我们</a></li>
			<li><a href="news.html">新闻中心</a></li>
			<li><a href="product.html">产品中心</a></li>
			<li><a class="current" href="contact.html">联系我们</a></li>
		</ul>
	</div>
	<div class="banner">
		<a href="###"><img src="/public/img/contect_banner.jpg" alt="banner3" /></a>
	</div>
	<div class="content clearfix">
		<div class="sidebar clearfix">
			<div class="sidebar_title">
				<h2>CONTECT&ensp;US</h2>
				<h3>联系我们</h3>
			</div>
			<ul>
				<li class="top">Detailed&ensp;address<br />And<br />Leaving&ensp;a&ensp;message
				</li>
				<li class="bottom">详细地址<br />给我们留言
				</li>
			</ul>
			<div class="search">
				<div class="search_title">
					<h2>SEARCH</h2>
					<h3>搜索</h3>
				</div>
				<div class="search_input">
					<form action="">
						<input class="text" type="text" /> <input class="button"
							type="image" src="/public/img/search_bg.png" />
					</form>
				</div>
			</div>
		</div>
		<div class="location">
			<img src="/public/img/about_main_left.png" alt="about_main_left" />
			<h2>
				产品详情 /&ensp;<span>Product&ensp;details</span>
			</h2>
		</div>
		<div class="map" id="map"
			style="overflow: hidden; position: relative; z-index: 0; background-color: rgb(243, 241, 236); color: rgb(0, 0, 0); text-align: left;"></div>
		<div class="msg">
			<form action="">
				<h2>给我留言</h2>
				<div class="name">
					<label for="name">姓&emsp;名:</label> <input id="name"
						type="text" />
				</div>
				<div class="mail">
					<label for="mail">邮&emsp;箱:</label> <input id="mail"
						type="text" />
				</div>
				<div class="text">
					<label for="text">留&emsp;言:</label>
					<textarea id="text"></textarea>
				</div>
				<div class="code">
					<label for="code">验证码:</label> <input id="code" type="text" />
					<img src="/public/img/contect_code.jpg" alt="contect_code" />
				</div>
				<div class="button">
					<input type="submit" value="提交" /> <input type="reset"
						value="重置" />
				</div>
			</form>
			<dl>
				<dt>联系方式</dt>
				<dd>公司地址：
					武汉市黄陂区前川街浙商工业园c区1号</dd>
				<dd>联&ensp;系&ensp;人：唐经理&ensp;先生</dd>
				<dd>联系电话：027-82730063</dd>
				<dd>手&emsp;&emsp;机：18717192266</dd>
			</dl>
		</div>
	</div>
	<div class="footer">
		<div class="top">
			<div class="left">
				<dl class="about">
					<dt>关于我们</dt>
					<dd>
						<a href="###">公司简介</a>
					</dd>
					<dd>
						<a href="###">企业文化</a>
					</dd>
					<dd>
						<a href="###">研发生产</a>
					</dd>
				</dl>
				<dl class="product">
					<dt>产品中心</dt>
					<dd>
						<a href="###">金属丝网</a>
					</dd>
					<dd>
						<a href="###">筛网</a>
					</dd>
					<dd>
						<a href="###">防护网</a>
					</dd>
				</dl>
			</div>
			<div class="code">
				<p>官方微信</p>
				<img src="/public/img/footer_code.jpg" />
			</div>
			<div class="right">
				<dl class="news">
					<dt>新闻中心</dt>
					<dd>
						<a href="###">最新动态</a>
					</dd>
					<dd>
						<a href="###">企业新闻</a>
					</dd>
					<dd>
						<a href="###">行业新闻</a>
					</dd>
					<dd>
						<a href="###">热点聚焦</a>
					</dd>
				</dl>
				<dl class="contact">
					<dt>联系我们</dt>
					<dd>
						<a href="###">在线咨询</a>
					</dd>
					<dd>
						<a href="###">网上留言</a>
					</dd>
					<dd>
						<a href="###">联系我们</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="copyright">
			<div>
				<p class="left">中国企业链版权所有 © 2012-2018</p>
				<p>沪ICP备13015158号-1</p>
			</div>
		</div>
	</div>
</body>
</html>
