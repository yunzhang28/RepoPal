import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class usersim {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 int num=1000;
			
		 String filePath0="X:\\yunzhang\\QsIDtemp.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<String> idlist0=new ArrayList<String>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist0.add(line);	
		 }
		 String filePath00="X:\\yunzhang\\QsID.txt";
		 BufferedReader br00 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath00)));		 
		 ArrayList<String> idlist=new ArrayList<String>();
		 for (String line = br00.readLine(); line != null; line = br00.readLine()) {
			 idlist.add(line);			  
		 }
		 br0.close();
		 br00.close();
		 
		 String outFile11 = "X:\\yunzhang\\result\\usersim51temp.txt";
		 FileOutputStream out11 = new FileOutputStream(outFile11, true);
		 
		 String outFile22 = "X:\\yunzhang\\result\\usersimnonzero51temp.txt";
		 FileOutputStream out22 = new FileOutputStream(outFile22, true);

		 
		 double starsim[][] = new double[num][num]; 
		 double starsim0[][] = new double[num][num];
		 for(int i=0;i<1;i++){
			 String filePath="X:\\yunzhang\\stargazers\\"+idlist0.get(i)+".txt";
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
			 String filePath22="X:\\yunzhang\\UserStarredList\\"+idlist0.get(i)+".txt";
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
			 

			 
		   for(int j=0;j<idlist.size();j++){
				 String filePath2="X:\\yunzhang\\stargazers\\"+idlist.get(j)+".txt";
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
			 
			 String filePath3="X:\\yunzhang\\UserStarredList\\"+idlist.get(j)+".txt";
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
			 

			 
			 //compute repo similarity
				 double reposim=0;
				 int count=0;
				 int nonzerocount=0;
				 for (int repo1=0;repo1<userinfo.size();repo1++){
					 					
					 for(int repo2=0;repo2<userinfo2.size();repo2++){
						 //compute user similarity
						 //double usersimtemp=usersim(userinfo.get(repo1).getName(),userinfo2.get(repo2).getName());
						 
						 double usersim1=usersim(arrayuser1.get(repo1),arrayuser2.get(repo2));

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
		     starsim[i][i+50]=0;
			 starsim0[i][i+50]=0;
			 br.close();
			 
			 StringBuffer   sb = new StringBuffer();
			 for(int m=0;m<starsim[i].length;m++){
				 sb.append(starsim[i][m]+"\t"); 
			 }
			 sb.append("\n");
	    	 out11.write(sb.toString().getBytes("utf-8"));
			 

			 StringBuffer   sb2 = new StringBuffer();
			 for(int m=0;m<starsim0[i].length;m++){
				 sb2.append(starsim0[i][m]+"\t"); 
			 }
			 sb2.append("\n");
	    	 out22.write(sb2.toString().getBytes("utf-8"));
	    	 
		 }
		 out11.close();
		 out22.close();
		 String outFile = "X:\\yunzhang\\result\\usersim51.txt";
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
		 
		 String outFile3 = "X:\\yunzhang\\result\\usersimnonzero51.txt";
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
		 
		 
		 
		 
		 

		 
		 
	}

	
	
	
	
	
	public static double usersim(ArrayList arrayuser1,ArrayList arrayuser2) throws Exception{

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
}	