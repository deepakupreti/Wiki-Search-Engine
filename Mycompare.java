package deepak_mini;


import java.util.Comparator;

public class Mycompare implements Comparator<Count>
{
	
	public int compare(Count a, Count b) {
		// TODO Auto-generated method stub
		if (a.t>= b.t) 
		{
			return 1;
		} 
		 else 
		 {
			return -1;
		 }
	}

}