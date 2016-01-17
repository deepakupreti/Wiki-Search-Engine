package deepak_mini;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Search 
{
	 public boolean isAlpha(String name) 
	 {
		    return name.matches("[a-zA-Z]+");
		}
	 File fileout = new File("output.txt");
	Stemmer stmr= new Stemmer();
	ArrayList<String> words = new ArrayList<String>(3);
	ArrayList<String> offset = new ArrayList<String>();

	void func() throws IOException
	{
		String curline="";
		BufferedReader br = null;
		br = new BufferedReader(new FileReader("secondary_index.txt"));
		while ((curline = br.readLine()) != null)
			{
				String temp [] ;
				temp=curline.split(":");
				words.add(temp[0]);
				offset.add(temp[1]);
				
			}
		
		/////preprocessing over
			Scanner in = new Scanner(System.in);	//user input
			Samplexml.query=in.nextLine();
		//	System.out.println(Samplexml.query);
			String temp [] ;
			temp=Samplexml.query.split(" ");
			//System.out.println(temp);
			int index;
			for(int i=0;i<temp.length;i++)
			{
			
				boolean status= Stop_words.check(temp[i]); //true if its a stop word so neglect it

				if(status==true)
				{
				
				continue;
				}
				else if(isAlpha(temp[i]))  // not a stop word
				{	
					stmr.add(temp[i].toCharArray(),temp[i].length());
					String tmp_stmr=stmr.stem(); // stemmed word
					index=Collections.binarySearch(words, temp[i]);//search in secondary index
					primary(index,temp[i]);
					//System.out.println(index);
				}
				else
				{
					continue;
				}
			}
	}
	
	void primary(int index,String word) throws IOException
	{
		int flagnoexist=0;
		String temp[];
		
		int pindex;
		
		if(index<0)
		{
			index=index*(-1);
			index=index-2;
			flagnoexist=1;
			if (index<0)
			{
				return;
			}
		}
	
		pindex=Integer.parseInt(offset.get(index));
		File file = new File("primary_index.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		raf.seek(pindex);
		if(flagnoexist==1)//means element not present in secondary index also check whether present in primary index
		{
			String temp1="";
			//  BufferedReader br =raf;
			 temp=raf.readLine().split(":");
				 for(int i = 0; i < 500; i++) 
				 {
					 temp1=raf.readLine();
					 
					 if(temp1==null)
					 {
						 return;
					 }
					 
					 temp=temp1.split(":");
					// System.out.println(temp[0]);
					 if(temp[0].equalsIgnoreCase(word))//word fopund in primary index
					 {
						// System.out.println(temp[0]);
						 raf.close();
						 raf = new RandomAccessFile(fileout, "r");
						 raf.seek(Integer.parseInt(temp[1]));
						 System.out.println("Read full line: " + raf.readLine());
					 break;
					 }
					  
					
				 }
	
		}
		else
		{	// System.out.println("Read full line: " + raf.readLine());
			 temp=raf.readLine().split(":");
			 raf.close();
			 
			 raf = new RandomAccessFile(fileout, "r");
			 raf.seek(Integer.parseInt(temp[1]));
			 System.out.println("Read full line: " + raf.readLine());
				
			 raf.close();
		}
	}

	
}
