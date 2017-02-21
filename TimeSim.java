import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class TimeSim {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		int num=1000;
		
		 String filePath0="X:\\yunzhang\\QsID1.txt";
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
		 double starsim[][] = new double[num][num]; 
		 double starsim0[][] = new double[num][num];
		 for(int i=0;i<idlist0.size();i++){
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
				 

			 
			 //compute repo time similarity
				 double reposim=0;
				 int count=0;
				 for (int repo1=0;repo1<userinfo.size();repo1++){
					 					
					 for(int repo2=0;repo2<userinfo2.size();repo2++){
						 //compute user similarity
						 //double usersimtemp=usersim(userinfo.get(repo1).getName(),userinfo2.get(repo2).getName());
						 
						if(userinfo.get(repo1).getId().equals(userinfo2.get(repo2).getId())){
							
							 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
							 sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
							 Date date1 = sdf.parse(userinfo.get(repo1).getTime());
							 Date date2 = sdf.parse(userinfo2.get(repo2).getTime());
							 long diff = date1.getTime() - date2.getTime();
							 double hour = Math.abs((double)diff / (1000 * 60 * 60));
							 int ye = (int) Math.ceil(hour);
							 double timesim1=1/(double)ye;
							
							 reposim+=timesim1;
							 count++;
						}else{
							continue;
						}
						
						 
					 }
					 
				 }
				 
				 starsim[i][j]= reposim / count;
				// starsim[j][i]= reposim / count;

				 if(reposim==0.0)
					 starsim[i][j]=0.0; 
				 
				br2.close(); 
				System.out.println(i+"-"+j);
				System.out.println(starsim[i][j]);
				System.out.println(count);
			 }
		     starsim[i][i]=0;

			 br.close();
			 
		 }
		 
		 String outFile = "X:\\yunzhang\\result\\timesim.txt";
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
		 

		 
		 
		
	 
}
	 
	
	
	
}
