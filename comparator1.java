package deepak_mini;


import java.util.Comparator;

public class comparator1 implements Comparator<postmerge>{

	@Override
	 public int compare(postmerge s1, postmerge s2){

	    		if(s1.weight>s2.weight)
	                return -1;
	            else if(s1.weight<s2.weight)
	                return 1;
	            else
	            	return 0;
	 }
}
