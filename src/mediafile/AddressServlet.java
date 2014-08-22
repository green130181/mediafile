package mediafile;

import javax.mail.Address;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.SortedMap;  
import java.util.TreeMap;  
  

import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject; 
/**
 * Servlet implementation class AddressServlet
 */
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("doPost");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.print("service");
		// TODO Auto-generated method stub
		 // 创建addressbook数据结构  
        SortedMap addressBook = new TreeMap();  
  
        // 创建新的address实体并放置到Map中  
//        Address annMichaels = new Address("P.O BOX 54534", "Seattle, WA", 42452,  
//                "561-832-3180", "531-133-9098");  
//        addressBook.put("Ann Michaels", annMichaels);  
//  
//        Address bettyCarter = new Address("53 Mullholand Drive", "Miami, FL", 72452,  
//                "541-322-1723", "546-338-1100");  
//        addressBook.put("Betty Carter", bettyCarter);  
  
        try {  
  
            // 准备转换通讯簿映射到JSON数组   
            // 数组用于放置多个地址条目  
            JSONArray jsonAddressBook = new JSONArray();  
  
            // 迭代过滤地址簿条目  
            {
//            for (Iterator iter = addressBook.entrySet().iterator(); iter.hasNext();) {  
  
                // 找当前迭代项   
//                Map.Entry entry = (Map.Entry) iter.next();  
//                String key = (String) entry.getKey();  
//                Address addressValue = (Address) entry.getValue();  
  
                // 以键值对的形式存入条目并分配给"name"  
                JSONObject jsonResult = new JSONObject();  
                jsonResult.put("name", "Miami, FL");  
  
                // 获取和创造相应的地址结构，綁定到新Key  
                // 追加地址条目到JSON格式结果   
                String streetText = "P.O BOX 54534";//addressValue.getStreet();  
                String cityText = "Seattle, WA";//addressValue.getCity();  
                int zipText = 42452;//addressValue.getZip();  
  
                //--注：Bean可以直接转换成JSON串，下面可以采用 new JSONObject(addressValue)  
                //形式来自动转换成JSON串  
                JSONObject jsonAddrObj = new JSONObject();  
  
                //以数组的形式存放，street为存放数组的变量名即Key，如果key不存在，则新建，如果存在，  
                //则在原来数组后追加  
                  
                //jsonAddress.append("street", streetText);  
                //上句等价于下面语句   
                jsonAddrObj.put("street", new JSONArray().put(streetText));  
                jsonAddrObj.append("city", cityText);  
                jsonAddrObj.append("city", "changsha");//追加  
                //上两句等价于如下语句  
                //jsonAddrObj.put("city", new  JSONArray().put(cityText).put("changsha"));  
                jsonAddrObj.append("zip", new Integer(zipText));  
                jsonResult.put("address", jsonAddrObj);  
  
                // 获取和结构，建立相应的电话到新的Key   
                // 追加在电话条目到JSON格式的结果里  
                String telText = "561-832-3180";//addressValue.getTel();  
                String telTwoText = "531-133-9098";//addressValue.getTelTwo();  
                JSONArray jsonTelephones = new JSONArray();  
                jsonTelephones.put(telText);  
                jsonTelephones.put(telTwoText);  
                jsonResult.put("phoneNumbers", jsonTelephones);  
  
                // 把JSON地址条目放置到全局的JSON地址薄数组里  
                jsonAddressBook.put(jsonResult);  
//            } // 结束循环地址薄
            }
  
            // 赋值JSON地址薄到结果字符变量   
            JSONObject resultJsonObj = new JSONObject().put("addressbook",  
                    jsonAddressBook);  
  
            //格式化输出到页面上的JSON串  
            String jsonHtmlStr = resultJsonObj.toString(4).replaceAll(" ", "&nbsp;")  
                    .replaceAll("\n", "<br>");  
  
            JSONObject jsonObj = new JSONObject().put("jsonHtmlStr", jsonHtmlStr).put(  
                    "jsonCode", resultJsonObj.toString());  
            // 返回JSON串  
            resp.getOutputStream().write(jsonObj.toString().getBytes());  
  
            System.out.println(resultJsonObj.toString(4));  
            //System.out.println(jsonObj.toString(4));  
            System.out.println("-----------------------------------------------");  
            readJSONString(req);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
	/** 
     * 从客户端读取JSON串 
     * @param req 
     */  
    public void readJSONString(HttpServletRequest req) {  
        System.out.println("接收到客户端的JSON信息如下：");  
        JSONObject clientJSONObj;  
        try {  
            clientJSONObj = new JSONObject(req.getParameter("jsonStr"));  
  
            System.out.println(clientJSONObj.toString(4));  
            JSONArray addressbookArray = clientJSONObj.getJSONArray("addressbook");  
            for (int i = 0; i < addressbookArray.length(); i++) {  
                System.out.println("The" + " " + (i + 1) + " addressbook msg:");  
                JSONObject addressbookJSONObj = addressbookArray.getJSONObject(i);  
                JSONObject addressJSONObj = addressbookJSONObj.getJSONObject("address");  
                System.out.println("address-------");  
                System.out.println("           " + addressJSONObj.getString("city"));  
                System.out.println("           " + addressJSONObj.getString("street"));  
                System.out.println("           " + addressJSONObj.getInt("zip"));  
                System.out.println("name----------");  
                System.out.println("           " + addressbookJSONObj.getString("name"));  
                System.out.println("phoneNumbers--");  
                JSONArray phoneNumbersArr = addressbookJSONObj  
                        .getJSONArray("phoneNumbers");  
                System.out.println("           " + phoneNumbersArr.getString(0));  
                System.out.println("           " + phoneNumbersArr.getString(1));  
                System.out.println();  
            }  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
    }  

}
