// 搜索
function reSearch() {
    for (var i = 0; i < tables.length; i++) {
    	var a = '';
        //拼接查询条件
        for (var int = 0; int < objs.length; int++) {
        	//判断类型
        	if (types[int] == 'date') {
        		a = a + objs[int] + ':' + $('#'+objs[int]).datebox('getValue') + ',';
			}else if (types[int] == 'combobox') {
				a = a + objs[int] + ':\'' + $('#'+objs[int]).combobox('getValue') + '\',';
			}else {
				a = a + objs[int] + ':\'' + $('#'+objs[int]).val() + '\',';
			}
    	}
        a = '{' + a.substring(0, a.length-1) + '}';
    	$('#table'+tables[i]).datagrid('load', eval("(" + a + ")"));
	}
}
//解析日期--yyyyMMdd
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return ''+y+(m<10?('0'+m):m)+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var y = s.substring(0, 4);
	var m = s.substring(4, 6);
	var d = s.substring(6, 8);
	var h = s.substring(8, 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	}else{
		return new Date();
	}
}
//解析日期--yyyyMMddhh
function myformatter1(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	return ''+y+(m<10?('0'+m):m)+(d<10?('0'+d):d)+(h<10?('0'+h):h);
}
function myparser1(s){
	if (!s) return new Date();
	var y = s.substring(0, 4);
	var m = s.substring(4, 6);
	var d = s.substring(6, 8);
	var h = s.substring(8, 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h)){
		return new Date(y,m-1,d,h);
	}else{
		return new Date();
	}
}

//获取url中的参数
function GetQueryString(name) {
	 var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if(r!=null)return  unescape(r[2]); return null;
}

//根据类别生成对应的组建样式 -- 查询框  type：文本类型  style：文本样式 format:文本格式 value:默认值 
function genStyleForSearch(type, style, format, value){
	var combstyle = '';
	var dataopt = '';
	var myformat = '';
	if (type == 'date') {//日期类型
		combstyle = "'easyui-datetimebox' ";
		//判断日期格式
		if (format == 'yyyyMMdd') {
			myformat = '';
		}else if(format == 'yyyyMMddhh'){
			myformat = '1';
		}else {
			myformat = '';
		}
		//日期类型 生成日期格式
		dataopt = 'formatter:myformatter'+myformat+',parser:myparser'+myformat;
	}else if (type == 'combobox') {//下拉框
		combstyle = "'easyui-combobox' ";
		dataopt = 'valueField:\'id\', textField:\'text\'';
		//生成下拉框选项
	}else {//没有对应的，生成文本框
		combstyle = "'easyui-textbox' ";
	}
	//默认值
	if (value == null) {
		value = '';
	}else if (value != '' ) {
		value = "value='"+value+"'";
	}
	//返回最终样式
	return combstyle+value+" style='"+style+"' data-options=\""+dataopt+"\"";
}

//根据类别生成对应的组建样式 -- 数据table
function genStyleForTable(type, style, format){
	var combstyle = '';
	var dataopt = '';
	if (type == 'date') {//日期类型
		combstyle = 'easyui-datetimebox';
		//日期类型 生成日期格式
		dataopt = 'formatter:myformatter,parser:myparser';
	}else if (type == 'combobox"') {//下拉框
		combstyle = 'easyui-combobox';
		//生成下拉框选项
	}else {//没有对应的，生成文本框
		combstyle = 'easyui-textbox';
	}
	//返回最终样式
	return "class='"+combstyle+"' style='"+style+"' data-options='"+dataopt+"'";
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    if (result != '/data-platform') {
		return '';
	}else {
		return result;
	}
}