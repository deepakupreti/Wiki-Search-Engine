package deepak_mini;

public class Tuple
{

 String postlist ="";
 String word ="";
String line="";
 int fno;
 //
StringBuilder name =new StringBuilder();
	long weight;
	
	void name_init(String temp)
	{
		name.append(temp);
	}
	
	void weight(long wght)
	{
		
		weight=wght;
	}
 void initpo(String temp)
 {
	 postlist=temp;
 }
 
 void initwo(String temp)
 {
	 word=temp;
 }
 
 
 void initfno(int temp)
 {
	fno=temp;
 }
 void initline(String temp)
 {
	line=temp;
 }
}
