import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetReadme {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 String filePath0="F:\\github\\data\\QsIDRM.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<String> idlist=new ArrayList<String>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist.add(line);			  
		 }
		 
		 
		 String filePath="F:\\github\\data\\1000qsreadme.txt";
		 BufferedReader br = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath)));
		 int count=0;
	     for (String line = br.readLine(); line != null; line = br.readLine()) {
	    	 
	    	 System.out.println(count+"\t"+idlist.get(count)+"\t"+line);
	   	    	 
	    	 String outFile = "F:\\github\\data\\readme\\"+idlist.get(count)+".txt";
			 FileOutputStream out = new FileOutputStream(outFile, true);
	    	 
			 String repo=captureHtml(line); 
	    	
	    	 StringBuffer   sb = new StringBuffer();
	
             sb.append(repo);
				
	         out.write(sb.toString().getBytes("utf-8"));
	         
		     count++;
		     out.close();
		  }
	     br.close();
	     br0.close();
		
		
		
	}

	
	public static String captureHtml(String strURL) throws Exception {
		//String strURL = "https://api.github.com/repos/pylerSM/Xinstaller/stargazers?page=2";
				//"https://api.github.com/users/tupunco/starred?page=2";
				//"https://api.github.com/repos/StylingAndroid/StylingActionBar/stargazers?page=1&per_page=100";
		//strURL= strURL + "/stargazers";
		//strURL=strURL+"access_token=b17aa0ba2a1bbb8f648f19d566dc0c17adfd508b";
		//strURL=strURL+"/master/README.md";
		URL url = new URL(strURL);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	    //httpConn.setRequestProperty("Accept", "application/vnd.github.v3.star+json");

		//httpConn.setFollowRedirects(true);   
		  
		//httpConn.setInstanceFollowRedirects(false);   
		  
		//httpConn.connect(); 
		//System.out.println("1");
		
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		
		//System.out.println("2");
		while ((line = bufReader.readLine()) != null){ 
			contentBuf.append(line);
		}
		
	     //System.out.println("3");
		
	    String buf = contentBuf.toString();
//		int beginIx = buf.indexOf("<div class=\"module sidebar-related\">");
//		int endIx = buf.indexOf("<div id=\"hot-network-questions\" class=\"module\">");
//		String frag = buf.substring(beginIx, endIx);
//		
//		System.out.println("captureHtml()的结果：\n" + frag);
//		//System.out.println("captureHtml()的结果：\n" + buf);
//		String[] temp=frag.split("/q/");
//		String[] result = new String[15];
//		for(int i=1;i<temp.length;i++){
//			String[] nums = temp[i].split("\\D+");
//			result[i-1]=nums[0];
//			System.out.println(result[i-1]);
//		}
		return buf;
	}
	
	
}
