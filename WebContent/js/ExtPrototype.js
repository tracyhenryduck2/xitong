Object.extend(Object,
{
	copyObject : function(obj) 
	{
		if(obj == null)
		{
			return null;
		}
		var destination;
		if(ZTEsoft.lang.isArray(obj))
		{
			destination = [];
			for(var i=0;i<obj.length;i++)
			{
				destination[destination.length]=Object.copyObject(obj[i]);
			}
			return destination;
		}
		else
		{
			if(ZTEsoft.lang.isNumber(obj) || ZTEsoft.lang.isString(obj) || ZTEsoft.lang.isBoolean(obj) ||ZTEsoft.lang.isDate(obj))
			 	destination=obj;
			else
				destination = {};
			for (var property in obj) 
			{
				try{
					if(typeof obj[property] == 'object')
					{
						destination[property] = Object.copyObject(obj[property]);
					}else
					{
						destination[property] = obj[property];
				  	}
					
				}catch(e)
				{
					alert(property);
				}
			 }
			return destination;
		}
	}
});
Object.extend(Object,
{
	equals : function(obj1,obj2) 
	{
		if(obj1 == obj2)
		{
			return true;
		}
		if(typeof(obj1)!=typeof(obj2))
		{
			return false;
		}
		if(ZTEsoft.lang.isArray(obj1))
		{
			if(obj1.length!=obj2.length)
			{
				
				return false;
			}
			for(var i=0;i<obj1.length;i++)
			{
				if(!Object.equals(obj1[i],obj2[i]))
				{
					return false;
				}
			}
		}
		else
		{
			for (var property in obj1) 
			{
				try{
					if(typeof obj1[property] == 'object')
					{
						if(!Object.equals(obj1[property],obj2[property]))
						{
							return false;
						}
					}else
					{
						if(!obj1[property]==obj2[property])
						{							
							return false;
						}
				  	}
					
				}catch(e)
				{
					alert(property);
					return false;
				}
			 }
			return true;
		}
	}
});


//extend to Array
Object.extend(Array.prototype, {
		remove : function(index, size){
			if (index < 0 || index >= this.length) {
				return this;
			}
			var ret = this.slice(0, index);
			var numargs = arguments.length;
			if (numargs > 1 && index + size < this.length) {
				for (var i = index + size; i < this.length; i++) {
					ret[ret.length] = this[i];
				}
			}
			return ret;
		}
	}	
);

//extend to String
Object.extend(String.prototype, {
	  sizeOfString : function(){
		var strNew = this.replace(/[\u0080-\u07FF]/g,"**").replace(/[\u0800-\uFFFF]/g,"***").replace(/[^\u0000-\uFFFF]/g,"****");
		return strNew.length;	  	
	  },
	  trim : String.prototype.strip
  }
);

//extend to Number
Object.extend(Number.prototype, {
	  fixMath : function(m, n, op) {
		  var a = (m+"");
		  var b = (n+"");
		  var x = 1;
		  var y = 1;
		  var c = 1;
		  if(a.indexOf(".")>0) {
		    x = Math.pow(10, a.length - a.indexOf(".") - 1);
		  }
		  if(b.indexOf(".")>0) {
		    y = Math.pow(10, b.length - b.indexOf(".") - 1);
		  }
		  switch(op)
		  {
		    case '+':
		    case '-':
		      c = Math.max(x,y);
		      m = Math.round(m*c);
		      n = Math.round(n*c);
		      break;
		    case '*':
		      c = x*y
		      m = Math.round(m*x);
		      n = Math.round(n*y);
		      break;
		    case '/':
		      c = Math.max(x,y);
		      m = Math.round(m*c);
		      n = Math.round(n*c);
		      c = 1;
		      break;
		  }
		  return eval("("+m+op+n+")/"+c);
	  },
      
	  rShift : function(digit)
	  {
		var val = this.toString();
		var index = val.indexOf(".");
		var zeroTmp = "0000000000";
		if (index > 0) {
			var arr = val.split('.');
			if (arr[1].length > digit) {
				val = arr[0]+arr[1].substring(0,digit)+"."+arr[1].substring(digit);
			}else{
				val = arr[0]+arr[1]+zeroTmp.substring(0,digit-arr[1].length);
			}
		}else
		{
			val += zeroTmp.substring(0,digit);	
		}
		return parseFloat(val);
	  },

	  lShift : function(digit)
	  {
	  	var sign = this<0?-1:1;
		
		var val = this<0?this*-1:this;
		val = val.toString();
		
		var zeroTmp = "0000000000";
		var arr = val.split('.');
		if(arr.length==1) {
			arr[1] = "0";
		}
		
		if (arr[0].length < digit) {
			val = "0."+zeroTmp.substring(0,digit-arr[0].length)+arr[0]+arr[1];
		}else{
			val = arr[0].substring(0,arr[0].length-digit)+"."+arr[0].substring(arr[0].length-digit)+arr[1];
		}
		
		return sign*parseFloat(val);
	  },
	  	
	  formatString : function(decimal,round) {
	  	var val = this;
		if(round)
		{
			val *= Math.pow(10, decimal);
			val = Math.round(val);
			val = val.lShift(decimal);
		}
		val = val.toString();
		
		var index = val.indexOf(".");
		var zeroTmp = "0000000000";
		if(index>0) {
			var arr = val.split('.');
			if(arr[1].length>decimal) {
				val = arr[0]+"."+arr[1].substring(0,decimal);
			}else {
				val = arr[0]+"."+arr[1]+zeroTmp.substring(0,decimal-arr[1].length);
			}
		} else
		{
			val += "."+zeroTmp.substring(0,decimal);
		}
		return val;
	  }
  }
);

String.interpret = function(value){
  return value == null ? '' : String(value);
}
