(function($) {
	$.fn.cySearchBox = function(options) {
		var settings = {
			'width' : '200px',
			'height' : '30px',
			'url' : '', 
			'type' : 'post', 
			'dataType' : 'json', 
			'data' : '',
			'maxNum' : '10',
			'searchTrHeight' : '30',
			'dataName' : 'name',
			'dataId' : 'id',
			'fontSize' : '14px',
			'fontColor' : '#2861A3',
			'fontHoverColor' : '#000000',
			'background' : '#FFFFFF',
			'hoverBackground' : '#E0E4E8'
		};
		if (options) {
			$.extend(settings, options);
		}
		var element = this;
		var elemetId=$(element).attr("id");
		var elemetName=$(element).attr("name");
		var id="cy_searchId_"+elemetId;
		
	
		this.initSearch = function() {
			this.elementStyle();
			$(element)[0].onkeyup=function(e) {
				element.check(e);
			};
			$(element)[0].onfocus=function() {
				element.search();
			};
		};
		this.elementStyle = function() {
		    var hiddenElement=new Object();
		    hiddenElement='<input type="hidden" name="'+elemetName+'" id="cy_searchId_'+elemetId+'" />';
			$(element).css("height", settings.height);
			$(element).css("width", settings.width);
			$(element).attr("name","cy_searchName_"+elemetName);
			$(element).parent().append('<span class="bg s_ipt_wr" id="search_span_'+elemetId+'"></span>');
			$("#search_span_"+elemetId).append(element);
			$("#search_span_"+elemetId).append(hiddenElement);
			$(element).addClass("search_class");
			$("#search_span_"+elemetId).append('<input id="search_hidden_input_'+elemetId+'"  style="display:none"/>');
			$("body").append('<div id="search_div_'+elemetId+'" ></div>');
			var left = $("#search_span_"+elemetId).offset().left;
			var top = $("#search_span_"+elemetId).offset().top + $("#search_span_"+elemetId).height();
			$("#search_div_"+elemetId).addClass("search_div");
			$("#search_div_"+elemetId).css("left",left + "px");
			$("#search_div_"+elemetId).css("top",top + "px");
			$("#search_div_"+elemetId).css("width",$(element).css("width"));
			$("#search_div_"+elemetId).css("display","none");
		};
		this.check = function(e) {
			var code = this.keycode(e);
			if ((code >= 48 && code <= 57) || (code >= 96 && code <= 105) || (code >= 65 && code <= 90) || code == 8 || code == 32) {
				if ($(element).val() != $("#search_hidden_input"+elemetId).val()) {
					$("#search_hidden_input"+elemetId).val($(element).val());
					this.search();
				}
			} else if (event.keyCode == 38) {
				if ($('#search_table_'+elemetId+' tr.cy_hover').html() === undefined) {
					$('.cy_line:last').addClass("cy_hover");
					$('.cy_line:last').css({
						"color" : settings.fontHoverColor,
						"background" : settings.hoverBackground
					});
					$(element).val($('#search_table_'+elemetId+'  tr.cy_hover').text());
					$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());

				} else {
					if ($('#search_table_'+elemetId+'  tr.cy_hover').prev().html() === undefined) {
						$(element).val($("#search_hidden_input"+elemetId).val());
						$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
						var obj=$('#search_table_'+elemetId+'  tr.cy_hover');
						obj.removeClass("cy_hover");
						obj.css({
						"color" : settings.fontColor,
						"background" : settings.background
						});
					} else {
					    var objPrev=$('#search_table_'+elemetId+'  tr.cy_hover').prev();
						objPrev.addClass("cy_hover");
						objPrev.css({
						"color" : settings.fontHoverColor,
						"background" : settings.hoverBackground
						});
						 var objNext=$('#search_table_'+elemetId+'  tr.cy_hover').next();
						objNext.removeClass("cy_hover");
						objNext.css({
						"color" : settings.fontColor,
						"background" : settings.background
						});
						$(element).val($('#search_table_'+elemetId+'  tr.cy_hover').text());
						$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
					}

				}

			} else if (event.keyCode == 40) {
				if ($('#search_table_'+elemetId+'  tr.cy_hover').html()=== undefined) {
					$('.cy_line:first').addClass("cy_hover");
					$('.cy_line:first').css({
						"color" : settings.fontHoverColor,
						"background" : settings.hoverBackground
					});
					$(element).val($('#search_table_'+elemetId+' tr.cy_hover').text());
					$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
				} else {
					if ($('#search_table_'+elemetId+' tr.cy_hover').next().html() === undefined) {
						$(element).val($("#search_hidden_input"+elemetId).val());
						$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
						var obj=$('#search_table_'+elemetId+' tr.cy_hover');
						obj.removeClass("cy_hover");
						obj.css({
						"color" : settings.fontColor,
						"background" : settings.background
						});
					} else {
					    var objNext=$('#search_table_'+elemetId+'  tr.cy_hover').next();
						objNext.addClass("cy_hover");
						objNext.css({
						"color" : settings.fontHoverColor,
						"background" : settings.hoverBackground
						});
						
					    var objPrev=$('#search_table_'+elemetId+'  tr.cy_hover').prev();
						objPrev.removeClass("cy_hover");
						objPrev.css({
						"color" : settings.fontColor,
						"background" : settings.background
						});
						$(element).val($('#search_table_'+elemetId+'  tr.cy_hover').text());
						$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
					}

				}
			} else if (event.keyCode = 13) {
				if ($('#search_table_'+elemetId+' tr.cy_hover').html() != undefined) {
					$(element).val($('#search_table_'+elemetId+' tr.cy_hover').text());
					$("#"+id).val($('#search_table_'+elemetId+' tr.cy_hover td input[name="search_ids"]').val());
				} else {

				}
				$('#search_div_'+elemetId).empty();
				$('#search_div_'+elemetId).css("display", "none");
			}

		};
		this.search = function() {
			if (settings.url != "" && settings.url != undefined) {
				$.ajax({
					type : settings.type,
					url : settings.url,
					data : eval('('+settings.data+')'),
					success : function(data) {
					var left = $("#search_span_"+elemetId).offset().left;
					var top = $("#search_span_"+elemetId).offset().top + $("#search_span_"+elemetId).height();
					$("#search_div_"+elemetId).css("left", left + "px");
					$("#search_div_"+elemetId).css("top", top + "px");
					data=eval('('+data+')');
					element.pushData(data);
					}
				});
			}
		};
		this.pushData=function(data) {
			if (data != "" && data != undefined) {
				var list = new Array();
				list = data.list;
				var layer;
				layer = "<table id='search_table_"+elemetId+"' style='border:0px' cellspacing='0' cellpading='0'>";
				var index=0;
				for (var i = 0; i < list.length; i++) {
					if (i < settings.maxNum) {
						var result = {};
						if (!this.isJson(list[i])) {
							result = eval('(' + list[i] + ')')
						}
						layer += "<tr class='cy_line'><td class='cy_std'>" + list[i][settings.dataName] + "<input type='hidden' name='search_ids' value='"+list[i][settings.dataId]+"'/></td></tr>";
						index++;
					}

				}
				layer += "</table>";

				$('#search_div_'+elemetId).empty();
				$('#search_div_'+elemetId).append(layer);
				$('.cy_line').css({
					"height" : settings.searchTrHeight,
					"font-size" : settings.fontSize,
					"color" : settings.fontColor,
					"background" : settings.background
				});
				$("#search_div_"+elemetId).css("height", index * settings.searchTrHeight);
				$('#search_div_'+elemetId).css("display", "");
				$('.cy_line').hover(function() {
					$('.cy_line').removeClass("cy_hover");
					$(this).css({
						"color" : settings.fontHoverColor,
						"background" : settings.hoverBackground
					});
					$(this).addClass("cy_hover");
				}, function() {
					$(this).removeClass("cy_hover");
					$(this).css({
						"color" : settings.fontColor,
						"background" : settings.background
					});
				});
				$('#search_table_'+elemetId).css("width", $('#search_div_'+elemetId).css("width"));
				$(document).click(function(e) {
					var result = $('#search_div_'+elemetId);
					var searchInput = $(element);
					if ((e.target != result[0]) && (e.target != searchInput[0])) {
						$('#search_div_'+elemetId).empty();
						$('#search_div_'+elemetId).css("display", "none");
					}
				});
				$('.cy_std').click(function() {
					$(element).val($(this).text());
					$("#"+id).val($("input[name='search_ids']",$(this)).val());
					//$('#'+input).focus();
					$('#search_div_'+elemetId).empty();
					$('#search_div_'+elemetId).css("display", "none");
				});
			} else {
				$('#search_div_'+elemetId).empty();
				$('#search_div_'+elemetId).css("display", "none");
			}
		};
		
		this.keycode = function(e) {
			var code;
			if (!e)
				var e = window.event;
			if (e.keyCode)
				code = e.keyCode;
			else if (e.which)
				code = e.which;
			return code;
		}
		function R(id) {
            if (!(this instanceof R)) {
                return new R(id);
            }
            this[0] = document.getElementById(id);
        }
        
		this.initValAndId=function(value,id)
		{
			$('#cy_searchId_'+elemetId).val(id);
			$(element).val(value);
		}
		this.isJson = function(obj) {
			var isjson = typeof (obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
			return isjson;
		}
		this.initSearch();
	};
})(jQuery);
