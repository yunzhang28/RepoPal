import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class starsim {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 int num=1000;
		
		 String filePath0="F:\\github\\data\\QsID.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<String> idlist=new ArrayList<String>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist.add(line);			  
		 }
		 br0.close();
		 double starsim[][] = new double[num][num]; 
		 double starsim0[][] = new double[num][num];
		 for(int i=0;i<7;i++){
			 String filePath="F:\\github\\data\\stargazers\\"+idlist.get(i)+".txt";
			 BufferedReader br = new BufferedReader(new InputStreamReader(  
		                new FileInputStream(filePath)));
			 ArrayList<staruserinfo> userinfo=new ArrayList<staruserinfo>();
			 for (String line = br.readLine(); line != null; line = br.readLine()) {
				 String[] user=line.split("\t"); 
				 staruserinfo user1=new staruserinfo(user[0],user[1],user[2]);
				 userinfo.add(user1);
			 }	
			// System.out.println(userinfo.size());
			
			 //get user star repo list
			 ArrayList<ArrayList<String>> arrayuser1=new ArrayList<ArrayList<String>>();
			 String filePath22="F:\\github\\data\\UserStarredList\\"+idlist.get(i)+".txt";
			 BufferedReader br22 = new BufferedReader(new InputStreamReader(  
		                new FileInputStream(filePath22)));
			 for (String line = br22.readLine(); line != null; line = br22.readLine()) {
				 String[] userid=line.split("\t");
				 ArrayList<String> tempuser= new ArrayList<String>();
				 for(int uid=0;uid<userid.length;uid++){
					 tempuser.add(userid[uid]);
				 }
				 arrayuser1.add(tempuser);
			 }
			 
			/* for (int repo1=0;repo1<userinfo.size();repo1++){
				    String user1=userinfo.get(repo1).getName();
				    ArrayList<String> tempuser= new ArrayList<String>();
									    
				    for(int pagenum=1;;pagenum++){
					String temp1=captureHtml("https://api.github.com/users/"+user1+"/starred?page="+pagenum+"&");
					if(temp1.equals("[]"))
						break;
					else{
					JSONArray jsonArray_temp1=JSONArray.fromObject(temp1);
				    Object[] obj_temp1= jsonArray_temp1.toArray();
				    for(int useri=0;useri<obj_temp1.length;useri++){ 
			    	    String repo_id=((JSONObject) obj_temp1[useri]).get("id").toString();			    	    
			    	    tempuser.add(repo_id);			    	   			    	    
			    	    }	    
					}
					}
					System.out.println(repo1);
					arrayuser1.add(tempuser);
			 }*/
			 
			 
		   for(int j=0;j<idlist.size();j++){
				 String filePath2="F:\\github\\data\\stargazers\\"+idlist.get(j)+".txt";
				 BufferedReader br2 = new BufferedReader(new InputStreamReader(  
			                new FileInputStream(filePath2)));
				 ArrayList<staruserinfo> userinfo2=new ArrayList<staruserinfo>();
				 for (String line = br2.readLine(); line != null; line = br2.readLine()) {
					 String[] user=line.split("\t"); 
					 staruserinfo user1=new staruserinfo(user[0],user[1],user[2]);
					 userinfo2.add(user1);
			     }	
			   //  System.out.println(userinfo2.size());
				 
			 //get user star repo list
			 ArrayList<ArrayList<String>> arrayuser2=new ArrayList<ArrayList<String>>();
			 
			 String filePath3="F:\\github\\data\\UserStarredList\\"+idlist.get(j)+".txt";
			 BufferedReader br3 = new BufferedReader(new InputStreamReader(  
		                new FileInputStream(filePath3)));
			 for (String line = br3.readLine(); line != null; line = br3.readLine()) {
				 String[] userid=line.split("\t");
				 ArrayList<String> tempuser= new ArrayList<String>();
				 for(int uid=0;uid<userid.length;uid++){
					 tempuser.add(userid[uid]);
				 }
				 arrayuser2.add(tempuser);
			 }
			 
			 
			/* for (int repo2=0;repo2<userinfo2.size();repo2++){
				 String user2=userinfo2.get(repo2).getName();
				 ArrayList<String> tempuser2= new ArrayList<String>();
				 for(int pagenum2=1;;pagenum2++){
						String temp2=captureHtml("https://api.github.com/users/"+user2+"/starred?page="+pagenum2+"&");
						if(temp2.equals("[]"))
							break;
						else{
						JSONArray jsonArray_temp2=JSONArray.fromObject(temp2);
					    Object[] obj_temp2= jsonArray_temp2.toArray();
					    for(int userj=0;userj<obj_temp2.length;userj++){ 
				    	    String repo_id2=((JSONObject) obj_temp2[userj]).get("id").toString();
				    	    tempuser2.add(repo_id2);			    	   			    	    
				    	    }	    
						}
						}
				 arrayuser2.add(tempuser2);
			 }
			 */
			 
			 //compute repo similarity
				 double reposim=0;
				 int count=0;
				 int nonzerocount=0;
				 for (int repo1=0;repo1<userinfo.size();repo1++){
					 					
					 for(int repo2=0;repo2<userinfo2.size();repo2++){
						 //compute user similarity
						 //double usersimtemp=usersim(userinfo.get(repo1).getName(),userinfo2.get(repo2).getName());
						 
						 double usersimtemp=usersim(arrayuser1.get(repo1),arrayuser2.get(repo2));
						 
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
						 sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
						 Date date1 = sdf.parse(userinfo.get(repo1).getTime());
						 Date date2 = sdf.parse(userinfo2.get(repo2).getTime());
						 long diff = date1.getTime() - date2.getTime();
						 double hour = Math.abs((double)diff / (1000 * 60 * 60));
						// double hour = 1.0;
						 double usersim1=usersimtemp/hour;
						 //System.out.println(usersim1);
						 reposim+=usersim1;
						 count++;
						 if(usersim1!=0.0){
							 nonzerocount++;
						 }
					 }
					 
				 }
				 
				 starsim[i][j]= reposim / count;
				// starsim[j][i]= reposim / count;
				 starsim0[i][j]= reposim / nonzerocount;
				// starsim0[j][i]= reposim / nonzerocount;
				 
				br2.close(); 
				System.out.println(i+"-"+j);
				System.out.println(starsim[i][j]);
				System.out.println(starsim0[i][j]);
			 }
		     starsim[i][i]=0;
			 starsim0[i][i]=0;
			 br.close();
			 
		 }
		 
		 String outFile = "F:\\github\\data\\result\\starsim.txt";
		 FileOutputStream out = new FileOutputStream(outFile, true);

 	    
		 for(int m=0;m<starsim.length;m++){
			 StringBuffer   sb = new StringBuffer();
			   for(int n=0;n<starsim[m].length;n++){
			    sb.append(starsim[m][n]+"\t");
			   }
			  sb.append("\n");
	    	  out.write(sb.toString().getBytes("utf-8"));
		 }
		 out.close();
		 
		 String outFile3 = "F:\\github\\data\\result\\starsimnonzero.txt";
		 FileOutputStream out3 = new FileOutputStream(outFile3, true);

 	    
		 for(int m=0;m<starsim0.length;m++){
			 StringBuffer   sb = new StringBuffer();
			   for(int n=0;n<starsim0[m].length;n++){
			    sb.append(starsim0[m][n]+"\t");
			   }
			  sb.append("\n");
	    	  out3.write(sb.toString().getBytes("utf-8"));
		 }
		 out3.close();
		 
		 
		 
		 
		 
		 //归一化
		 String outFile2 = "F:\\github\\data\\result\\starsim01.txt";
		 FileOutputStream out2 = new FileOutputStream(outFile2, true);

		 double min=0;
		 double max=0;
		 for(int m=0;m<starsim.length;m++){
			   for(int n=0;n<starsim[m].length;n++){
			    if(starsim[m][n]>max)
			    	max=starsim[m][n];
			    else if(starsim[m][n]<min)
			    	min=starsim[m][n];
			   }
		 }
		 double starsim01[][] = new double[num][num];
		 for(int m=0;m<starsim.length;m++){
			 for(int n=0;n<starsim[m].length;n++){
				 starsim01[m][n] = (starsim[m][n]-min)/(max-min);  
			 }
		 }
		 
		 for(int m=0;m<starsim01.length;m++){
			 StringBuffer   sb = new StringBuffer();
			   for(int n=0;n<starsim01[m].length;n++){
			    sb.append(starsim01[m][n]+"\t");
			   }
			  sb.append("\n");
	    	  out2.write(sb.toString().getBytes("utf-8"));
		 }
		 out2.close();
		 
		 
		 
		 
		 
		//归一化
		 String outFile4 = "F:\\github\\data\\result\\starsim01nonzero.txt";
		 FileOutputStream out4 = new FileOutputStream(outFile4, true);

		 double min0=0;
		 double max0=0;
		 for(int m=0;m<starsim0.length;m++){
			   for(int n=0;n<starsim0[m].length;n++){
			    if(starsim0[m][n]>max0)
			    	max0=starsim0[m][n];
			    else if(starsim0[m][n]<min0)
			    	min0=starsim0[m][n];
			   }
		 }
		 double starsim010[][] = new double[num][num];
		 for(int m=0;m<starsim0.length;m++){
			 for(int n=0;n<starsim0[m].length;n++){
				 starsim010[m][n] = (starsim0[m][n]-min0)/(max0-min0);  
			 }
		 }
		 
		 for(int m=0;m<starsim010.length;m++){
			 StringBuffer   sb = new StringBuffer();
			   for(int n=0;n<starsim010[m].length;n++){
			    sb.append(starsim010[m][n]+"\t");
			   }
			  sb.append("\n");
	    	  out4.write(sb.toString().getBytes("utf-8"));
		 }
		 out4.close();
		 
		 
		 
	}

	
	
	
	
	
	public static double usersim(ArrayList arrayuser1,ArrayList arrayuser2) throws Exception{
//		ArrayList<String> arrayuser1=new ArrayList<String>();
//		for(int pagenum=1;;pagenum++){
//		String temp1=captureHtml("https://api.github.com/users/"+user1+"/starred?page="+pagenum+"&");
//		if(temp1.equals("[]"))
//			break;
//		else{
//		JSONArray jsonArray_temp1=JSONArray.fromObject(temp1);
//	    Object[] obj_temp1= jsonArray_temp1.toArray();
//	    for(int i=0;i<obj_temp1.length;i++){ 
//    	    String repo_id=((JSONObject) obj_temp1[i]).get("id").toString();
//    	    arrayuser1.add(repo_id);			    	   			    	    
//    	    }	    
//		}
//		}
//		
//		ArrayList<String> arrayuser2=new ArrayList<String>();
//		for(int pagenum2=1;;pagenum2++){
//		String temp2=captureHtml("https://api.github.com/users/"+user2+"/starred?page="+pagenum2+"&");
//		if(temp2.equals("[]"))
//			break;
//		else{
//		JSONArray jsonArray_temp2=JSONArray.fromObject(temp2);
//	    Object[] obj_temp2= jsonArray_temp2.toArray();
//	    for(int j=0;j<obj_temp2.length;j++){ 
//    	    String repo_id2=((JSONObject) obj_temp2[j]).get("id").toString();
//    	    arrayuser2.add(repo_id2);			    	   			    	    
//    	    }	    
//		}
//		}
		//compute sim of arrayuser1 and arrayuser2						
			int union=arrayuser1.size();
			
			for(int i=0; i<arrayuser2.size();i++){
				boolean flag=true;
				for(int j=0;j<arrayuser1.size();j++){
				  if(arrayuser2.get(i).equals(arrayuser1.get(j))){
					  flag=false;
					  break;
				  }
				}
				if(flag)
					union++;
			}
			
			int inter=0;
			
			for(int m=0; m<arrayuser2.size();m++){
				for(int n=0;n<arrayuser1.size();n++){
					if(arrayuser2.get(m).equals(arrayuser1.get(n))){
						inter++;
						break;
					}
				}
			}
			
			double sim=((double)inter)/((double)union);
			
		return sim;	
	}
	
	
	public static String captureHtml(String strURL) throws Exception {
		//String strURL = "https://api.github.com/repos/pylerSM/Xinstaller/stargazers?page=2";
				//"https://api.github.com/users/tupunco/starred?page=2";
				//"https://api.github.com/repos/StylingAndroid/StylingActionBar/stargazers?page=1&per_page=100";
		//strURL= strURL + "/stargazers";
		strURL=strURL+"access_token=b17aa0ba2a1bbb8f648f19d566dc0c17adfd508b";
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
