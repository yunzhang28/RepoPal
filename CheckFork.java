import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CheckFork {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 String outFile = "F:\\github\\data\\forkQs.txt";
		 FileOutputStream out = new FileOutputStream(outFile, true);
		 
		 String filePath0="F:\\github\\data\\QsID.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<String> idlist=new ArrayList<String>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist.add(line);			  
		 }
		 
		 String filePath="F:\\github\\data\\1000fork.txt";
		 BufferedReader br = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath)));
		 for (String line = br.readLine(); line != null; line = br.readLine()) {
			  String[] qs=line.split("\t");
			  StringBuffer   sb = new StringBuffer();
			  System.out.println(qs.length);
			  for(int i=1;i<qs.length;i++){
				  for(int j=0;j<idlist.size();j++){
					 if(qs[i].equals(idlist.get(j))){
						
				    	    sb.append(qs[0]+"\t"+idlist.get(j)+"\n"); 
					 }
						 
				  }
			  }
			  out.write(sb.toString().getBytes("utf-8"));
			  
		 }

		 out.close();
			        	
	}

}
