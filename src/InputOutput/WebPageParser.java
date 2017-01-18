package InputOutput;
import java.io.*;

import java.net.URL;
import java.net.URLConnection;

//import WPPRead.OfficeDetailExtract;



public class WebPageParser
{
	public static BufferedReader read(String url) throws Exception{

	
		//System.out.println("url being read");
		 URL UrlObject = new URL(url);
		 URLConnection con = UrlObject.openConnection();
		 con.setConnectTimeout(40000);
		 con.setReadTimeout(40000);
		 return new BufferedReader(new InputStreamReader(con.getInputStream()));

	

}

	
}

