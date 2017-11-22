 <%@ page contentType="text/html; charset=UTF-8" language="java"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.contextPath }/">
		<meta charset="utf-8" />
		<title>新闻中心</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/public.css" />
		<link rel="stylesheet" type="text/css" href="css/news.css" />
		<style type="text/css">
			.news_list img{width: 202px;height: 122px;} 
		</style>
	</head>
	<body>
		<div class="header">
			<a href="index"><img src="img/logo.png" alt="logo" /></a>
			<ul>
				<li><a href="index">首&emsp;&emsp;页</a></li>
				<li><a href="about.jsp">关于我们</a></li>
				<li><a class="current" href="news?method=toNewsCenter">新闻中心</a></li>
				<li><a href="product?method=toProductCenter">产品中心</a></li>
				<li><a href="contact.jsp">联系我们</a></li>
			</ul>
		</div>
		<div class="banner">
			<a href="###"><img src="img/banner2.jpg" alt="banner2" /></a>
		</div>
		<div class="content clearfix">
			<div class="sidebar clearfix">
				<div class="sidebar_title">
					<h2>NEWS&ensp;CENTER</h2>
					<h3>新闻中心</h3>
				</div>
				<ul>
					<c:forEach items="${newsTypes }" var="nt" end="0">
						<li class="current">
							<a href="${pageContext.request.contextPath }/news?method=listByType&type=${nt.id }">${nt.name }</a>
						</li>						
					</c:forEach>
					<c:forEach items="${newsTypes }" var="nt" begin="1">
						<li>
							<a href="${pageContext.request.contextPath }/news?method=listByType&type=${nt.id }">${nt.name }</a>
						</li>	
					</c:forEach>
					<!-- <li class="current"><a href="###">最新动态</a></li>
					<li><a href="###">企业新闻</a></li>
					<li><a href="###">行业新闻</a></li>
					<li><a href="###">热点聚焦</a></li> -->
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
				<h2>新闻中心 /&ensp;<span>News&ensp;center</span></h2>
			</div>
			<div class="news_list">
				<ul>
					<c:forEach items="${news }" var="n">
						<li>
							<a class="img" href="${pageContext.request.contextPath }/news?method=getDetail&id=${n.id }"><img src="image?show=news&path=${n.image }" alt="news_list_01" /></a>
							<h2><a href="${pageContext.request.contextPath }/news?method=getDetail&id=${n.id }">${n.title }</a></h2>
							<p><a href="${pageContext.request.contextPath }/news?method=getDetail&id=${n.id }">${fn:length(n.content)<100?n.content:fn:substring(n.content,0,100) }......</a></p>
							<span><fmt:formatDate value="${n.publishTime }" pattern="yyyy-MM-dd"/></span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<c:if test="${page != ps }">
				<div class="page">
				<ul class="clearfix">
					<li class="prev"><a href="news?method=listByType&type=${type }&page=${page-1<=1?1:page-1}">上一页</a></li>
					<li class="on"><a href="news?method=listByType&type=${type }&page=1">1</a></li>
					<li><a href="news?method=listByType&type=${type }&page=2">2</a></li>
					<li><a href="news?method=listByType&type=${type }&page=3">3</a></li>
					<li><a href="news?method=listByType&type=${type }&page=4">4</a></li>
					<li><a href="news?method=listByType&type=${type }&page=5">5</a></li>
					<li><a href="news?method=listByType&type=${type }&page=6">6</a></li>
					<li><a href="###">···</a></li>
					<li class="next"><a href="${pageContext.request.contextPath }/news?method=listByType&type=${type }&page=${page>=ps?ps:page+1}">下一页</a></li>
				</ul>
			</div>
			</c:if>
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
</html>
