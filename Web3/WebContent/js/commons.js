$(function(){
	
	$("#wrapper").css({
		minHeight:$(window).height() - 30	
	});

	$(window).resize(function(){
		$("#wrapper").css({
			minHeight:$(window).height() - 30	
		});	
	});
	
	var field = $(".field");
	
	$.each(field,function(i,e){
		
		if(i == field.length-1){
			$(e).css({
				border:"none"	
			});
		}
	});
	
	
	
	
	
	var selectH3 = $(".selectH3");
	var selectList = $(".selectList");
	$.each(selectH3,function(i,e){
		$(e).click(function(){
			if( selectList.eq(i).css("display") == "none" ){
				selectList.eq(i).css("display","block");	
			}else{
				selectList.eq(i).css("display","none");	
			}
			selectList.eq(i).find("a").click(function(){
				
				var _html = $(this).html();
				
				$(".selectH3").eq(i).html( _html );
				
				$(".selectH3").eq(i).css({
					textIndent:50	
				});
				
				selectList.eq(i).css("display","none");
				
				return false;	
			});
			
			var selectLi = selectList.eq(i).find("li");
			$.each(selectLi,function(i,e){
	
				if(i == selectLi.length-1){
					$(e).css({
						border:"none"	
					});
				}
			});	
			
		});
	});
	
	
});