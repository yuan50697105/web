DROP DATABASE IF EXISTS web;
CREATE DATABASE web;
USE web;
CREATE TABLE web.user
(
  id        INT(11) PRIMARY KEY                 NOT NULL AUTO_INCREMENT,
  name      VARCHAR(50),
  password  VARCHAR(50),
  type      INT(11)                                      DEFAULT '0',
  loginTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX
  ON web.user (name);
CREATE TABLE web.protype
(
  id   INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20)
);
CREATE UNIQUE INDEX name
  ON web.protype (name);
CREATE TABLE web.newstype
(
  id   INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20)
);
CREATE UNIQUE INDEX name
  ON web.newstype (name);
CREATE TABLE web.news
(
  id            INT(11) PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  title         VARCHAR(255),
  content       MEDIUMTEXT,
  type          INT(11),
  createTime    TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL,
  publishTime   TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
  publishStatus INT(11) DEFAULT '0'                     NOT NULL,
  userid        INT(11),
  image         VARCHAR(255),
  CONSTRAINT news_newstype_id_fk FOREIGN KEY (type) REFERENCES newstype (id),
  CONSTRAINT news_user_id_fk FOREIGN KEY (userid) REFERENCES user (id)
);
CREATE INDEX news_user_id_fk
  ON web.news (userid);
CREATE INDEX news_newstype_id_fk
  ON web.news (type);
CREATE TABLE web.product
(
  id            INT(11) PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  name          VARCHAR(255),
  image         VARCHAR(255),
  content       MEDIUMTEXT,
  type          INT(11),
  createTime    TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL,
  publishTime   TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
  publishStatus INT(11)                                          DEFAULT '0',
  userid        INT(11),
  CONSTRAINT product_protype_id_fk FOREIGN KEY (type) REFERENCES protype (id),
  CONSTRAINT product_user_id_fk FOREIGN KEY (userid) REFERENCES user (id)
);
CREATE INDEX product_protype_id_fk
  ON web.product (type);
CREATE INDEX product_user_id_fk
  ON web.product (userid);
# 用户
INSERT INTO web.user (name, password, type, loginTime) VALUES ('administor', '123456789', 0, '2017-09-19 15:57:33');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('abcdefg', '123456789', 1, '2017-09-19 15:57:33');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('tomcat', '123456789', 1, '2017-09-19 16:10:55');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('tomcat12', '123456789', null, '2017-09-20 17:28:05');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('tomcat1', '123456789', null, '2017-09-20 17:28:05');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('tomcat3', '123456789', null, '2017-09-20 17:29:17');
INSERT INTO web.user (name, password, type, loginTime) VALUES ('tomcat4', '123456789', null, '2017-09-20 17:29:17');
# 新闻类型
INSERT INTO web.newstype (name) VALUES ('最新动态');
INSERT INTO web.newstype (name) VALUES ('企业新闻');
INSERT INTO web.newstype (name) VALUES ('行业新闻');
INSERT INTO web.newstype (name) VALUES ('热点聚焦');
# 产品类型
INSERT INTO web.protype (name) VALUES ('高速公路护栏网');
INSERT INTO web.protype (name) VALUES ('钢板网');
INSERT INTO web.protype (name) VALUES ('勾花网');
INSERT INTO web.protype (name) VALUES ('建筑安全网');
INSERT INTO web.protype (name) VALUES ('防盗美格网');
INSERT INTO web.protype (name) VALUES ('养殖系列网');
INSERT INTO web.protype (name) VALUES ('踏尘网');
INSERT INTO web.protype (name) VALUES ('金属丝网');
# 新闻
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('正品控宣言：让商品拥有独一无二的身份证', '<p>今天，中国家电及消费电子博然会（Appliance&amp;electronics World Expo，简称AWE）圆满落幕，展会吸引了<br />包括消费电子、智能家电、白色家电、厨房电器、生活电器、环境家电及家电配件等多品类的家电制造商参加<br />，也是对商品正品的最大支持</p>', 1, '2017-10-12 16:31:37', '2017-10-12 16:54:44', 1, 3, 'news_img.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('三大”黑科技”锁定中国消费者的心', '<p>三大&rdquo;黑科技&rdquo;锁定中国消费者的心</p>', 1, '2017-10-12 16:32:23', '2017-10-12 16:54:44', 1, 3, 'news_list_01.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('2016AWE：方太最全智能嵌入式厨电套系亮相', '<p>2016AWE：方太最全智能嵌入式厨电套系亮相</p>', 1, '2017-10-12 16:36:44', '2017-10-12 16:54:44', 1, 3, 'news_list_02.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('美的焖香鼎釜IH智能电饭煲全球首发', '<p>&nbsp;美的焖香鼎釜IH智能电饭煲全球首发</p>', 3, '2017-10-12 16:37:14', '2017-10-12 16:54:44', 1, 3, 'news_list_03.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('AWE2016盛大开幕  释放家电业“引力波', '<p>AWE2016盛大开幕&nbsp; 释放家电业&ldquo;引力波</p>', 4, '2017-10-12 16:37:43', '2017-10-12 16:54:44', 1, 3, 'news_list_04.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('高科技新型金属丝网今日出炉看博达如何掌控', '<p>高科技新型金属丝网今日出炉看博达如何掌控</p>', 2, '2017-10-12 16:38:00', '2017-10-12 16:54:44', 1, 3, 'news_list_05.jpg');
INSERT INTO web.news (title, content, type, createTime, publishTime, publishStatus, userid, image) VALUES ('健康中国科普万里行全国大型公益活动新闻发布会成功举行', '<p>&emsp;&emsp;4月11日，健康中国科普万里行全国大型公益活动新闻发布会在北京举行。中国福利基金会朱朝英副理事长、养老事业发展基金秘书长谢君、杜志政博士、中国疾控中心付萍研究员、中国教育科学研究院尚大光教授及活动赞助企业出席发布会。会上，朱朝英副理事长就健康中国科普万里行公益活动发起及组织实施提出严格要求。谢君秘书长就活动筹备工作做了介绍。杜志政博士做了活动实施步骤、活动试运行情况发言。</p>
<p><br /> <img src="img/news_details_img01.png" alt="news_details_img01" /><br /> <em>中国福利基金会朱朝英副理事长</em></p>
<p>&emsp;&emsp;朱朝英副理事长指出，健康中国科普万里行全国大型公益活动是中国社会福利基金会贯彻落实习总书记在2016年8月在全国卫生与健康大会上提出要把人民健康放在优先发展的战略地位这一指示精神，在国家有关部委联合指导下，在国内等主流权威媒体支持下，由中国社会福利基金会发起，搭建以政府为主导、部门协作、全社会参与、舆论导向支持、以北京为启动，逐步推向各省市城市社区并走进乡村的全国性大型健康科普公益活动，切实让全国千万家庭亿万民众分享国家健康红利。基金会领导非常重视，要求各部门、各机构要加强组织领导、统一协调、科学实施、务求实效，坚决克服走过场和形式主义，切实把健康中国科普万里行公益活动做成一台奉献给社会的健康福利大餐。</p>
<p><br /> <img src="img/news_details_img02.png" alt="news_details_img02" /></p>
<p>&emsp;&emsp;谢君秘书长代表中国社会福利基金会养老事业发展基金表示，在基金会统一部署、统一指挥下，健康中国科普万里行全国大型公益活动自去年十月筹备以来，成立项目办公室，经过精心策划、精细化活动实施方案，各项工作进展顺利，在人员、时间、精力、效果确保情况下，通过试运行情况来看，健康中国科普万里行全国大型公益活动社会反响强烈，很受居委会、社区居民欢迎，社会效果凸显，为下一步活动全面展开夯实基础。希望媒体界朋友们大力支持，给予健康中国科普万里行活动全程跟踪、全方位宣传报道，向全社会传播健康中国科普正能量。同时也诚挚邀请社会各界力量、行业组织团体、企事业单位共同参与健康中国科普万里行全国大型公益活动，用我们的责任感、公益心为全社会送去健康福荫。</p>
<p><br /> <img src="img/news_details_img03.jpg" alt="news_details_img03" /><br /> <em>养老事业发展基金秘书长谢君</em></p>
<p>&emsp;&emsp;最后，杜志政博士介绍了自3月2日以来在北京市西城区、丰台区、朝阳区、海淀区、东城区等十五个社区进行十五场健康中国科普万里行公益活动试运行情况，如中央教科所尚大光研究员的人的生命健康讲座、中国疾控中心余雅婷研究员的营养平衡讲座及免费检测、首医大李教授的睡眠健康讲座、海归博士6人老年智力开发预防和老年智呆课题讲座、现场互动以及北京协和医院、北京老年医院等实力派专家现场免费检测、免费咨询，现场没有任何企业产品销售、讲座内容不植入任何企业产品推广。活动深受社区居委会和社区居民的欢迎，很多居民询问下次活动什么时候再来。为此，我们将总结试运行阶段经验，推出更加科学、更接地气、更注重实效、内容更丰富的健康科普活动送给社会。</p>', 1, '2017-10-12 16:54:35', '2017-10-12 16:54:44', 1, 3, 'news_details_img01.png');
# 产品
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('勾花网', 'product_01.jpg', '<p>勾花网</p>', 3, '2017-10-12 16:25:59', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('防盗美格网', 'product_02.jpg', '<p>防盗美格网</p>', 5, '2017-10-12 16:26:15', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('建筑安全网', 'product_03.jpg', '<p>建筑安全网</p>', 4, '2017-10-12 16:26:31', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('养殖系列网', 'product_04.jpg', '<p>养殖系列网</p>', 6, '2017-10-12 16:26:44', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('钢板网', 'product_05.jpg', '<p>钢板网</p>', 2, '2017-10-12 16:27:28', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('踏尘网', 'product_07.jpg', '<p>踏尘网</p>', 7, '2017-10-12 16:29:07', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('金属丝网', 'product_08.jpg', '<p>金属丝网</p>', 8, '2017-10-12 16:29:21', '2017-10-12 17:05:45', 1, 3);
INSERT INTO web.product (name, image, content, type, createTime, publishTime, publishStatus, userid) VALUES ('高速公路护栏网', 'product_06.jpg', '<div class="parameter"><img src="img/pro_details_img.jpg" alt="pro_details_img" /><dl><dt>高速公路护栏网</dt><dd>
<p>公路护栏网根据其样式分为：</p>
<p>折弯隔离栅，框架隔离栅，波浪隔离栅，双圈隔离栅，双边丝隔离栅等。</p>
</dd><dd>
<p>表面颜色可选：</p>
<p>白色，绿色，墨绿色，草绿色，黑色，蓝色，黄色等等。</p>
</dd><dd>
<p>规格：</p>
<p>200cm*200cm*10cm、150cm*150cm*6cm等。</p>
</dd><dd>
<p>防腐形式：</p>
<p>电镀锌、热镀锌、喷塑、浸塑。</p>
</dd></dl></div>
<div class="text">
<ul>
<li>
<p>公路护栏网是非常重要的交通基础设施，我国高速公路是从80年代发展起来的，它对国民经济和社会的发展起着重要的作用是高速公路重要维护和安全保障设施。</p>
</li>
<li>
<p>公路护栏网的涂装工艺流程：</p>
<p>公路护栏网的涂装工艺由两个阶段来完成：第一阶段为固定床阶段，第二阶段为气流输送阶段，在固定床的基础上继续增大流速（W），床层开始膨胀和松动，床层高度开始增加，每个粉末颗粒被浮起，因而离开原来位置作一定程度的移动，这时便进入流化床阶段。</p>
<p>流化床内粉层膨胀，其高度（I）随着气体速度的增大而增加，但床内压强（△P）并不增大，在一定范围内变动流速而不影响流体所需的单位功率，这是流化床的特征，正是利用这一特点来进行涂装工艺实施的。</p>
<p>流化床内粉末流化状态的均匀性是保证涂膜均匀的关键，在粉末涂装上用的流化床属于&ldquo;竖式流态化&rdquo;，流化数必需经过试验找出，能够涂敷就好，流化床内粉末的悬浮率最高可达30～50%。</p>
</li>
<li>
<p>公路护栏网作为一种有效的隔离防护产品，它的特点如下：</p>
<p>1、选用网片和立柱组合的安装模式，具有结构简单、安装便捷，美观实用等特点。而且运输便捷，在安装时不受地形起伏影响。</p>
<p>2、对于南方地区，尤其是一些山地、坡地、多弯地带的高速公路两侧能很方便的安装。</p>
<p>3、和其它护栏网比较来说价位适中，适合在国内各等级公路两侧大面积推广。</p>
<p>用途及使用范围：主要用于公园/动物园围栏、校园/场区围界、道路交通隔离、临时隔离带。</p>
</li>
</ul>
</div>', 1, '2017-10-12 16:58:35', '2017-10-12 17:05:45', 1, 3);

