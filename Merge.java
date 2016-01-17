package deepak_mini;



import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;


public class Merge 
{
	
	FileWriter f_file;
	FileWriter primaryindex;
	FileWriter secondaryindex;
	int file_no=0;
	public static int cmpflag=0;
	
	void print() throws IOException
	{
		
		ArrayList<postmerge> arrayList = new ArrayList<postmerge>();
		comparator mine_c = new comparator();
	
		PriorityQueue<Tuple> pq_data = new PriorityQueue<Tuple>(mine_c);
		
		long linecount=0;
    	long offset=0;
    	long offset_secondary=0;
    	String gplist="";
    	String gwrd="";
    	String gline="";
    	int gfno=-1; 
		String temp_line="";
		f_file = new FileWriter("output.txt",true);//all output
		primaryindex = new FileWriter("primary_index.txt",true);//all output
		secondaryindex = new FileWriter("secondary_index.txt",true);//all output
	
		for(int i=0;i<Samplexml.no_temp_files;i++)
		{
			BufferedReader br=null; //bufferreader
			br = new BufferedReader(new FileReader(i+".txt"));
    		Samplexml.br_list.add(br);
			temp_line=br.readLine();
			String[] temp_str=null;
			String[] temp = temp_line.split(" ");
			Tuple tmp=new Tuple();
			tmp.initline(temp_line);
			tmp.initwo(temp[0]);
			tmp.initpo(temp[1].toString());  //inserted after removing braces
			tmp.initfno(i);
		    pq_data.add(tmp);

		}

		int flag=-1;
		while(pq_data.size() != 0)
		{
			flag++;
			if(flag==0)//will enter ponly on frst iteration
			{
				int tmp_fno; // the number of file that is in current obj
				String tmpwrd="";
				String tmplist="";
				String next_l="";//will contain the nextline of the element popped
				
				Tuple tmp1=new Tuple();
				tmp1=pq_data.remove(); //contains the poped eobject
				
				
				tmpwrd=tmp1.word; //initialissing global variales
				tmplist=tmp1.postlist;//.trim();
				gplist=tmplist;
		    	gwrd=tmpwrd;
		    	gline=tmp1.line;
		    	gfno=tmp1.fno; 
				   if((next_l=Samplexml.br_list.get(tmp1.fno).readLine()) == null)// if the file is fiunished
				   {
					   Samplexml.br_list.get(tmp1.fno).close();
				   }
				   
				 else{  // if file is not finished
					 	String[] temp_str=null;
						String[] temp = next_l.split(" ");
					
						Tuple tmp=new Tuple();
							tmp.initline(next_l);
						tmp.initwo(temp[0]);
						tmp.initfno(tmp1.fno);
						tmp.initpo(temp[1].toString());
						
						pq_data.add(tmp);
				 		}
			}  
			else  //after frst time
			{
				int tmp_fno; // the number of file that is in current obj
				String tmpwrd="";
				String tmplist="";
				String next_l="";//will contain the nextline of the element popped
				
				Tuple tmp1=new Tuple();
				tmp1=pq_data.remove(); //contains the poped eobject
				if(tmp1.word.equals(gwrd)) //if poped object has same name as already popped one
				{
					//String del="";
					gplist=gplist+"|"+tmp1.postlist;
					//gplist=gplist.replace("||", "|");
					
				
					if((next_l=Samplexml.br_list.get(tmp1.fno).readLine()) == null)// if the file is fiunished
					   {
						   Samplexml.br_list.get(tmp1.fno).close();
					   }
					else
					{
						String[] temp_str=null;
						String[] temp = next_l.split(" ");
					
						Tuple tmp=new Tuple();
						tmp.initline(next_l);
						tmp.initwo(temp[0]);
						tmp.initfno(tmp1.fno);
						tmp.initpo(temp[1].toString());
						
						pq_data.add(tmp);
					}
				}
				else  //if the popped element is different from prev
				{
					gplist=gplist.replace("||","|");
					gplist=gplist.replace("|","@");
					gplist=gplist.trim();
					
					postmerge post;
	
						String posttemp[];
						posttemp=gplist.split("@");
						for(int i=0;i<posttemp.length;i++)
						{
							post=new postmerge();
								String posttemp2[]=posttemp[i].split(",")	;
							int len=	posttemp2.length;
									int no=Integer.parseInt(posttemp2[len-1]);
							post.name_init(posttemp[i]);
							post.weight(no);
							
							arrayList.add(post);
						}
					Collections.sort(arrayList,new comparator1());
						StringBuilder delete=new StringBuilder();
						delete.setLength(0);
						for (postmerge number : arrayList) {
						 String main=number.name.toString();
						 String delete22[];
						 delete22=number.name.toString().split(",");
						 int len22=delete22.length;
						 String hell=",";
						 hell=hell+delete22[len22-1];
						 main=main.replace(hell, "");
					      delete.append(main);
					      delete.append("|");
					    } 
					f_file.write(delete.toString());
					
					arrayList.clear();
					f_file.write(System.getProperty( "line.separator" ));
					f_file.flush();
					primaryindex.write(gwrd+":"+offset);
					primaryindex.write(System.getProperty( "line.separator" ));
					primaryindex.flush();
					linecount++;//means this much lines has been written in the primary index file
					String del="";
					if(linecount%500==1)
					{
						secondaryindex.write(gwrd+":"+offset_secondary);//starting address of 500th line of primaryindex file
						secondaryindex.write(System.getProperty( "line.separator" ));
						secondaryindex.flush();
						 del=gwrd+":"+offset_secondary;
					}
				
					offset_secondary+=del.length()+System.getProperty( "line.separator" ).length();
					offset+=delete.toString().length()+System.getProperty( "line.separator" ).length();
					delete.setLength(0);
					gplist=tmp1.postlist;
			    	gwrd=tmp1.word;
			    	gline=tmp1.line;
			    	gfno=tmp1.fno;
			    	if((next_l=Samplexml.br_list.get(tmp1.fno).readLine()) == null)// if the file is fiunished
					   {
						   Samplexml.br_list.get(tmp1.fno).close();
					   }
					else
					{
						String[] temp_str=null;
						String[] temp = next_l.split(" ");
						Tuple tmp=new Tuple();
						tmp.initline(next_l);
						tmp.initwo(temp[0]);
						tmp.initfno(tmp1.fno);
						tmp.initpo(temp[1].toString());
						pq_data.add(tmp);
					}
			    	
				}
			}
		}
		 f_file.close();
		 primaryindex.close();
		 secondaryindex.close();
	}
}
