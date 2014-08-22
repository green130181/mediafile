package mediafile;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

class IPv4Validator {  
	  public static final String DELIM = "\\.";  
	   
	  public boolean isValidIP(String ip) {  
	    if(ip == null || "".equals(ip.trim())) {  
	      return false;  
	    }  
	   
	    String[] parts = ip.split(DELIM);  
	   
	    if(parts.length != 4) {  
	      return false;  
	    }  
	   
	    for(String part : parts) {  
	      try {  
	        int intVal = Integer.parseInt(part);  
	        if(intVal < 0 || intVal > 255) {  
	          return false;  
	        }  
	   
	      } catch(NumberFormatException nfe) {  
	        return false;  
	      }  
	    }  
	   
	    return true;  
	  }  
}
	  
public class FindAllMediaFile {
	public String helloworld() {
		return "Hello World";
	}
	public static String hostIP = null;
	public String topPath = null;
	
	public void dir(String path, String prefixStr, StringBuffer outString) throws UnknownHostException {
		if (topPath == null) {
			topPath = path;

			IPv4Validator validator = new IPv4Validator();
			Enumeration<NetworkInterface> netInterfaces = null;  
			try {  
			    netInterfaces = NetworkInterface.getNetworkInterfaces();  
			    while (netInterfaces.hasMoreElements()) {  
			        NetworkInterface ni = netInterfaces.nextElement();  
			        System.out.println("DisplayName:" + ni.getDisplayName());  
			        System.out.println("Name:" + ni.getName());
			        if (ni.getName().equals("lo") == true) {
			        	continue;
			        }
			        Enumeration<InetAddress> ips = ni.getInetAddresses();  
			        while (ips.hasMoreElements()) {  
			        	InetAddress element = ips.nextElement();
			            System.out.println("IP:" + element.getHostAddress());
			            System.out.println(validator.isValidIP(element.getHostAddress()));
			            if (validator.isValidIP(element.getHostAddress())) {
			            	hostIP = element.getHostAddress();
			            	System.out.println("hostIP " + hostIP);
			            }
			        }  
			    }  
			} catch (Exception e) {  
			    e.printStackTrace();  
			} 
			hostIP = "119.254.108.145";
		}
		
		File file = new File(path);
		File[] tempList = file.listFiles();
		/*
		<li><span class="folder">Folder 2</span>
		<ul>
			<li><span class="folder">Subfolder 2.1</span>
				<ul id="folder21">
					<li><span class="file">File 2.1.1</span></li>
					<li><span class="file">File 2.1.2</span></li>
				</ul></li>
			<li><span class="file">File 2.2</span></li>
		</ul></li>
		
		<li><span class="file"><a href=rtsp://172.16.14.187/test.mp4>Item 1.1</a></span></li>
		
		out.print("<a href=" + url + ">" + "W3School</a>");
				files.dir("/home/zhangzhao/work/media/borqs/new");
		*/
//		System.out.println("该目录下对象个数：" + tempList.length);
		outString.append("<li><span class=\"folder\">" + path.substring(path.lastIndexOf('/') + 1) + "</span><ul>");
		
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//				System.out.println("文     件：" + tempList[i]);
				String tempFile = tempList[i].toString();
				String subFile = tempFile.substring(tempFile.lastIndexOf('/') + 1);
				outString.append("<li><span class=\"file\">");
				outString.append("<a href=" + prefixStr + "://" + hostIP + "/");
				if (prefixStr.equals("http") == true) {
					outString.append("movies/");
				}
				outString.append(tempFile.substring(tempFile.indexOf(topPath) + topPath.length()) + ">" + subFile + "</a>");
				outString.append("</span></li>");
//				+ subFile.substring(subFile.lastIndexOf('/') + 1) + "</span></li>");
			}
			if (tempList[i].isDirectory()) {
//				System.out.println("文件夹：" + tempList[i]);
				dir(tempList[i].toString(), prefixStr, outString);
			}
		}
		outString.append("</ul></li>");
	}
}
