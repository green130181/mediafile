<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>   
<head>   
<title> JSON Address Book </title>   
  
</head>  
<body>   
<div style="text-align:left" id="addressBookResults"></div>  

<script type="text/javascript" src="prototype-1.4.0.js"></script>   
<script type="text/javascript" src="JSON-js/json.js"></script>   
<script type="text/javascript">  

//address对象  
function address(city,street,zip){  
    this.city = city;//城市  
    this.street = street;//街道  
    this.zip = zip;//邮编  
}  
//addressbook对象  
function addressbook(city,street,zip,name,tel1,tel2){  
    //addressbook对象中含有address对象属性  
    this.address = new address(city,street,zip);  
    //人的名字属性  
    this.name = name;  
    //人的电话号码属性，且有两个电话号码  
    this.phoneNumbers = [tel1,tel2];  
}  
//创建两个addressbook对象实例，这些信在实际的项目中是由用户通过页面输入的  
var addressbookObj1 = new addressbook("Seattle, WA","P.O BOX 54534",  
                                        42452,"Ann Michaels",  
                                        "561-832-3180","531-133-9098");  
var addressbookObj2 = new addressbook("Miami, FL","53 Mullholand Drive",  
                                        72452,"Betty Carter",  
                                        "541-322-1723","546-338-1100");  

//创建要传递给后台的参数对象  
var paramObj={};  
//因为有多个(这里是两个)，我们用数组的形式  
paramObj.addressbook=new Array(addressbookObj1,addressbookObj2);  
  
//通过对象实例的toJSONString方法，JavaScript对象转JSON串  
var param = paramObj.toJSONString();  
/* alert(param);   */
  
// 定义 service URL   
var url = '/mediafile/AddressServlet?timeStamp='+new Date();  

// 通过原型创建AJAX请求的WEB服务, 响应后, 回调 showResponse 方式        
new Ajax.Request( url, { method: 'post', parameters:"jsonStr="+param,   
                            onComplete: callBack });  

// 回调方法，接收服务器返回过来的JSON串，  
//并用eval函数或String对象实例parseJSON转换成JavaScript对象  
function callBack(originalRequest)  {  
    // 获取服务器返回的JSON原始串   
    var jsonRaw = originalRequest.responseText;  
    //原始串转换成JavaScript对象  
    //var jsonRawObj = eval("(" + jsonRaw + ")");用此种方式也行  
    var jsonRawObj = jsonRaw.parseJSON();  
      
    //从json原始对象中提取HTML格式串  
    var jsonHtmlStr = jsonRawObj.jsonHtmlStr;  
    //提取AddreeBook的JSON串  
    var jsonCode = jsonRawObj.jsonCode;  
    // 通过eval动态的把JSON响应串构造成JavaScript对象  
    //var jsonContent = jsonCode.parseJSON();用此种方式也行  
    jsonContent = eval("(" + jsonCode + ")");     
      
    // 从服务器获取的JSON格式串后，显示数据到页面      
    finalResponse = "<b>服务器返回的JSON串如下： </b><br/><br>";  
    finalResponse +=  jsonHtmlStr+"<br/>";  
    finalResponse +=  "<br><b>从JSON对象提取信息如下： </b><br/><br>";  
    // 根据地址薄长度循环.  
    for (i = 0; i < jsonContent.addressbook.length; i++) {  
         finalResponse += "<hr/>";  
         finalResponse += "<i>Name:</i> " + jsonContent.addressbook[i].name + "<br/>";            
         finalResponse += "<i>Address:</i> " + jsonContent.addressbook[i].address.street   
                            + " -- " +   
                           jsonContent.addressbook[i].address.city[0] + "," +             
                           jsonContent.addressbook[i].address.zip + ".<br/>";   
         finalResponse += "<i>Telephone numbers:</i> "   
                        + jsonContent.addressbook[i].phoneNumbers[0] + " &amp; " +   
         jsonContent.addressbook[i].phoneNumbers[1] + ".";            
    }  
    // 把结果置换到结果区域并显示  
    document.getElementById("addressBookResults").innerHTML = finalResponse;  
}  
</script>   

</body>   
</html>   