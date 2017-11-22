/** 
 * jQuery Time Plugin
 * @Author lee.siu.wah
 * @Version 1.0
 */
;(function($){
	/** 为jQuery增加对象的方法 */
	$.fn.extend({
		runTime : function(){
			/** 创建日期对象 */
			var d = new Date();
			/** 定义数组封装最后结果 */
			var arr = new Array();
			/** 获取年 */
			arr.push(d.getFullYear() + "年");
			/** 获取月 */
			arr.push($.calc(d.getMonth() + 1) + "月");
			/** 获取日 */
			arr.push($.calc(d.getDate()) + "日");
			/** 获取星期几 */
			arr.push("&nbsp;" + $.weeks[d.getDay()] + "&nbsp;");
			/** 获取小时 */
			arr.push($.calc(d.getHours()) + ":");
			/** 获取分 */
			arr.push($.calc(d.getMinutes()) + ":");
			/** 获取秒 */
			arr.push($.calc(d.getSeconds()));
			
			/** 为查询到得dom元素加innerHTML */
			this.html(arr.join(""));
			
			var t = this;
			/** 开启延迟的定时器 */
			setTimeout(function(){
				t.runTime();
			}, 1000);
		}
	});
	$.extend({
		weeks : ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
		calc : function(str){
			return str > 9 ? str : "0" + str;
		}
	});
}(jQuery));