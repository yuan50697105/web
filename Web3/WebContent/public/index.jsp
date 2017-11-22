<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" tagdir="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }">
<meta charset="utf-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/css/index.css" />
<script type="text/javascript" src="/public/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="/public/js/jquery.SuperSlide.2.1.1.source.js"></script>
<script type="text/javascript" src="/public/js/banner.js"></script>
</head>
<body>
	<div class="header">
		<a href="index.html"><img src="/public/img/logo.png" alt="logo" /></a>
		<ul>
			<li><a class="current" href="index.html">首&emsp;&emsp;页</a></li>
			<li><a href="about.html">关于我们</a></li>
			<li><a href="news.html">新闻中心</a></li>
			<li><a href="product.html">产品中心</a></li>
			<li><a href="contact.html">联系我们</a></li>
		</ul>
	</div>
	<div class="content">
		<div class="banner">
			<ul>
				<li><a href="about.html"> <img src="/public/img/banner1.jpg"
						alt="banner1" />
				</a></li>
				<li><a href="about.html"> <img src="/public/img/banner2.jpg"
						alt="banner2" />
				</a></li>
				<li><a href="about.html"> <img src="/public/img/banner3.jpg"
						alt="banner3" />
				</a></li>
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
			<a class="img" href="about.html"><img src="/public/img/about_img.jpg"
				alt="about_img" /></a>
		</div>
		<div class="product">
			<div class="title">
				<h2>产品中心</h2>
				<h3>Product</h3>
			</div>
			<ul>
				<c:forEach items="${products }" var="p">
					<li><a href="/product?method=getDetail&id=${p.id }"><img
						src="/image?path=${p.image }" alt="product_1" /></a>
					<p>
						<a href="/product?method=getDetail&id=${p.id }">${p.name }</a>
					</p></li>
				</c:forEach>
				<!-- 
				<li><a href="pro_details.html"><img
						src="/public/img/product_01.jpg" alt="product_1" /></a>
					<p>
						<a href="pro_details.html">勾花网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_02.jpg" alt="product_2" /></a>
					<p>
						<a href="pro_details.html">防盗美格网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_03.jpg" alt="product_3" /></a>
					<p>
						<a href="pro_details.html">建筑安全网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_04.jpg" alt="product_4" /></a>
					<p>
						<a href="pro_details.html">养殖系列网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_05.jpg" alt="product_5" /></a>
					<p>
						<a href="pro_details.html">钢板网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_06.jpg" alt="product_6" /></a>
					<p>
						<a href="pro_details.html">高速公路护栏网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_07.jpg" alt="product_7" /></a>
					<p>
						<a href="pro_details.html">踏尘网</a>
					</p></li>
				<li><a href="pro_details.html"><img
						src="/public/img/product_08.jpg" alt="product_8" /></a>
					<p>
						<a href="pro_details.html">金属丝网</a>
					</p></li> 
					-->
			</ul>
		</div>
		<div class="news clearfix">
			<div class="title">
				<h2>新闻中心</h2>
				<h3>News</h3>
			</div>
			<c:forEach items="${news }" var="n" begin="0" end="1">
				<div class="news_left">
					<a href="/news?method=getDetail&id=${n.id }"><img src="/image?path=${n.image }" alt="" /></a>
				</div>
				<div class="news_right">
				<div class="top clearfix">
					<div class="date">
						<h2>12</h2>
						<span>2017.03</span>
					</div>
					<div class="text">
						<h2>
							<a href="news_details.html">正品控宣言：让商品拥有独一无二的身份证</a>
						</h2>
						<p>今天，中国家电及消费电子博然会（Appliance&electronics
							World Expo，简称AWE）圆满落幕，展会吸引了
							包括消费电子、智能家电、白色家电、厨房电器、生活电器、环境家电及家电配件等多品类的家电制造商参加
							，也是对商品正品的最大支持......</p>
					</div>
				</div>
			</c:forEach>
			
				<div class="more">
					<ul>
						<li><a href="news_details.html">·
								三大”黑科技”锁定中国消费者的心</a> <span>2017-03-15</span>
						</li>
						<li><a href="news_details.html">·
								正品控宣言：让商品拥有独一无二的身份证</a> <span>2017-03-16</span>
						</li>
						<li><a href="news_details.html">·
								2016AWE：方太最全智能嵌入式厨电套系亮相</a> <span>2017-03-17</span>
						</li>
						<li><a href="news_details.html">·
								美的焖香鼎釜IH智能电饭煲全球首发</a> <span>2017-03-18</span>
						</li>
						<li><a href="news_details.html">· AWE2016盛大开幕
								释放家电业“引力波”</a> <span>2017-03-20</span></li>
						<li><a href="news_details.html">·
								高科技新型金属丝网今日出炉看博达如何掌控</a> <span>2017-03-21</span>
						</li>
						<li><a href="news_details.html">·
								2016年中国家电发展高峰论坛文字实录</a> <span>2017-03-22</span>
						</li>
						<li><i>&zwj;</i></li>
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
<script type="text/javascript">jQuery(".left").slide({titOnClassName:"current"});</script>
</html>
