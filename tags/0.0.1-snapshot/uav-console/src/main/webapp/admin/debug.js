window.isDevelop = true;
function getString(obj){
	var res = "";
	if(typeof obj == "object")
	{
		var n;
		for(n in obj)
		{
			res += n + ":" +obj[n]+"\n";
		}
		if(Ext.isArray(obj))
		{
			res = "[\n"+res+"]";
		}else{
			res = "{\n"+res+"}";
		}
	}else{
		res = res + obj;
	}
	return res;
}
function trace(obj){
	var res = getString(obj);
	var len = arguments.length;
	for(var i=1;i<len;i++)
	{
		res +=  "," + getString(arguments[i]);
	}
	console.log(res);
}

var marks = {};
function before(k){
	k = k || ' ';
	marks[k] = new Date().getTime();
}
function after(k){
	k = k || ' ';
	console.log(''+ k +'='+(new Date().getTime()-marks[k]));
}