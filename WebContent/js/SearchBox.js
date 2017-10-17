function getData(input,hiddenInput,searchDiv,url,key)
{

 $.ajax({ 
   type:"post",
   url:url,
   data:key,
   success:function(data){
   if(data!=""&&data!=undefined)
   {
    var obj=eval('('+data+')');
    var ss=new Array();
    ss = obj.list;
    var layer;
    layer = "<table id='aa'>";
    for(var i=0;i<ss.length;i++){
    	layer += "<tr class='line'><td class='std' id='searchtd_"+i+"'>"+ss[i].name+"("+ss[i].code+";"+ss[i].dept_name+")<input name='search_id' style='display:none' value='"+ss[i].id+"'/></td></tr>";
    }
    layer += "</table>";
    $('#'+searchDiv).empty();
    $('#'+searchDiv).append(layer);
     $('#'+searchDiv).css("width",$("#"+input).width());
    $('#'+searchDiv).css("display","");
   
    	$('#'+searchDiv).css("height","200px");
    	$('#'+searchDiv).css("overflow","scroll");
    var left = $('#'+input).offset().left;//获取距离最左端的距离，像素，整型
   var top = $('#'+input).offset().top+25;//获取距离最顶端的距离，像素，整型（26为搜索输入框的高度）
   $('#'+searchDiv).css("left",left+"px");//重新定义CSS属性
   $('#'+searchDiv).css("top",top+"px");
    $('.line').hover(function(){
     $('.line').removeClass("hover");
     $(this).addClass("hover");
    },function(){
     $(this).removeClass("hover");
    });
     $('.std').css("width",$("#"+input).width());
      $(document).click(function(e){
      var result = $("#"+searchDiv);
      var searchInput=$("#"+input);
       if((e.target != result[0])&&(e.target!=searchInput[0])){
           $('#'+searchDiv).empty();
        	$('#'+searchDiv).css("display","none");
         }
      });
    $('.std').click(function(){
     $('#'+input).val($(this).text());
     var id=$(this).attr("id");
     $("#productId").val($("#"+id+" input[name=search_id]").val());
    //$('#'+input).focus();
    $('#'+searchDiv).empty();
    $('#'+searchDiv).css("display","none");
    });
   }
   else{
    $('#'+searchDiv).empty();
    $('#'+searchDiv).css("display","none");
   }
 }
 });

}
function search(input,hiddenInput,searchDiv,url,key)
{
var event=event||window.event;
if((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=96 && event.keyCode<=105) || (event.keyCode>=65 && event.keyCode<=90) || event.keyCode==8||event.keyCode==32)
{
    if($("#"+hiddenInput).val()!=$("#"+input).val())
    {
    	$("#"+hiddenInput).val($("#"+input).val());
        getData(input,hiddenInput,searchDiv,url,key);
    }
   
  
} 
 else if(event.keyCode == 38){
   if($('#aa tr.hover').html()==undefined)
  {
	$('.line:last').addClass("hover");
       $('#'+input).val($('#aa tr.hover').text());
  }
 
 else
  {
    if($('#aa tr.hover').prev().html()==undefined)
        {
	  $("#"+input).val($("#"+hiddenInput).val());
          $('#aa tr.hover').removeClass("hover");
        }
        else
        {
		$('#aa tr.hover').prev().addClass("hover");
              $('#aa tr.hover').next().removeClass("hover");
              $('#'+input).val($('#aa tr.hover').text());
              
        }
    
  }
 
  
 }else if(event.keyCode == 40){
   if($('#aa tr.hover').html()==undefined)
  {
	$('.line:first').addClass("hover");
        $('#'+input).val($('#aa tr.hover').text()); 
  }
  else
  {
        if($('#aa tr.hover').next().html()==undefined)
        {
	  $("#"+input).val($("#"+hiddenInput).val());
          $('#aa tr.hover').removeClass("hover");
        }
        else
        {
         $('#aa tr.hover').next().addClass("hover");
         $('#aa tr.hover').prev().removeClass("hover");
         $('#'+input).val($('#aa tr.hover').text());
        }
	
       
  }
}
else if(event.keyCode=13)
{
	$('#'+input).val($('#aa tr.hover').text());
	 var id=$('#aa tr.hover td').attr("id");
	 $("#productId").val($("#"+id+" input[name=search_id]").val());
	 $('#'+searchDiv).empty();
    $('#'+searchDiv).css("display","none");
}
}


