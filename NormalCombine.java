import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NormalCombine {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String filePath0="F:\\github\\data\\result\\starsim-final.txt";
		 BufferedReader br0 = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(filePath0)));		 
		 ArrayList<double[]> idlist0=new ArrayList<double[]>();
		 for (String line = br0.readLine(); line != null; line = br0.readLine()) {
			 idlist0.add(line);			  
		 }
		 
		 

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
		 
		
    }	
	
	public static double[] normalization(double[] array){
		 double min=0;
		 double max=0;
		 for(int m=0;m<array.length;m++){
			 if(array[m]>max)
			    max=array[m];
			 else if(array[m]<min)
			    min=array[m];			 
		 }
		 double array01[] = new double[array.length];
		 for(int m=0;m<starsim.length;m++){
			 for(int n=0;n<starsim[m].length;n++){
				 starsim01[m][n] = (starsim[m][n]-min)/(max-min);  
			 }
		 }
		
		
		return array;
		
	}
	
	
	
}
