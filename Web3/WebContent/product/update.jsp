<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>丫丫豆-都市信息平台</title>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/commons.js"></script>
    <!-- TinyMCE -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/tiny_mce/tiny_mce.js"></script>
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
            height: 40px;
            padding: 0 10px;
        }

        .tab th {
            background: url('${pageContext.request.contextPath}/images/tabThBg.png') right center no-repeat;
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
    <script type="text/javascript">
        tinyMCE.init({
                mode: "exact", // mode:指定附加的模式 textareas : html页面上所有的textarea元素都附加上.
                elements: "content", // exact:精确的模式， 需要指定html元素id属性值
                theme: "advanced", // 主题：advanced(高级的) 、simple(简单的)
                language: "zh-cn", // 国际化
                /** 指定工具按钮资源文件存放的文件夹 */
                plugins: "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",

                /** 指定工具按钮在上面放四排 */
                theme_advanced_buttons1: "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
                theme_advanced_buttons2: "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
                theme_advanced_buttons3: "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
                theme_advanced_buttons4: "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",

                /** 指定工具按钮上下的位置 top、bottom */
                theme_advanced_toolbar_location: "top",
                /** 指定工具按钮左、中、右显示的位置 left、center、right */
                theme_advanced_toolbar_align: "left",
                /** 指定状态栏的位置 (top、bottom) */
                theme_advanced_statusbar_location: "bottom",
                /** 指定编辑器是否可以拖动 */
                theme_advanced_resizing: true
            });

        /** 当前文档加载完 */
        $(document).ready(function () {
            /** 让类型下拉列表选中 */
            $("#typeSelect").val("${product.proType.id}");
            /** 表单输入校验 */        		
			$("#productForm").submit(function() {
					var title = $("#title");
					var content = tinyMCE.get('content').getContent();
					var image = $("#image");
					var msg = "";
					if ($.trim(title.val()) == "") {
						msg = "标题不能为空！";
						title.focus();
					} else if (image.val().length == 0) {
						msg = "图片不能为空";
					} else if ($.trim(content) == "") {
						msg = "内容不能为空！";
					}
					if (msg != "") {
						alert(msg);
						return false;
					} else {
						// 提交表单的方法
						return confirm("确认添加");
					}
				});
			});
				</script>
</head>
<body>

<div id="wrapper">
    <div class="box">
        <!-- 表单 -->
        <form id="productForm" action="${pageContext.request.contextPath }/product" method="post" enctype="multipart/form-data">
            <input type="hidden" name="method" value="update"/> <input
                type="hidden" name="id" value="${product.id}"/>
            <table width="98%" class="tab">

                <tr align="left">
                    <td>新闻类型：<select name="type" id="typeSelect">
                        <c:forEach items="${proTypes }" var="pt">
                            <option value="${pt.id }">${pt.name }</option>
                        </c:forEach>
                    </select> &nbsp;<font color="color"></font>
                    </td>
                </tr>

                <tr align="left">
                    <td>产品名称：<input type="text" name="name"
                                    value="${product.name }" size="30" id="title"/></td>
                </tr>
				<tr>
					<td>
						产品图片：
						<input type="file" name="file" id="image">
					</td>
				</tr>
                <tr>
                    <td>产品详情：<br/> <textarea name="content" id="content">${product.content} </textarea>
                    </td>
                </tr>

                <tr>
                    <td><input type="submit" value="确 定">&nbsp; <input
                            type="reset" value="重 置"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>