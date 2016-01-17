package deepak_mini;




import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Driver {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException  {
		long startTime = System.currentTimeMillis();
		Stop_words obj2= new Stop_words();
		obj2.main_func();//calling  methd
		
		File file = new File(args[0]);
		InputStream inputStream= new FileInputStream(file);
		Reader reader = new InputStreamReader(inputStream,"UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		Samplexml handler = new Samplexml();
		saxParser.parse(is, handler);
		
		//XMLReader p=XMLReaderFactory.createXMLReader();
		//p.setContentHandler(new Samplexml());
		//p.parse("sample.xml");
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("total time= "+totalTime);
	}
}