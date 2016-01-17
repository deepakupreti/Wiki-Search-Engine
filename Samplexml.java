package deepak_mini;


//C:\Program Files\Java\jdk1.8.0_05\bin
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.io.*;
import java.util.Set;
import java.util.Scanner;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class Samplexml extends DefaultHandler {
	

	public static StringBuilder strname=new StringBuilder();

	public static ArrayList<Integer> f_stat = new ArrayList<Integer>();//names of temp files
	public static ArrayList<BufferedReader> br_list = new ArrayList <BufferedReader>(); //list for bufferreader
	long ext=0;
	long ref=0;
	public static String stop_word[]= {"coord","gr","tr","td","nbsp","http","https","www","a","about","above","across","after","again","against","all","almost","alone","along","already","also","although","always","among","an","and","another","any","anybody","anyone","anything","anywhere","are","area","areas","around","as","ask","asked","asking","asks","at","away","b","back","backed","backing","backs","be","became","because","become","becomes","been","before","began","behind","being","beings","best","better","between","big","both","but","by","c","came","can","cannot","case","cases","certain","certainly","clear","clearly","come","could","d","did","differ","different","differently","do","does","done","down","down","downed","downing","downs","during","e","each","early","either","end","ended","ending","ends","enough","even","evenly","ever","every","everybody","everyone","everything","everywhere","f","face","faces","fact","facts","far","felt","few","find","finds","first","for","four","from","full","fully","further","furthered","furthering","furthers","g","gave","general","generally","get","gets","give","given","gives","go","going","good","goods","got","great","greater","greatest","group","grouped","grouping","groups","h","had","has","have","having","he","her","here","herself","high","high","high","higher","highest","him","himself","his","how","however","i","if","important","in","interest","interested","interesting","interests","into","is","it","its","itself","j","just","k","keep","keeps","kind","knew","know","known","knows","l","large","largely","last","later","latest","least","less","let","lets","like","likely","long","longer","longest","m","made","make","making","man","many","may","me","member","members","men","might","more","most","mostly","mr","mrs","much","must","my","myself","n","necessary","need","needed","needing","needs","never","new","new","newer","newest","next","no","nobody","non","noone","not","nothing","now","nowhere","number","numbers","o","of","off","often","old","older","oldest","on","once","one","only","open","opened","opening","opens","or","order","ordered","ordering","orders","other","others","our","out","over","p","part","parted","parting","parts","per","perhaps","place","places","point","pointed","pointing","points","possible","present","presented","presenting","presents","problem","problems","put","puts","q","quite","r","rather","really","right","right","room","rooms","s","said","same","saw","say","says","second","seconds","see","seem","seemed","seeming","seems","sees","several","shall","she","should","show","showed","showing","shows","side","sides","since","small","smaller","smallest","so","some","somebody","someone","something","somewhere","state","states","still","still","such","sure","t","take","taken","than","that","the","their","them","then","there","therefore","these","they","thing","things","think","thinks","this","those","though","thought","thoughts","three","through","thus","to","today","together","too","took","toward","turn","turned","turning","turns","two","u","under","until","up","upon","us","use","used","uses","v","very","w","want","wanted","wanting","wants","was","way","ways","we","well","wells","went","were","what","when","where","whether","which","while","who","whole","whose","why","will","with","within","without","work","worked","working","works","would","x","y","year","years","yet","you","young","younger","youngest","your","yours","z"};
	//
	public static int no_temp_files = 0;
	public static int pageCount = 0; //will create new file after every 100pages are read
	static Remove_words rmv =new Remove_words();
	static Stop_words swrd= new Stop_words();
	static Stemmer stmr= new Stemmer();
	static StringBuilder sb = new StringBuilder();
	static StringBuilder infobox = new StringBuilder();
	static StringBuilder externallink = new StringBuilder();
	static StringBuilder reference = new StringBuilder();	
	static BufferedWriter out = null;
	StringBuilder temp=new StringBuilder();
	StringBuilder ptitle=new StringBuilder(); // temp to push value in category of page
	long flag_curley=0;	//{}
	long flag_d_equals=0;	// ==
	long flag_braces=0;	//[]
  long cid=0;
  long refcount=0;
  long extcount=0;

	boolean btext;
	boolean bpage;
	boolean btitle;
	boolean bid;
	//boolean breference;
	//boolean externallink;
	//boolean infobox;
	
	Page pg;
	public static String query="";
	
	public void startDocument(){
		
	
			
	}
	
	public void endDocument(){
	
	Merge mrg =new Merge();
	try {
		mrg.print();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public void startElement(String nameSpaceURI,String localName,String qName,Attributes atts)
	{

		
		if(qName.equalsIgnoreCase("page"))
		{
			bpage=true;	
			pg=new Page();
			cid=0;
		}

		if(qName.equalsIgnoreCase("title"))
		{
			btitle=true;
			
		}

	
		if(qName.equalsIgnoreCase("id"))
		{
			cid++;
			
			if(cid==1)
			{
				bid=true;
			}

		}
		
		if(qName.equalsIgnoreCase("text"))
		{
			btext=true;	
		}
						
}
	public void endElement(String nameSpaceURI,String localName,String qName){
		
		if(qName.equalsIgnoreCase("text"))		
		{
			btext=false;
		}
			
		if(qName.equalsIgnoreCase("page"))		
		{
			bpage=false;
			//System.out.println("page no "+pg.id);
		
			pg.text1(sb.toString());
			//System.out.println(pg.infobox);
			rmv.getdata(pg); //sent all the info related the obj of page class to count class
			
			sb.setLength(0); 
			cid=0;
			flag_curley=0;	//{}
			flag_d_equals=0;	// ==
			flag_braces=0;	//[]
			
		  
		}
		
		else if(qName.equalsIgnoreCase("id"))		
		{
			bid=false;
			
			
		}


		else if(qName.equalsIgnoreCase("title"))		
		{
			btitle=false;
			ptitle.setLength(0);
		}
}
	
	public void characters(char[] ch,int start,int length)
	{
				
		if(btitle)
		{
			  for(int i=start;i<(start+length);i++)
			  {
				  ptitle.append(Character.toLowerCase(ch[i]));
			  }
			  
			  pg.title1(ptitle.toString());
			
		}
		
		else if(bid && cid==1)	//outside revision
		{
			String tmp="";  
			for(int i=start;i<(start+length);i++)
			  {
				tmp+=Character.toLowerCase(ch[i]);
			  }
			
			pg.id=Integer.parseInt(tmp);
			bid=false;
			cid++;
		
		}
		
		else  if(btext)
		{	int infoflag=0;
			int insideinfo=0;
			for(int i=start;i<(start+length);i++)
			{
				int flag_ext=0;
	
				if(Character.isLetter(ch[i]) && flag_curley==1)// if inside {} braces
				{
					//continue;
				}
				else if( ch[i]!='{' && ch[i]!='}' && flag_curley>=2 && ch[i]=='I' && ch[i+1]=='n' && ch[i+2]=='f' && ch[i+6]=='f')//check if infobox is in {{}}
				{
					insideinfo=1;
				}
				else if( ch[i]!='{' && ch[i]!='}' && flag_curley>=2 && insideinfo==1)// infobox present
				{
					if(Character.isLetter(ch[i]) && infoflag==1)
					{
						infobox.append(Character.toLowerCase((ch[i])));

					}
					
					else if(ch[i]!='|' && !Character.isLetter(ch[i]) && infoflag==1)
					{
						infobox.append(" ");

					}
					else if(ch[i]=='|')
					{
						infoflag=0;
					}
					else if(ch[i]=='=')
					{
						infoflag=1;
					}
					//else if(Character.isLetter(ch[i]) && infoflag!=0)
				}
				
				else if(ch[i]=='}' && flag_curley<0)
					break;
				
				else if(ch[i]==']' && flag_braces<0)
					break;
				
				else if(ch[i]=='{')
				{
					flag_curley++;
				}
				else if(ch[i]=='}')
				{
					flag_curley--;
					if(flag_curley==0 && insideinfo==1)//means inbfobox present
					{
						//will check for infobox
						String t2 = infobox.toString();
						//pg.infobox(t2);
						pg.infobox("hi");
						infobox.setLength(0);
						infoflag=0;
						insideinfo=0;
					}
				}
				
				
				else if(ch[i]=='=' && ch[i+1]=='=' && flag_d_equals==0 && flag_curley==0)// means if not inside {} and staringg of ==
				{
					flag_d_equals=1;
				}
				else if(ch[i]=='=' && ch[i+1]=='=' && flag_d_equals==1 && flag_curley==0)//means if not inside {} and ending of ==
				{
					flag_d_equals=0;
				}
				
				
				else if( ch[i]=='['  && flag_curley==0 && 	 flag_d_equals==0) //image tag
				{
					 flag_braces++;
					
				} 
					else if( ch[i]!='[' && ch[i]!=']' && flag_braces>0  && flag_curley==0 && 	 flag_d_equals==0)//data inside [] braces
				{
					
					if(temp.equals(""))//first character after [ so space inserted
					{
						temp.append(" ");
					}
					
					if(Character.isLetter(ch[i])  || ch[i]==' ' || ch[i]==':')
					{
						temp.append(Character.toLowerCase((ch[i])));
					}
					
					else if(!Character.isLetter(ch[i]) )//
					{
					temp.append(" ");	
					}

					
				}
				
				else if( ch[i]==']'  && flag_curley==0 && 	 flag_d_equals==0) //braces closing tag
				{
					 flag_braces--;
					 
					 if(flag_braces==0)      //end of braces tag
					 {
						 temp.append(" ");          //apend " " at last of temp
						String t1 = temp.toString();
						if(t1.contains("www"))	//if 
						{
							
								t1=t1.replaceAll(":"," ");
								sb.append(t1.toLowerCase());
						}
						
						else if( t1.contains("category"))
						{
							t1=t1.replaceAll("category"," ");
							t1=t1.replaceAll(":"," ");
							pg.category1(t1.toLowerCase());
									
						}
						
						else if(!t1.contains(":"))                //if ":" is not in [] then append in sb
						 {
				
							sb.append(t1.toLowerCase());
						 }

						temp.setLength(0);
						 
					 }
					
				}
				
				else if( flag_curley==0 && 	 flag_d_equals==0 && flag_braces==0)// character or space occur outside of {} braces 
				{
					if(Character.isLetter(ch[i]) || ch[i]==' ' )
					{
						sb.append(Character.toLowerCase(ch[i]));

					}

					else if(ch[i]=='\n')// && ch[i+1]!='\n')
					{
						sb.append(' ');	
					}
					
					else //&& Character.isLetter(ch[i+1]))
					{
						sb.append(' ');	
					}
				}
			}
		}	
}
	

	

}




/*
try{	
	Iterator<Count> itr=(swrd.main_hash.get("A")).iterator();

	 System.out.println(swrd.main_hash.get("A").size());

while(itr.hasNext())
{
	//System.out.println("inside ");
	   System.out.print(itr.next());//.b);//+","+itr.next().c+","+itr.next().b+"   ");
}
/*		 Set<String> keys = swrd.main_hash.keySet();
  for(String key: keys)
  {
      System.out.println(key+" "+swrd.main_hash.get(key));
  }

}
catch(Exception e)
{
System.out.println("error");
}*/