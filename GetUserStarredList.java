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

public class GetUserStarredList {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 String filePath0="F:\\github\\data\\QsID.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<String> idlist=new ArrayList<String>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist.add(line);			  
		 }
		 br0.close();
		 System.out.println(idlist.size());
		 
		 for(int i=0;i<idlist.size();i++){
			 
			 System.out.println("repo"+i);
			 String filePath="F:\\github\\data\\stargazers\\"+idlist.get(i)+".txt";
			 BufferedReader br = new BufferedReader(new InputStreamReader(  
		                new FileInputStream(filePath)));
			 ArrayList<staruserinfo> userinfo=new ArrayList<staruserinfo>();
			 for (String line = br.readLine(); line != null; line = br.readLine()) {
				 String[] user=line.split("\t"); 
				 staruserinfo user1=new staruserinfo(user[0],user[1],user[2]);
				 userinfo.add(user1);
			 }	
			 System.out.println(userinfo.size());

			 String outFile = "F:\\github\\data\\UserStarredList\\"+idlist.get(i)+".txt";
			 FileOutputStream out = new FileOutputStream(outFile, true);
			 
			 //get user star repo list
			 for (int repo1=0;repo1<userinfo.size();repo1++){
				    String user1=userinfo.get(repo1).getName();
				    StringBuffer   sb = new StringBuffer();
					for(int pagenum=1;pagenum<=160;pagenum++){
						String temp1=null;
						   if(pagenum%2==1){	
						     temp1=captureHtml("https://api.github.com/users/"+user1+"/starred?page="+pagenum+"&");
						   }else{
							 temp1=captureHtml2("https://api.github.com/users/"+user1+"/starred?page="+pagenum+"&");
						   }	
					if(temp1.equals("[]"))
						break;
					else{
					JSONArray jsonArray_temp1=JSONArray.fromObject(temp1);
				    Object[] obj_temp1= jsonArray_temp1.toArray();
				    for(int useri=0;useri<obj_temp1.length;useri++){ 
			    	    String repo_id=((JSONObject) obj_temp1[useri]).get("id").toString();			    	    
			    	    sb.append(repo_id+"\t");
			    	    }	    
					}
					}
					sb.append("\n");
					out.write(sb.toString().getBytes("utf-8"));
					System.out.println(repo1);
			 }
			 out.close();
		 }
		
	}

	
	public static String captureHtml(String strURL) throws Exception {
		//String strURL = "https://api.github.com/repos/pylerSM/Xinstaller/stargazers?page=2";
				//"https://api.github.com/users/tupunco/starred?page=2";
				//"https://api.github.com/repos/StylingAndroid/StylingActionBar/stargazers?page=1&per_page=100";
		//strURL= strURL + "/stargazers";
		strURL=strURL+"access_token=69b005c0f8d06a1f57a658f9bbb2f61fffcfab1b";
		URL url = new URL(strURL);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	    //httpConn.setRequestProperty("Accept", "application/vnd.github.v3.star+json");

		//httpConn.setFollowRedirects(true);   
		  
		//httpConn.setInstanceFollowRedirects(false);   
		  
		//httpConn.connect(); 

		
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		

		while ((line = bufReader.readLine()) != null){ 
			contentBuf.append(line);
		}
		

		
	    String buf = contentBuf.toString();

		return buf;
	}
	
	public static String captureHtml2(String strURL) throws Exception {
		//String strURL = "https://api.github.com/repos/pylerSM/Xinstaller/stargazers?page=2";
				//"https://api.github.com/users/tupunco/starred?page=2";
				//"https://api.github.com/repos/StylingAndroid/StylingActionBar/stargazers?page=1&per_page=100";
		//strURL= strURL + "/stargazers";
		strURL=strURL+"access_token=d88d707e6b54c6403f483e310c06d2ea1add651d";
		URL url = new URL(strURL);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	    //httpConn.setRequestProperty("Accept", "application/vnd.github.v3.star+json");

		//httpConn.setFollowRedirects(true);   
		  
		//httpConn.setInstanceFollowRedirects(false);   
		  
		//httpConn.connect(); 

		
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		

		while ((line = bufReader.readLine()) != null){ 
			contentBuf.append(line);
		}
		

		
	    String buf = contentBuf.toString();

		return buf;
	}
	
	
}
