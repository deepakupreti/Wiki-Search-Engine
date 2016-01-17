package deepak_mini;




import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Stop_words 
{
	Search src=new Search();
	static TreeMap <String, TreeSet< Count>> main_hash = new TreeMap<String, TreeSet<Count>>();
	static  LinkedHashSet<String> hstop_words = new LinkedHashSet<String>();
	

	void main_func() //add stop words to hashset
	{

		for(int i=0;i<435;i++)
		{
			hstop_words.add(Samplexml.stop_word[i]);// put stop_words in hashset
			//System.out.println(Samplexml.stop_word[i]);
		}
	}
	
	public static boolean check(String temp) //check whether the word is a stop word
	{
		if(hstop_words.contains(temp))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}

