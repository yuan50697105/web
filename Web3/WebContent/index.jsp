<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<script type="text/javascript" src="js/jquery-1.11.0.js" ></script>
		<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.source.js" ></script>
		<script type="text/javascript" src="js/banner.js" ></script>
	</head>
	<body>
		<a href="info/login.jsp">进入后台</a>
		<div class="header">
			<a href="index"><img src="img/logo.png" alt="logo" /></a>
			<ul>
				<li><a class="current" href="index">首&emsp;&emsp;页</a></li>
				<li><a href="about.jsp">关于我们</a></li>
				<li><a href="news?method=toNewsCenter">新闻中心</a></li>
				<li><a href="product?method=toProductCenter">产品中心</a></li>
				<li><a href="contact.jsp">联系我们</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="banner">
				<ul>
					<li>
						<a href="about.html">
							<img src="img/banner1.jpg" alt="banner1" />
						</a>
					</li>
					<li>
						<a href="about.html">
							<img src="img/banner2.jpg" alt="banner2" />
						</a>
					</li>
					<li>
						<a href="about.html">
							<img src="img/banner3.jpg" alt="banner3" />
						</a>
					</li>
				</ul>
				<ol>
					<li></li>
					<li></li>
					<li></li>
				</ol>
			</div>
			<div class="about clearfix">
				<div class="title">
					<h2>关于我们</h2>
					<h3>About us</h3>
				</div>
				<div class="left">
					<div class="hd">
						<ul>
							<li>公司简介</li>
							<li class="middle">企业文化</li>
							<li>服务理念</li>
						</ul>
					</div>
					<div class="bd">
						<p>博达有限公司是一家生产各种金属丝网、筛网及大型桥梁、公路、铁路护栏、防护网系列！是生产销售为一体的大型企业公司。具有三十多年的生产历史，公司自建立以来不断引进先进的生产技术设备，改善管理方式，所生产的丝网产品采用优质原材料，科学检测。公司主要产品有：高速公路护栏网，钢板网、勾花网、建筑安全网、防盗美格网、养殖系列网、踏尘网、等等。</p>
						<p>优质原材料，科学检测。公司主要产品有：高速公路护栏网，钢板网、勾花网、建筑安全网、防盗美格网、养殖系列网、踏尘网、等等。博达有限公司是一家生产各种金属丝网、筛网及大型桥梁、公路、铁路护栏、防护网系列！是生产销售为一体的大型企业公司。具有三十多年的生产历史，公司自建立以来不断引进先进的生产技术设备，改善管理方式，所生产的丝网产品采用</p>
						<p>优质原材料，科学检测。公司主要产品有：高速公路护栏网，钢板网、勾花网、建筑安全网、防盗美格网、养殖系列网、踏尘网、等等。是生产销售为一体的大型企业公司。具有三十多年的生产历史，公司自建立以来不断引进先进的生产技术设备，改善管理方式，所生产的丝网产品采用博达有限公司是一家生产各种金属丝网、筛网及大型桥梁、公路、铁路护栏、防护网系列！</p>
					</div>
				</div>
				<a class="img" href="about.html"><img src="img/about_img.jpg" alt="about_img" /></a>
			</div>
			<div class="product">
				<div class="title">
					<h2>产品中心</h2>
					<h3>Product</h3>
				</div>
				<ul>
					<c:forEach items="${products }" var="p">
						<li>
							<a href="product?method=getDetail&id=${p.id }"><img src="image?show=product&path=${p.image }" alt="product_1" /></a>
							<p><a href="product?method=getDetail&id=${p.id }">${p.name }</a></p>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="news clearfix">
				<div class="title">
					<h2>新闻中心</h2>
					<h3>News</h3>
				</div>
				<c:forEach items="${news }" var="n" end="0">
					<div class="news_left">
						<a href="news?method=getDetail&id=${n.id }"><img src="image?show=news&path=${n.image }" alt="" /></a>
					</div>				
				</c:forEach>
				<div class="news_right">
					<c:forEach items="${news }" var="n" end="0">
						<div class="top clearfix">
							<div class="date">
								<h2><fmt:formatDate value="${n.publishTime }" pattern="dd"/></h2>
								<span><fmt:formatDate value="${n.publishTime }" pattern="yyyy.MM"/></span>
							</div>
							<div class="text">
								<h2><a href="news?method=getDetail&id=${n.id }">${n.title }</a></h2>
								<p>${fn:substring(n.content,0,100) } ......</p>
							</div>
						</div>
					</c:forEach>
					<div class="more">
						<ul>
							<c:forEach items="${news }" var="n" begin="1">
								<li>
									<a href="news?method=getDetail&id=${n.id }">${n.title }</a>
									<span><fmt:formatDate value="${n.publishTime }" pattern="yyyy-MM-dd"/></span>
								</li>							
							</c:forEach>						
							<li>
								<i>&zwj;</i>
							</li>
						</ul>
					</div>
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
	<script type="text/javascript">jQuery(".left").slide({titOnClassName:"current"});</script>
</html>
