<%@ page language="java" import="java.util.*" import="mediafile.FindAllMediaFile" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC"-//WAPFORUM//DTD XHTML Mobile 1.0//EN""http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>jQuery treeview</title>

<link rel="stylesheet" href="jquery-treeview/jquery.treeview.css" />
<link rel="stylesheet" href="jquery-treeview/demo/screen.css" />

<script
	src="jquery-treeview/jquery.min.js"></script>
<script src="jquery-treeview/demo/jquery.cookie.js"></script>
<script src="jquery-treeview/jquery.treeview.js"></script>

<script type="text/javascript" src="jquery-treeview/demo/demo.js"></script>

</head>
<body>
	这是rtsp视频源页面.<br>
	<a href=httpsource.jsp>转到http页面</a>
	<div id="main">
		<h4>根目录</h4>
		<ul id="browser" class="filetree">
			<%
				FindAllMediaFile files = new FindAllMediaFile();
				/* out.println(files.helloworld());
				String url = "http://119.254.108.145/sample_100kbit.mp4";
				out.print("<a href=" + url + ">" + "W3School</a>"); */
				StringBuffer buffer = new StringBuffer();
				files.dir("/usr/local/movies/", "rtsp",  buffer);
				out.print(buffer);
			%>
			<!-- <li><span class="folder">Folder 1</span>
				<ul>
					<li><span class="file"><a href=rtsp://172.16.14.187/test.mp4>Item 1.1</a></span></li>
				</ul></li>
			<li><span class="folder">Folder 2</span>
				<ul>
					<li><span class="folder">Subfolder 2.1</span>
						<ul id="folder21">
							<li><span class="file">File 2.1.1</span></li>
							<li><span class="file">File 2.1.2</span></li>
						</ul></li>
					<li><span class="file">File 2.2</span></li>
				</ul></li>
			<li class="closed"><span class="folder">Folder 3 (closed
					at start)</span>
				<ul>
					<li><span class="file">File 3.1</span></li>
				</ul></li>
			<li><span class="file">File 4</span></li>
			find . -name *.mp4 | xargs -n 1 --max-procs=1 sudo MP4Box -hint 
			-->
		</ul>
	</div>

</body>

</html>
