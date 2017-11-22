<%@ page contentType="text/html; charset=UTF-8"  language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.contextPath }/">
		<meta charset="utf-8" />
		<title>产品详情页</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/public.css" />
		<link rel="stylesheet" type="text/css" href="css/pro_details.css" />
		<script type="text/javascript" src="js/jquery-1.11.0.js" ></script>
		<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.source.js" ></script>
	</head>
	<body>
		<div class="header">
			<a href="index"><img src="img/logo.png" alt="logo" /></a>
			<ul>
				<li><a href="index">首&emsp;&emsp;页</a></li>
				<li><a href="about.jsp">关于我们</a></li>
				<li><a href="news?method=toNewsCenter">新闻中心</a></li>
				<li><a class="current" href="product?method=toProductCenter">产品中心</a></li>
				<li><a href="contact.jsp">联系我们</a></li>
			</ul>
		</div>
		<div class="banner">
			<a href="###"><img src="img/banner3.jpg" alt="banner3" /></a>
		</div>
		<div class="content clearfix">
			<div class="sidebar clearfix">
				<div class="sidebar_title">
					<h2>PRODUCT&ensp;CENTER</h2>
					<h3>产品中心</h3>
				</div>
				<ul>
					<c:forEach items="${proTypes }" var="pt" end="0">
						<li class="current"><a href="product?method=listByType&type=${pt.id} ">${pt.name }</a></li>
					</c:forEach>
					<c:forEach items="${proTypes }" var="pt" begin="1">
						<li><a href="product?method=listByType&type=${pt.id} ">${pt.name }</a></li>
					</c:forEach>
					<!-- <li class="current"><a href="###">高速公路护栏网</a></li>
					<li><a href="###">钢板网</a></li>
					<li><a href="###">勾花网</a></li>
					<li><a href="###">建筑安全网</a></li>
					<li><a href="###">防盗美格网</a></li>
					<li><a href="###">养殖系列网</a></li> -->
				</ul>
				<div class="search">
					<div class="search_title">
						<h2>SEARCH</h2>
						<h3>搜索</h3>
					</div>
					<div class="search_input">
						<form action="">
							<input class="text" type="text" />
							<input class="button" type="image" src="img/search_bg.png" />
						</form>
					</div>
				</div>
			</div>
			<div class="location">
				<img src="img/about_main_left.png" alt="about_main_left"/>
				<h2>产品详情 /&ensp;<span>Product&ensp;details</span></h2>
			</div>
			<div class="main">
				${product.content }
				<div class="location">
					<img src="img/about_main_left.png" alt="about_main_left"/>
					<h2>产品推荐 /&ensp;<span>Product&ensp;recommendations</span></h2>
				</div>
				<div class="recommend">
					<ul>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend01.jpg" src="img/por_details_recommend01.jpg" alt="por_details_recommend01" />
							</a>
							<p><a href="###">ASF-01-886</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend02.jpg" src="img/por_details_recommend02.jpg" alt="por_details_recommend02" />
							</a>
							<p><a href="###">ASF-01-666</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend03.jpg" src="img/por_details_recommend03.jpg" alt="por_details_recommend03" />
							</a>
							<p><a href="###">ASF-01-899</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend04.jpg" src="img/por_details_recommend04.jpg" alt="por_details_recommend04" />
							</a>
							<p><a href="###">ASF-01-133</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend01.jpg" src="img/por_details_recommend01.jpg" alt="por_details_recommend01" />
							</a>
							<p><a href="###">ASF-01-886</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend02.jpg" src="img/por_details_recommend02.jpg" alt="por_details_recommend02" />
							</a>
							<p><a href="###">ASF-01-666</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend03.jpg" src="img/por_details_recommend03.jpg" alt="por_details_recommend03" />
							</a>
							<p><a href="###">ASF-01-899</a></p>
						</li>
						<li>
							<a class="img" href="###" target="_blank">
								<img _src="img/por_details_recommend04.jpg" src="img/por_details_recommend04.jpg" alt="por_details_recommend04" />
							</a>
							<p><a href="###">ASF-01-133</a></p>
						</li>
					</ul>
					<a class="prev" href="javascript:void(0)"></a>
					<a class="next" href="javascript:void(0)"></a>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="top">
				<div class="left">
					<dl class="about">
						<dt>关于我们</dt>
						<dd><a href="###">公司简介</a></dd>
						<dd><a href="###">企业文化</a></dd>
						<dd><a href="###">研发生产</a></dd>
					</dl>
					<dl class="product">
						<dt>产品中心</dt>
						<dd><a href="###">金属丝网</a></dd>
						<dd><a href="###">筛网</a></dd>
						<dd><a href="###">防护网</a></dd>
					</dl>
				</div>
				<div class="code">
					<p>官方微信</p>
					<img src="img/footer_code.jpg" />
				</div>
				<div class="right">
					<dl class="news">
						<dt>新闻中心</dt>
						<dd><a href="###">最新动态</a></dd>
						<dd><a href="###">企业新闻</a></dd>
						<dd><a href="###">行业新闻</a></dd>
						<dd><a href="###">热点聚焦</a></dd>
					</dl>
					<dl class="contact">
						<dt>联系我们</dt>
						<dd><a href="###">在线咨询</a></dd>
						<dd><a href="###">网上留言</a></dd>
						<dd><a href="###">联系我们</a></dd>
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
	<script type="text/javascript">jQuery(".recommend").slide({ mainCell:"ul", effect:"leftLoop", vis:4, scroll:1,  autoPage:true, switchLoad:"_src" });</script>
</html>
