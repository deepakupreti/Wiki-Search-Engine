package deepak_mini;



import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

public class Remove_words 
{
	//boolean status;
	
	static int sizehmap=0;
	 HashMap<String,Count> hashmap;
	 Stop_words swrd= new Stop_words();
	 Stemmer stmr= new Stemmer();
	//static Page pg= new Page();
	
	 public boolean isAlpha(String name) 
	 {
		    return name.matches("[a-zA-Z]+");
		}
	void getdata(Page smpl)//came here after a page is read
	{
		Samplexml.pageCount++;
		 hashmap=new HashMap<String,Count>();// alloted memory each time a new page starts
		 sizehmap=0;
			StringBuilder tempt=new StringBuilder();
			int i;
			for( i=0;i<smpl.title.length();i++)
			{	
				
				if(Character.isLetter(smpl.title.charAt(i)))
				{
					tempt.append(smpl.title.charAt(i));
				
				}
				

				if(!Character.isLetter(smpl.title.charAt(i)))
				{
					
					//tempt=tempt.trim();
					if(tempt.length()>0)
						{
						extra(tempt.toString(),'t',smpl.id);
						}
				tempt.setLength(0);
				}
				
			}
			if(i==smpl.title.length())
			{
				//tempt=tempt.trim();
				if(tempt.length()>0)
				{
					extra(tempt.toString(),'t',smpl.id);
					tempt.setLength(0);
				}
	
			}
		
		//title end
			
		tempt.setLength(0);
			//category start
			
		for( i=0;i<smpl.category.length();i++)
		{	
			
			if(Character.isLetter(smpl.category.charAt(i)))
			{
				tempt.append(smpl.category.charAt(i));
				//System.out.println(tempt);
			
			}
			

			if(!Character.isLetter(smpl.category.charAt(i)))
			{
				
				if(tempt.length()>0)
					{
					extra(tempt.toString(),'c',smpl.id);
					}
			tempt.setLength(0);
			}
			
		}
		if(i==smpl.category.length())
		{
			//tempt=tempt.trim();
			if(tempt.length()>0)
			{
				extra(tempt.toString(),'c',smpl.id);
				tempt.setLength(0);
			}

		}
			tempt.setLength(0);
			//category end
			
			
			
			//text strt

			
			for( i=0;i<smpl.text.length();i++)
			{	
				
				if(Character.isLetter(smpl.text.charAt(i)))
				{
					tempt.append(smpl.text.charAt(i));
					//System.out.println(tempt);
				
				}
				

				if(!Character.isLetter(smpl.text.charAt(i)))
				{
					//System.out.println("h ");
					//tempt=tempt.trim();
					if(tempt.length()>0)
						{
						extra(tempt.toString(),'b',smpl.id);
						
						}
				tempt.setLength(0);
				}
				
			}
			if(i==smpl.text.length())
			{
				//tempt=tempt.trim();
				if(tempt.length()>0)
				{
					extra(tempt.toString(),'b',smpl.id);
					tempt.setLength(0);
				}

			}
			
			//text end
			
			
			//infobox start
			for( i=0;i<smpl.infobox.length();i++)
			{	
				
				if(Character.isLetter(smpl.infobox.charAt(i)))
				{
					tempt.append(smpl.infobox.charAt(i));
					//System.out.println(tempt);
				
				}
				

				if(!Character.isLetter(smpl.infobox.charAt(i)))
				{
					//System.out.println("h ");
					//tempt=tempt.trim();
					if(tempt.length()>0)
						{
						extra(tempt.toString(),'f',smpl.id);
						}
				tempt.setLength(0);
				}
				
			}
			if(i==smpl.infobox.length())
			{
				//tempt=tempt.trim();
				if(tempt.length()>0)
				{
					extra(tempt.toString(),'f',smpl.id);
					tempt.setLength(0);
				}

			}
				tempt.setLength(0);
				
				//infobox end

				//externallink start
				for( i=0;i<smpl.externallink.length();i++)
				{	
					
					if(Character.isLetter(smpl.externallink.charAt(i)))
					{
						tempt.append(smpl.externallink.charAt(i));
						//System.out.println(tempt);
					
					}
					

					if(!Character.isLetter(smpl.externallink.charAt(i)))
					{
						//System.out.println("h ");
						//tempt=tempt.trim();
						if(tempt.length()>0)
							{
							extra(tempt.toString(),'e',smpl.id);
							}
					tempt.setLength(0);
					}
					
				}
				if(i==smpl.externallink.length())
				{
					//tempt=tempt.trim();
					if(tempt.length()>0)
					{
						extra(tempt.toString(),'e',smpl.id);
						tempt.setLength(0);
					}

				}
					tempt.setLength(0);
					
					//externallink end
					
					
					//reference start
					for( i=0;i<smpl.reference.length();i++)
					{	
						
						if(Character.isLetter(smpl.reference.charAt(i)))
						{
							tempt.append(smpl.reference.charAt(i));
							//System.out.println(tempt);
						
						}
						

						if(!Character.isLetter(smpl.reference.charAt(i)))
						{
							//System.out.println("h ");
							//tempt=tempt.trim();
							if(tempt.length()>0)
								{
								extra(tempt.toString(),'r',smpl.id);
								}
						tempt.setLength(0);
						}
						
					}
					if(i==smpl.reference.length())
					{
						//tempt=tempt.trim();
						if(tempt.length()>0)
						{
							extra(tempt.toString(),'r',smpl.id);
							tempt.setLength(0);
						}

					}
						tempt.setLength(0);
						
						//references end
					
				
			sizehmap=hashmap.size();
			
				map(sizehmap);
				
				if(Samplexml.pageCount==2000)
				{
					//System.out.println("cc"+Samplexml.pageCount);
					Samplexml.pageCount=0;
					file_write();
					 swrd.main_hash.clear();
				

				}
	}
	
	void extra(String temp1,char s,int id) //for inserting in hashmap
	{
		
		boolean status= swrd.check(temp1); //true if its a stop word so neglect it

		String temp="";
		
		if(status==true)
		{
		
		}
		else if(isAlpha(temp1))  // not a stop word
		{	
			
			//System.out.println(temp1);
			//System.out.println("sti");
			//title strt
			stmr.add(temp1.toCharArray(),temp1.length());
			String tmp_stmr=stmr.stem(); // stemmed word
		
			if((hashmap.containsKey(tmp_stmr)))  //present in hashmap
			{
				
				if(s=='t')
				{
					//System.out.println(tmp_stmr);
					
					(hashmap.get(tmp_stmr)).t++;  // incrementing value of filed
					//System.out.println((hashmap.get(tmp_stmr)).t++);
				}
				else if(s=='c')
				{
					(hashmap.get(tmp_stmr)).c++;  // incrementing value of filed

				}
				else if(s=='b')
				{
					(hashmap.get(tmp_stmr)).b++;  // incrementing value of filed

				}
				else if(s=='f')
				{
					(hashmap.get(tmp_stmr)).f++;  // incrementing value of filed

				}
				else if(s=='r')
				{
					(hashmap.get(tmp_stmr)).r++;  // incrementing value of filed

				}
				else if(s=='e')
				{
					(hashmap.get(tmp_stmr)).e++;  // incrementing value of filed

				}
				//swrd.main_hash.get(tmp_stmr);
			}
			else // not in hashmap
			{
			 	Count cnt = new Count();	
			 	cnt.t=0;//title
				cnt.i=id;//id
				cnt.c=0;//category
				cnt.b=0;
				cnt.f=0;
				cnt.e=0;
				cnt.r=0;
				//cnt.id=id;
				if(s=='t')
				{
								
					cnt.t=1;  //incremeneting field
	
				}
				else if(s=='c')
				{
					cnt.c=1;  //incremeneting field
				}
				else if(s=='b')
				{
					cnt.b=1;  //incremeneting field
				}
				else if(s=='e')
				{
					cnt.e=1;  //incremeneting field
				}
				else if(s=='f')
				{
					cnt.f=1;  //incremeneting field
				}
				else if(s=='r')
				{
					cnt.r=1;  //incremeneting field
				}
				
				
				hashmap.put(tmp_stmr, cnt);  //pushed the entry in hashmap			
			}
				
		}
	
	}

void map(int length)//no of key in hashmap
{
	
	Iterator iter = (Iterator) hashmap.keySet().iterator();
	for (String key : hashmap.keySet())
	{
	   if(swrd.main_hash.containsKey(key))   // if key is in treemap
	   {
		   swrd.main_hash.get(key).add(hashmap.get(key));   //object corrosponding to key of hashmap is added  
	   }
	   else// if key is not present
	   {
		   TreeSet <Count> set = new TreeSet<Count>(new Mycompare());
		   set.add(hashmap.get(key));
		   swrd.main_hash.put(key, set);
	   }
	}

}

void file_write()
{
	try{	
		FileWriter fstream = new FileWriter(Samplexml.no_temp_files+".txt",true);
		Samplexml.no_temp_files++;
		Set<String> keys = swrd.main_hash.keySet();
	        for(String key: keys)
	        { 
	         	 fstream.write(key+" ");
	
	  
	            Iterator<Count> itr=(swrd.main_hash.get(key)).iterator();   //objects corrosponmding to the upper string
	            while(itr.hasNext())
	            {
	            	derefer(itr.next(),fstream);   
	            }	
	            fstream.write(System.getProperty( "line.separator" ));
	        }
	       
	        try
            {
                  fstream.close(); 
            }
            catch(IOException ex)
            {
                  System.out.println("File Closing Error");
           }
 

	
	}
	catch(Exception e)
	{
		System.out.println("error");
	}
}

void derefer(Count obj,FileWriter fstream)
{
	int weight=0;
StringBuilder temp =new StringBuilder ();

temp.append(obj.i);//id
//temp.append(":");
if(obj.t!=0)
{weight=weight +100*obj.t;
	temp.append(",t"+obj.t);
	
}
if(obj.c!=0)
{ weight=weight +50*obj.c;
	temp.append(",c"+obj.c);
	
}
if(obj.b!=0)
{weight=weight +12*obj.b;
	temp.append(",b"+obj.b);
	
}
if(obj.f!=0)
{weight=weight +25*obj.f;
	temp.append(",f"+obj.f);
	
}
if(obj.r!=0)
{weight=weight +5*obj.r;
	temp.append(",r"+obj.r);
	
}
if(obj.e!=0)
{weight=weight +2*obj.e;
	temp.append(",e"+obj.e);
	
}
temp.append(","+weight);
temp.append("|");
try{


fstream.write(temp.toString());
fstream.flush();
//fstream.newLine();
    
}
catch(Exception e)
{
	System.out.println("file error");
}
temp.setLength(0);
}
}
		
