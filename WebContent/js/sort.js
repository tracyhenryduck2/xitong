/**
 * 排序控件 v1.1
 * 2009-02-12 
 * 将数据表格的渲染部分整合进来
 */
(function($){
	
	// 数据表格渲染，包括表格样式，隔行换色、行滑动变色等
	$.fn.render = function(){
		if(!$(this).hasClass("dataGrid")) $(this).addClass("dataGrid");
		
		$(this).find('tbody tr:odd').addClass('odd');
		$(this).find('tbody tr').hover(
			function() {$(this).addClass('highlight');},
			function() {$(this).removeClass('highlight');}	
		).find("input[type='checkbox']").click(function(){
			if($(this).attr("checked")){
				$(this).parent().parent("tr").addClass('selected');
			}else{
				$(this).parent().parent("tr").removeClass('selected');
			}
		});	
		$(this).find('tbody tr').dblclick(function(){
			var flag = $(this).find("input[type='checkbox']").attr("checked");
			
			//取消选中
			$(this).parent().find("input[type='checkbox']").each(function(){
				$(this).attr("checked",false);
				$(this).parent().parent().removeClass("selected");
			});
			if(flag){
				$(this).find("input[type='checkbox']").attr("checked","");
				$(this).removeClass('selected');
			} else {
				$(this).find("input[type='checkbox']").attr("checked",true);
				$(this).addClass('selected');
				//try {detail();} catch (e) {}
			}
		});
		return $(this);
	}
	
	// 数据表格排序
	var _sortDesc = "";
	$.fn.sort = function(sortFormName){
		
		if(!$(this).hasClass("dataGrid")) $(this).addClass("dataGrid");
		
		switch($("input[name='sortorder']").val()){
			case 'asc':
				_sortDesc = "降序↓";
			break;
			
			case 'desc':
			default:
				_sortDesc = "升序↑";
			break;
		}
		
		$(this).find("tr:first th").each(function(i){
			//
			//[sortname]
			if($(this).attr("sname")!=null && $(this).attr("sname")!="") {
				var sname = $(this).attr("sname");
				var _sortnameAttr = $(this).attr("sortname");
				if(_sortnameAttr==null || _sortnameAttr=="") {
					$(this).html("<div id=\"sort_"+sname+"\" >"+$(this).html()+"</div>");
				} else {
					$(this).html("<div id=\"sort_"+_sortnameAttr+"\" sid=\"sort_"+sname+"\" title=\"点击按“"+$(this).text()+"”"+_sortDesc+"排序\">"+$(this).html()+"</div>");
				}
			} else if($(this).attr("sortname")!=null && $(this).attr("sortname")!="") {
				var _sortnameAttr = $(this).attr("sortname");
				$(this).html("<div id=\"sort_"+_sortnameAttr+"\" title=\"点击按“"+$(this).text()+"”"+_sortDesc+"排序\">"+$(this).html()+"</div>");
				
				$("div#sort_"+_sortnameAttr).bind("click",function(e){
					var _sortorder = $("input[name='sortorder']").val();
					$("input[name='sortname']").val(_sortnameAttr);
					$("input[name='sortorder']").val((_sortorder == "" ||_sortorder == "desc" || _sortorder == "DESC")?"asc":"desc");
						  
					document.forms[sortFormName].submit();
				});	
			}
		});
		
		var _currSortname = $("input[name='sortname']").val();
		if(_currSortname && $.trim(_currSortname) != ""){
			$("div#sort_"+_currSortname).removeClass().addClass($("input[name='sortorder']").val());
			$(this).find("tr:first th[sortname]").removeClass("currentSort");
			$(this).find("tr:first th[sortname='"+_currSortname+"']").addClass("currentSort");
		}
		
	}
})($);

