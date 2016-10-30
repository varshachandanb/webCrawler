
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;
import java.util.UUID;

import java.io.File;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler 
{
	
	 String crawlStorageFolder = "D:/MS/Fall16/IR/Assg2/Assg2_Part2_LATimes/";
	 private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|" 
			   + "|rm|smil|wmv|swf|wma|zip|rar|gz|xml|css))$"); 
	
	 
	 public static int fetches_attempted=0; //fetches
	 public static int fetches_suceeded=0; //visit 
	 public static int fetches_aborted=0;
	 public static int fetches_failed=0;
	 public static int total_URLs_extracted=0;//no of outgoing urls including repeats
	 public static int unique_URLs_extracted=0; // hashset 
	 public static int unique_URLs_within_News_Site=0; // ok 
	 public static int unique_URLs_outside_News_Site=0; // n_ok
	 public static int status_200=0;
	 public static int status_301=0;
	 public static int status_302=0;
	 public static int status_401=0;
	 public static int status_403=0;
	 public static int status_404=0;
	 public static int status_503=0;
	 public static int file1KB=0;
	 public static int file1to10KB=0;
	 public static int file10to100KB=0;
	 public static int file100to1MB=0;
	 public static int file1MB=0;
	 public static int typehtml=0;
	 public static int typejpeg=0;
	 public static int typepng=0;
	 public static int typepdf=0;
	 public static int typetif=0;
	 public static int typegif=0;
	 public static int typedoc=0;
	 HashSet<String> uniqueUrl= new HashSet<String>();
	 HashSet<String> uniqueUrlwithinNewSite= new HashSet<String>();
	 HashSet<String> uniqueUrloutsideNewSite= new HashSet<String>();
	 
	 
	 @Override
	 public boolean shouldVisit(Page referringPage, WebURL url) 
	 {
		 String href = url.getURL().toLowerCase();
		 
		  return !FILTERS.matcher(href).matches()
			&&href.startsWith("http://www.huffingtonpost.com/");
		  //&&href.startsWith("http://www.latimes.com/");
		 
		  
	 }
	 @Override
	 public void handlePageStatusCode(WebURL url, int statusCode , String Description)
	 {
		 String link=url.toString();
		 link=link.replaceAll(",","");
		 // Status Code *******
		  if(statusCode==200)
			  status_200+=1;
		  if(statusCode==301)
			  status_301+=1;
		  if(statusCode==302)
			  status_302+=1;
		  if(statusCode==401)
			  status_401+=1;
		  if(statusCode==403)
			  status_403+=1;
		  if(statusCode==404)
			  status_404+=1;
		  if(statusCode==503)
			  status_503+=1;
		  if(statusCode>=400 && statusCode <599)
			  fetches_failed+=1;
		  
		  if(statusCode>=300 && statusCode <400)
			  fetches_aborted+=1;
		  
		 try
		 {
		  fetches_attempted+=1;
		  FileWriter fw1 = null;
		  fw1 = new FileWriter(new File("fetch_NewsSite1.csv"), true);
		  StringBuilder builder1 = new StringBuilder();
		  String rowData1 =link+","+statusCode;
		  builder1.append(rowData1 +"\n");
		  fw1.write(builder1.toString());
		  fw1.close();
		 }
		 catch (IOException e)
		 {
				e.printStackTrace();
		 }
	 }
			
	 @Override
	  public void visit(Page page) 
	  {
		  String url = page.getWebURL().getURL();
		  int statusCode = page.getStatusCode();
		  url=url.replaceAll(",","");
		  //System.out.println("URL: " + url);
		  String hashedName = UUID.randomUUID().toString();
		  String filename = hashedName; 
		  String sameDomain="";
		  System.out.println("ContentType: "+page.getContentType()); 	
		  try 
		     { 
			  if(statusCode==200)
			  {
				  
				  fetches_suceeded+=1;
				  FileWriter fw2 = null;
				  fw2 = new FileWriter(new File("visit_NewsSite1.csv"), true);
				  StringBuilder builder2 = new StringBuilder();
				  String rowData2 =url.toString()+","+page.getContentData().length+","+page.getParseData().getOutgoingUrls().size()+","+page.getContentType().split(";")[0];
				  builder2.append(rowData2 +"\n");
				  fw2.write(builder2.toString());
				  fw2.close();
			  
			 
			   //File Size *******
			  if(page.getContentData().length<1024)
				  file1KB+=1;
			  if(page.getContentData().length>=1024 &&page.getContentData().length <10240)
				  file1to10KB+=1;
			  if(page.getContentData().length>=10240 &&page.getContentData().length <102400)
				  file10to100KB+=1;
			  if(page.getContentData().length>=102400 &&page.getContentData().length <1024000)
				  file100to1MB+=1;
			  if(page.getContentData().length>=1024000)
				  file1MB+=1;
			  
			  	  
				// Content Type ********	  
			  if(page.getContentType().contains("pdf"))
				  typepdf+=1;
			  if(page.getContentType().contains("png"))
				  typepng+=1;
		      if(page.getContentType().contains("jpeg"))
		    	  typejpeg+=1;
		      if(page.getContentType().contains("gif"))
		    	  typegif+=1;
		      if(page.getContentType().contains("tif"))
		    	  typetif+=1;
		      if(page.getContentType().contains("doc"))
		    	  typedoc+=1;
			  if(page.getContentType().contains("text/html"))
				  typehtml+=1;
			  }
			
		     }
			 catch (IOException e)
			 {
					e.printStackTrace();
			 }
		  if (page.getParseData() instanceof HtmlParseData) 
		  {
			  
			  HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			  String text = htmlParseData.getText();
			  String html = htmlParseData.getHtml();
			  Set<WebURL> links = htmlParseData.getOutgoingUrls();
			  total_URLs_extracted+=links.size();
			  
			  for (WebURL w: links)
			  {
				  String link= w.toString();
				  uniqueUrl.add(link);
				  unique_URLs_extracted=uniqueUrl.size();
				  if(link.startsWith("http://www.huffingtonpost.com/"))
				  //if(link.startsWith("http://www.latimes.com/"))	  
					  {
						  uniqueUrlwithinNewSite.add(link);
						  unique_URLs_within_News_Site=uniqueUrlwithinNewSite.size();
					  }
				  else 
				  	  {		 
				  		uniqueUrloutsideNewSite.add(link);
				  		unique_URLs_outside_News_Site=uniqueUrloutsideNewSite.size();
				  	  }
			  }
		  }
	}
}
