package deepak_mini;


import java.util.Comparator;


public class comparator implements Comparator<Tuple>{
    public int compare(Tuple s1, Tuple s2){
    	//if(Merge.cmpflag==0)
    		//{
    		int compare = s1.word.toString().compareToIgnoreCase(s2.word.toString());
    		if(compare > 0)
                return 1;
            else
                return -1;
    	//	}
    //	else //if(Merge.cmpflag==1)
    //	{
    //		if(s1.weight>=s2.weight)
    	//	{
    	//		return 1;
    	//	}
    	//	else
    		//	return -1;
    	//}
 

    }
}