


import java.io.FileWriter;
import java.io.PrintWriter;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String crawlStorageFolder = "D:/MS/Fall16/IR/Assg2/Assg2_Part2_LATimes/";
		 int numberOfCrawlers = 7;
		 CrawlConfig config = new CrawlConfig();
		 config.setCrawlStorageFolder(crawlStorageFolder);
		 config.setMaxDepthOfCrawling(20);
		 config.setMaxPagesToFetch(10000);
		 config.setIncludeBinaryContentInCrawling(true);
		 //config.setPolitenessDelay(500);
		 config.setUserAgentString("crawler4j (https://github.com/yasserg/crawler4j/)");
		 config.setIncludeBinaryContentInCrawling(true);
		 /*
		 * Instantiate the controller for this crawl.
		 */
		 PageFetcher pageFetcher = new PageFetcher(config);
		 RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		 RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		 CrawlController controller;
			try 
			{
				controller = new CrawlController(config, pageFetcher, robotstxtServer);
				//http://www.huffingtonpost.com/,http://www.latimes.com/
				controller.addSeed("http://www.huffingtonpost.com/");
				//controller.addSeed("http://www.latimes.com/");
				controller.start(MyCrawler.class, numberOfCrawlers);
			    PrintWriter writer = new PrintWriter("CrawlReport_Huffington.txt", "UTF-8");
				
				//PrintWriter writer = new PrintWriter("CrawlReport_LATimes.txt", "UTF-8");
				writer.println("Name: Varsha Chandan Bellara");
				writer.println("USC ID: 3875887109");
				//writer.println("News site crawled: http://www.latimes.com/");
				writer.println("News site crawled: http://www.huffingtonpost.com/");
				writer.println("Fetch Statistics \n"+"================ ");
				writer.println("# fetches attempted: "+MyCrawler.fetches_attempted);
				writer.println("# fetches succeeded: "+MyCrawler.fetches_suceeded);
				writer.println("# fetches aborted: "+MyCrawler.fetches_aborted);
				writer.println("# fetches failed: "+MyCrawler.fetches_failed);
				writer.println("Outgoing URLs: \n ==============");
				writer.println("Total URLs extracted: "+MyCrawler.total_URLs_extracted);
				writer.println("# unique URLs extracted: "+MyCrawler.unique_URLs_extracted);
				writer.println("# unique URLs within News Site: "+MyCrawler.unique_URLs_within_News_Site);
				writer.println("# unique URLs outside News Site: "+MyCrawler.unique_URLs_outside_News_Site);
				writer.println("Status Codes: \n =============");
				writer.println("200 OK: "+MyCrawler.status_200);
				writer.println("301 Moved Permanently: "+MyCrawler.status_301);
				writer.println("302 Found: "+MyCrawler.status_302);
				writer.println("401 Unauthorized: "+MyCrawler.status_401);
				writer.println("403 Forbidden: "+MyCrawler.status_403);
				writer.println("404 Not Found: "+MyCrawler.status_404);
				writer.println("503 Service Unavailable: "+MyCrawler.status_503);
				writer.println("File Sizes: \n ===========");
				writer.println("< 1KB: "+MyCrawler.file1KB);
				writer.println("1KB ~ <10KB: "+MyCrawler.file1to10KB);
				writer.println("10KB ~ <100KB: "+MyCrawler.file10to100KB);
				writer.println("100KB ~ <1MB: "+MyCrawler.file100to1MB);
				writer.println(">= 1MB: "+MyCrawler.file1MB);
				writer.println("Content Types: \n ==============");
				writer.println("text/html: "+MyCrawler.typehtml);
				writer.println("image/gif: "+MyCrawler.typegif);
				writer.println("image/jpeg: "+MyCrawler.typejpeg);
				writer.println("image/png: "+MyCrawler.typepng);
				writer.println("image/tif: "+MyCrawler.typetif);
				writer.println("application/pdf: "+MyCrawler.typepdf);
				writer.close();
				
				
				
				
				
				
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
