<%@ page language="java" import="java.util.*" import="mediafile.FindAllMediaFile" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC"-//WAPFORUM//DTD XHTML Mobile 1.0//EN""http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
  <head>

	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
	<title>My JSP 'httpsource.jsp' starting page</title>
	
	<link rel="stylesheet" href="jquery-treeview/jquery.treeview.css" />
	<link rel="stylesheet" href="jquery-treeview/demo/screen.css" />
	
	<script
		src="jquery-treeview/jquery.min.js"></script>
	<script src="jquery-treeview/demo/jquery.cookie.js"></script>
	<script src="jquery-treeview/jquery.treeview.js"></script>
	
	<script type="text/javascript" src="jquery-treeview/demo/demo.js"></script>
  </head>
  
  <body>
    这是http视频源页面.. <br>
    <a href=index.jsp>转到rtsp页面</a>
    <div id="main">
		<h4>根目录</h4>
		<ul id="browser" class="filetree">
			<%
				FindAllMediaFile files = new FindAllMediaFile();
				StringBuffer buffer = new StringBuffer();
				files.dir("/usr/local/movies/", "http", buffer);
				out.print(buffer);
			%>
		</ul>
	</div>
  </body>
</html>
