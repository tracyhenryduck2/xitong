//-------------------- ui tag --------------------------------
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
	function forbidBackSpace(e) {
		var ev = e || window.event; //获取event对象
		var obj = ev.target || ev.srcElement; //获取事件源
		var t = obj.type || obj.getAttribute('type'); //获取事件源类型
		//获取作为判断条件的事件类型
		var vReadOnly = obj.readOnly;
		var vDisabled = obj.disabled;
		//处理undefined值情况
		vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
		vDisabled = (vDisabled == undefined) ? true : vDisabled;
		//当敲Backspace键时，事件源类型为密码或单行、多行文本的，
		//并且readOnly属性为true或disabled属性为true的，则退格键失效
		var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
		//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
		var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
		//判断
		if (flag2 || flag1) return false;
	}
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress = forbidBackSpace;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown = forbidBackSpace;

function download(fileUrl,fileName){
	window.location="../DownLoad!downLoad.action?fileRealName="+fileUrl+"&fileName="+fileName;
}

/**
 * TabNavigation
 * @author yswang
 * @date 2009/06/07
 */
function goCenter(){
	parent.document.URL="../Index!center.action";
}

/*------------------------------------------------------------------------------*/
/*
var P ,D ;
try{
	P = window.parent, D = P.getOpener();
}catch(e){}
*/
// 阻止事件传递和冒泡
function clearEvent(evt){
	 evt = evt || window.event;
     if (evt.stopPropagation){ evt.stopPropagation(); }else{ evt.cancelBubble = true;} 
 	 //if (evt.preventDefault){  evt.preventDefault();  }else{ evt.returnValue = false;}
}

function toUTF8(szInput){ 
	 var wch,x,uch="",szRet="";
	 for (x=0; x<szInput.length; x++){
		  wch=szInput.charCodeAt(x);
		  if (!(wch & 0xFF80)){
		   szRet += szInput.charAt(x);
		  }else if (!(wch & 0xF000)){
		   uch = "%" + (wch>>6 | 0xC0).toString(16) + 
		      "%" + (wch & 0x3F | 0x80).toString(16);
		   szRet += uch; 
		  }else{
		   uch = "%" + (wch >> 12 | 0xE0).toString(16) + 
		      "%" + (((wch >> 6) & 0x3F) | 0x80).toString(16) +
		      "%" + (wch & 0x3F | 0x80).toString(16);
		   szRet += uch; 
		  }
	 }
	 return(szRet);
}

/**
 * 文本框只能输入int
 * author:yswang
 * Example: <input type="text" onkeypress="return filterInt(event);"/>
 */
function filterInt(evt){
	evt = evt || window.event;
	var _keyCode = evt.keyCode || evt.which;
	return (( _keyCode >=48 && _keyCode <= 57 ) || _keyCode == 44 || _keyCode == 45 );
}

/**
 * 文本框只能输入浮点数
 * author:yswang
 * Example: <input type="text" onkeypress="return filterFloat(event);"/>
 */
function filterFloat( evt ){
	evt = evt || window.event;
	var _keyCode = evt.keyCode || evt.which;
	var obj = evt.srcElement||evt.target;
	
	if( _keyCode == 46 ){
		var _val = obj.value;
		if( _val == "" || _val.split(".").length == 2 ){
			return false;
		}
	}
	
	return (( _keyCode >=48 && _keyCode <= 57 ) || _keyCode == 44 || _keyCode == 45 || _keyCode == 46 );
}

/**
 *  复选框全选
 */
function checkAll(_self,itemName){
	$("input[type='checkbox'][name="+itemName+"]").each(function(){
		$(this).attr("checked",_self.checked);
	});
	if(_self.checked){
		$(".dataGrid1 tbody tr").addClass("selected");
	}else{
		$(".dataGrid1 tbody tr").removeClass("selected");
	}
}

/**
 *  获取复选框选中的数量
 */
function getCheckedNum(itemName){
	var _checkedArr = $("input[type='checkbox'][name="+itemName+"]:checked");
	return _checkedArr.length;
}

/**
 *  获取复选框选中的值，多个值之间以逗号,分隔
 */
function getCheckedValue(itemName){
	var _value = "";
	var _len = getCheckedNum(itemName);
	if(_len > 0){
		$("input[type='checkbox'][name="+itemName+"]:checked").each(function(i){
			_value += $(this).val();
			if(i != _len -1){
				_value += ",";
			}
		});
	}
	return _value;
}

/**
 *  判断复选框是否至少有一个被选中
 */
function isCheckAny(itemName){
	var _checkedNum = getCheckedNum(itemName);
	return (_checkedNum > 0);
}

/**
 *  判断复选框是否只有一个被选中
 */
function isCheckOne(itemName){
	var _checkedNum = getCheckedNum(itemName);
	return (_checkedNum == 1);
}

/**
* @param url 异步调用的url
* @param div 动态图层的名称
* @return 动态载入指定url的数据
*/
function loadDynamicData(url,div){
	document.getElementById(div).innerHTML="";
	$.ajax({
		url:url,
		cache:false,
		success:function(html){
			document.getElementById(div).innerHTML=html;
		}
	});
} 

/**
 * 渲染按钮
 */
function cssButton( $button ){
	$button.addClass("buttonBg")
			.mouseover(function(){
				$(this).addClass("buttonHoverBg");
			}).mouseout(function(){
				$(this).removeClass("buttonHoverBg");
			});
}
function renderButton(){
	cssButton( $(":submit") );
	cssButton( $(":button") );
	cssButton( $(":reset") );
	cssButton( $("button") );
}
/**
 * 渲染文本框，文本域
 */
function cssTextField( $textField ){
	$textField.each(function(){
		$(this).attr("autocomplete","off");
		if( $(this).attr("render") != 'false' ){
			$(this).addClass('textField')
				   .focus(function(){$(this).addClass("focus");})
				   .blur(function(){$(this).removeClass("focus");});
		}
	});
}
function renderTextField(){
	cssTextField( $(":text") );
	cssTextField( $(":password") );
	//$("input:disabled").addClass('disabled');
	
	$("textarea").focus(function(){$(this).addClass("focus");})
			  	 .blur(function(){$(this).removeClass("focus");});
}
$(function(){
	renderTextField();
	renderButton();
	
	// 点击页面关闭开始菜单
	$(document).bind("mousedown", function(){
		try{
			top.hideStartMenu();
		}catch(e){}
	});
});

/**
 * 屏蔽 F5刷新，Ctrl+N 新建窗口， 退格键等
 * 注册Ctrl+D显示工作台
 */
 document.onkeydown = function(evt){
	evt = evt || window.event;
	if( evt.keyCode == 116 // F5
		|| (evt.ctrlKey && evt.keyCode == 82) // Ctrl+R
		|| (evt.ctrlKey && evt.keyCode == 78) // Ctrl+N
		|| (evt.shiftKey && evt.keyCode == 121) // Shift+F10
		|| (evt.srcElement.type !='text' && evt.srcElement.type !='textarea' && evt.srcElement.type !='password' && evt.keyCode == 8) // 退格删除键
	){
		if(evt.preventDefault){
			evt.preventDefault();
		}else{
			evt.keyCode = 0;
			evt.returnValue = false;
		}
	}
	//evt.ctrlKey && evt.keyCode == 68 ->Ctrl+D
	/*
	if(evt.which || (evt.ctrlKey && evt.altKey)){
		top.showDeskTop();
		if(evt.preventDefault){
			evt.preventDefault();
		}else{
			evt.keyCode = 0;
			evt.returnValue = false;
		}
	}*/
}

//判断是否为空
function isBlank(str)
{
	var strDst = new String(str);
	if(strDst.length == 0)
		return true;
	for(i = 0; i < strDst.length; i++){
		if(strDst.charAt(i) != " "){
			return false;
		}
	}
	return true;
}
function trim(value){
	return value.replace(/(^\s*)|(\s*$)/g,"");
}
function IsLegal(dstStr)
{
	dstStr = String(dstStr);	
	if(dstStr.indexOf("'") != -1 || dstStr.indexOf("\"") != -1){
		return false;
	}	
	return true;
}
function isNum(strNum){
	var input = String(strNum);
	for(var i=0;i<input.length;i++){
		if(!(input.charAt(i) <= '9' && input.charAt(i) >= '0')) {
			return false;
		}
	}
	return true;
}

//判断IP地址是否正确.
function isIPAddr( str )
{
	return /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/.test(str);
}
function showQQ(url,width,height){
	var left=eval(screen.Width-width)/2;   //离左边距离
	var top =eval(screen.Height-height)/2; //离上边距离
	window.open(url,"","width="+width+",height="+height+",left="+left+",top="+top+",toolbar=no,menubar=no,scrollbars=no,resizable=yes");
}

/**
 * 验证表字段的唯一性
 * @param tableName
 * @param fieldName
 * @param fieldValue
 * @param keyId
 * @param keyValue
 * @returns {Boolean}
 */
function checkTableFieldUnique(tableName,fieldName,fieldValue,keyId,keyValue) {
	 var html = $.ajax({
		  data: "tableName="+tableName+"&fieldName="+fieldName+"&fieldValue="+fieldValue+"&keyName="+keyId+"&keyValue="+keyValue,
		  url: g_Path+"/common/Common!isDbExist.action?",
		  type: 'POST',
		  async: false
		 }).responseText;
	 if(html==true || html=="true") {
		 return true;
	 } else {
		 return false;
	 }
}

//同步函数
function getAjaxResult(action,data) {
	var html = $.ajax({
		  data: data,
		  url: action,
		  async: false
		 }).responseText;
	return html;
}


/**
 * 共用导出函数
 */
function exportExcel() {
	if(!isCheckAny("idArr")){
		Dialog.alert("请至少勾选一个!");
		return;
	}
	
	var keyArr = [];
	var str = "";
	var keyStr = "";
	var tmpIndex = 0;
	var flagArr = [];
	$("#table1 thead tr th").each(function(i){
		if($(this).find("div").length >0 ) {
			//alert($(this).find("div").html() + " " + $(this).find("div").attr("id"));
			str  += "'"+tmpIndex+"':'"+$(this).find("div").html()+"',";
			keyStr += "'"+tmpIndex+"':'"+$(this).find("div").attr("id")+"',";
			keyArr[tmpIndex++] = $(this).find("div").attr("id");
			flagArr[i] = 1;
		} else {
			flagArr[i] = 0;
		}
	});
	
	if(str.length>0) {
		str = "{"+str.substring(0,str.length-1)+"},";
		str += "{"+keyStr.substring(0,keyStr.length-1)+"}";
	}
	$("input[type='checkbox'][name='idArr']:checked").each(function(i){
		str += ",{";
		var tmpStr = "";
		var tmpIndex = 0;
		$(this).parent().parent().find("td").each(function(i){
			if(flagArr[i]==1){
				tmpStr += "'"+keyArr[tmpIndex++]+"':'"+$(this).text()+"',";
			}
		 });
		if(tmpStr.length>0) {
			tmpStr = tmpStr.substring(0,tmpStr.length-1);
		}
		str += tmpStr;
		str += "}";
	});
	pageExport(str.replace(/[\r\n]/g,""));
}

function getHeadStr(){
	var str = "";
	var keyStr = "";
	var tmpIndex = 0;
	$("#table1 thead tr th").each(function(i){
		if($(this).find("div").length >0 ) {
			//alert($(this).find("div").html() + " " + $(this).find("div").attr("id"));
			str  += "'"+tmpIndex+"':'"+$(this).find("div").html()+"',";
			var _sid = $(this).find("div").attr("sid");
			if(_sid!=null && _sid!="") {
				keyStr += "'"+tmpIndex+"':'"+_sid+"',";
			} else {
				keyStr += "'"+tmpIndex+"':'"+$(this).find("div").attr("id")+"',";
			}
			tmpIndex++;
		}
	});
	if(str.length>0) {
		str = "{"+str.substring(0,str.length-1)+"},";
		str += "{"+keyStr.substring(0,keyStr.length-1)+"}";
	}
	return str;
}

function pageExport(data) {
	if($("#common_page_export_id").html()!=null && $("#common_page_export_id").html().length>0) {
		$("#common_page_export_data_id").val(data);
		common_page_export.submit();
	} else {
		var str = "<div id=\"common_page_export_id\">" +
			"<form action=\""+g_Path+"/common/Common!export.action\" name=\"common_page_export\" id=\"common_page_export\" method=\"post\">" +
			"<input type=\"hidden\" name=\"data\" id=\"common_page_export_data_id\" value=\""+data+"\"/>" +
			"</form></div>";
		$("body").append(str);
		common_page_export.submit();
	}
}

/**
 * 共用导出函数
 */
function exportTxt() {
	if(!isCheckAny("idArr")){
		Dialog.alert("请至少勾选一个！");
		return;
	}
	var str = "";
	$("#table1 thead tr th").each(function(i){
		if(i>0) {
			str  += $(this).html()+",";
		}
	});
	if(str.length>0) {
		str = str.substring(0,str.length-1)+"\r\n";
	}
	$("input[type='checkbox'][name='idArr'][checked]").each(function(i){
		var tmpStr = "";
		$(this).parent().parent().find("td").each(function(i){
			if(i>0) {
				tmpStr += $(this).html()+",";
			}
		 });
		if(tmpStr.length>0) {
			tmpStr = tmpStr.substring(0,tmpStr.length-1)+"\r\n";
		}
		str += tmpStr;
	});
	pageExportTxt(str);
}

function pageExportTxt(data) {
	if($("#common_page_export_id").html()!=null && $("#common_page_export_id").html().length>0) {
		$("#common_page_export_data_id").val(data);
		common_page_export.submit();
	} else {
		var str = "<div id=\"common_page_export_id\">" +
			"<form action=\""+g_Path+"/common/Common!exportTxt.action\" name=\"common_page_export\" id=\"common_page_export\" method=\"post\">" +
			"<input type=\"hidden\" name=\"data\" id=\"common_page_export_data_id\" value=\""+data+"\"/>" +
			"</form></div>";
		$("body").append(str);
		common_page_export.submit();
	}
}


function getCheckRowValue() {
	if(!isCheckAny("idArr")){
		Dialog.alert("请勾选一行记录");
		return;
	}
	if(!isCheckOne("idArr")){
		Dialog.alert("请只勾选一行记录");
		return;
	}
	return getCheckedValue("idArr");
}

/**
 * 刷新
 */
function refresh() {
	window.location.reload();
}

function shrRadixPoint(str,n) {
	if ((str == null)||(str.toString() == "")||(str == "null")) {
		return "";
	}	
	var value = parseFloat(str);
	value = value.lShift(n);  
	
	return value.formatString(n,false);	
}

function deleteRadixPoint(str) {
	if ( (str == null)||(str.toString() == "")||(str == "null") ) {
		return "";
	}
	str=str+"";
    return (str.replace(/(^0\.)|(\.)/, "") - 0)+"";
}

/************************************************************
函数名称：addEndZero
函数功能：增加小数点[保持小数点后，一定有几位小数,如 (1.00)]
输入参数： strVal       需要校验的字符串或数
			num          几位
			strIntercept 是否截取,如2位小数，输入0.1234; 如截取，返回0.12
输出参数：无
函数说明：如： addEndZero(intCurrencyBalanceNew + "",2,"Y")
************************************************************/
function addEndZero(strVal,num,strIntercept) {
	var strCurrent = strVal + "";
   var pos = strCurrent.indexOf(".")-0;
   var endStr = "";
	if(pos>0) {
		var decimalDigits = strCurrent.length-pos-1;
		if(decimalDigits<num) {
			for (var i=1;i<=(num-decimalDigits);i++) {
				endStr=endStr+"0";
			}
			strCurrent=strCurrent+endStr;
		}
		else {
			if (strIntercept == "Y") {
				strCurrent=strCurrent.substr(0,pos+num+1);
			}
		}
	}
	else {
		if(num>0) {
			for (var i=0;i<num;i++) {
				endStr=endStr+"0";
			}
			strCurrent = strCurrent+"."+endStr;
		}
	}
	return strCurrent;
}

function toFloatSum(dbValue, num) {
	if(num==null) {
		num = 2;
	}
	return shrRadixPoint(dbValue,num);
}


function toIntSum(dbValue, num) {
	if(num==null) {
		num = 2;
	}
	var strSum = addEndZero(dbValue,num,"Y");
	return deleteRadixPoint(strSum);
}

function mycheckByValue(n,v){
		var arr = $("input[type='checkbox'][name='"+n+"']");
        for(var i=0;i<arr.length;i++) {
        	if(arr[i].value==v){
        		arr[i].checked=true;
        	}
        }
}

function exportAllPage(){
	var keyArr = [];
	var str = "";
	var keyStr = "";
	var tmpIndex = 0;
	var flagArr = [];
	$("#table1 thead tr th").each(function(i){
		if($(this).find("div").length >0 ) {
			//alert($(this).find("div").html() + " " + $(this).find("div").attr("id"));
			str  += "'"+tmpIndex+"':'"+$(this).find("div").html()+"',";
			keyStr += "'"+tmpIndex+"':'"+$(this).find("div").attr("id")+"',";
			keyArr[tmpIndex++] = $(this).find("div").attr("id");
			flagArr[i] = 1;
		} else {
			flagArr[i] = 0;
		}
	});
	if(str.length>0) {
		str = "{"+str.substring(0,str.length-1)+"},";
		str += "{"+keyStr.substring(0,keyStr.length-1)+"}";
	}
	
	$("input[type='checkbox'][name='idArr']").each(function(i){
		str += ",{";
		var tmpStr = "";
		var tmpIndex = 0;
		$(this).parent().parent().find("td").each(function(i){
			if(flagArr[i]==1){
				tmpStr += "'"+keyArr[tmpIndex++]+"':'"+$(this).html()+"',";
			}
		 });
		if(tmpStr.length>0) {
			tmpStr = tmpStr.substring(0,tmpStr.length-1);
		}
		str += tmpStr;
		str += "}";
	});
	pageExport(str);
}