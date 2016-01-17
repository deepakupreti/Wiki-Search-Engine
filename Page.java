package deepak_mini;




public class Page {
	

	int id;
	//String title="";
	StringBuilder title = new StringBuilder();
	StringBuilder text = new StringBuilder();
	StringBuilder category = new StringBuilder();
	StringBuilder infobox = new StringBuilder();
	StringBuilder externallink = new StringBuilder();
	StringBuilder reference = new StringBuilder();
	
	void text1 (String s2)
	{
		text.append(s2);
		//System.out.println(s1);
	}
	
	void category1 (String s1)
	{
		category.append(s1);
		//System.out.println("cate "+category);
		//System.out.println(s1);
	}
	
	void title1 (String s1)
	{
		title.append(s1);
		//System.out.println("cate "+title);
		//System.out.println(s1);
	}
	
	void infobox (String s2)
	{
		infobox.append(s2);
		//System.out.println(s1);
	}
	void externallink (String s2)
	{
		externallink.append(s2);
		//System.out.println(s1);
	}
	
	void reference(String s2)
	{
		reference.append(s2);
		//System.out.println(s1);
	}

}
