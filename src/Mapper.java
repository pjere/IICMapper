import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import ICPE.Site;
import InputOutput.CSVReader;
import InputOutput.MyFileWriter;


public class Mapper {

	/**
	 * @param args
	 */
	public static void KMLMaker(String S3ICbFileName, String S3ICFileName, String GEREPAirFileName, String GEREPEauFileName, String KMLName)
	{
		
		BufferedReader rdr ;

		MyFileWriter.writeToFile(KMLName,"<?xml version=\"1.0\" encoding=\"UTF-8\"?><kml xmlns=\"http://earth.google.com/kml/2.0\"> <Document>\n");
		try
		{
			rdr = new BufferedReader(new FileReader(S3ICbFileName)) ;
			while (rdr.ready()==true) 
			{
				Site CurrentSite = new Site( rdr.readLine() , S3ICFileName, GEREPAirFileName, GEREPEauFileName);
				System.out.println("Current Site : "+CurrentSite.getS3ICNum());
				String PlaceMark = "<Style id=\"My_Style\"><IconStyle><Icon><href>https://pjeremie.org/img/";
				switch(CurrentSite.getRegimeEtablissement())
				{
				case AUTORISATION: PlaceMark = PlaceMark+"factoryyellow";
					break;
				case DECLARATION: PlaceMark = PlaceMark+"factoryblue";
					break;
				case ENREGISTREMENT:PlaceMark = PlaceMark+"factorygreen";
					break;
				case NONCLASSE: PlaceMark = PlaceMark+"factoryblue";
					break;
				case SEVESOBAS: PlaceMark = PlaceMark+"factoryorange";
					break;
				case SEVESOHAUT: PlaceMark = PlaceMark+"factoryred";
					break;
				default:
					break;
				}
				PlaceMark = PlaceMark+".png</href> </Icon></IconStyle></Style>";
				//PlaceMark = PlaceMark + "<Placemark><name>"+CurrentSite.getS3ICNum()+"</name>";
				PlaceMark = PlaceMark +"<Placemark><styleUrl>#My_Style</styleUrl><Point><coordinates>";
				PlaceMark = PlaceMark+CurrentSite.getLongitude()+","+CurrentSite.getLatitude()+"</coordinates></Point>";
				PlaceMark = PlaceMark+"<description><![CDATA["+CurrentSite.toHTML()+"]]></description>"+"</Placemark>\n";
				PlaceMark.replace('\u0013', ' ');
				MyFileWriter.writeToFile(KMLName,PlaceMark);
				
				
			}//while
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
		MyFileWriter.writeToFile(KMLName,"</Document></kml>");
	}
	
	
	
	public static void EmissionsKMLMaker( String GEREPAirFileName, String KMLName)	
	{
		//in the following section we generate two hashmaps of the emissions parameters and max value and min value regionally for those parameters.
		HashMap<String,Double> ParametersMaxAir = new HashMap<String,Double>();
		HashMap<String,Double> ParametersMinAir = new HashMap<String,Double>();
		BufferedReader rdr ;
		try
		{
			rdr = new BufferedReader(new FileReader(GEREPAirFileName)) ;
			Vector<String> CurrentLine = new Vector<String>();
			String str ="";
			while (rdr.ready()==true) 
			{
				str = rdr.readLine();
				//System.out.println(str);
				CurrentLine = CSVReader.ParseCSVLine(str);
				try{
				if(ParametersMaxAir.containsKey(CurrentLine.get(9)))
				{
					System.out.println("Found min and max for parameter "+CurrentLine.get(9)+" @ "+CurrentLine.get(13));
					ParametersMaxAir.put(CurrentLine.get(9), Math.max(Double.parseDouble(CurrentLine.get(13)),ParametersMaxAir.get(CurrentLine.get(9))));
					ParametersMinAir.put(CurrentLine.get(9), Math.min(Double.parseDouble(CurrentLine.get(13)),ParametersMinAir.get(CurrentLine.get(9))));
				}
				else
				{
					ParametersMaxAir.put(CurrentLine.get(9), Double.parseDouble(CurrentLine.get(13)));
					ParametersMinAir.put(CurrentLine.get(9), Double.parseDouble(CurrentLine.get(13)));
				}
				}
				catch(NumberFormatException a){}
			}//while
		}//try
		catch (NullPointerException a)
		{
			a.printStackTrace();
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
		

	for(String Parameter : ParametersMaxAir.keySet())
	{
		//initializes all the KML files for every parameter.
		MyFileWriter.writeToFile("/home/pjere/JavaProjects/IICMapper/src/KMLFiles/"+Parameter+".kml","<?xml version=\"1.0\" encoding=\"UTF-8\"?><kml xmlns=\"http://earth.google.com/kml/2.0\"> <Document>\n");
		System.out.println("Initializing KML file for "+Parameter);
	}	
		
	try {
		rdr = new BufferedReader(new FileReader(KMLName));
		Vector<String> CurrentLine = new Vector<String>();
	String KMLInstallationPoint ="";
	while (rdr.ready()==true) 
	{
		KMLInstallationPoint = rdr.readLine();
		BufferedReader rdrGEREP = new BufferedReader(new FileReader(GEREPAirFileName)) ;
		while(rdrGEREP.ready()==true)
		{
			String rdrGEREPline = rdrGEREP.readLine();
			CurrentLine = CSVReader.ParseCSVLine(rdrGEREPline);
				if(KMLInstallationPoint.contains(CurrentLine.get(1)))
				{
					double min = ParametersMinAir.get(CurrentLine.get(9));
					System.out.println("Current Parameter min : "+min);
					double max = ParametersMaxAir.get(CurrentLine.get(9));
					System.out.println("Current Parameter max : "+max);
					System.out.println("Current Parameter value : "+CurrentLine.get(13));
					double ratio = (Double.parseDouble(CurrentLine.get(13))-min)/(max-min);

					System.out.println("Current Parameter ratio : "+ratio);
					int filenameint = (int) (ratio*20);
					filenameint = filenameint*5;
					System.out.println("Adding placemark for "+CurrentLine.get(1)+" and parameter "+CurrentLine.get(9)+" @ "+filenameint);
					String placemarkname =  "https://pjeremie.org/img/"+filenameint+".png";
					MyFileWriter.writeToFile("/home/pjere/JavaProjects/IICMapper/src/KMLFiles/"+CurrentLine.get(9)+".kml", KMLInstallationPoint.replaceFirst("https://pjeremie.org/img/(.*)png", placemarkname));
				}
			
		}
		rdrGEREP.close();
	}
	rdr.close();
	
	for(String Parameter : ParametersMaxAir.keySet())
	{
		//ends all the KML files for every parameter.
		MyFileWriter.writeToFile("/home/pjere/JavaProjects/IICMapper/src/KMLFiles/"+Parameter+".kml","\n\n</Document></kml>\n\n");
	}
	
	
	} 
	
	
	catch (FileNotFoundException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (IOException a) 
	{
		System.out.println("Problème d'IO");
	}
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//KMLMaker("/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC-b.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP air.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP eau.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/testKML.kml");
		EmissionsKMLMaker("/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP air.csv","/home/pjere/JavaProjects/IICMapper/src/XLSFiles/testKML.kml");
		//Site TestInsta = new Site("0065.09490	Placo 93 (Bernouille -Delta)	PLACOPLATRE	En fonctionnement	A	NS - Non Seveso	COUBRON	93015	93470	Bernouille Delta	93074002			SEINE-SAINT-DENIS	77-5 - Carrières Nord	34 Avenue Franklin Roosevelt		92282	SURESNES	FRANCE			SAINT-GOBAIN	PLACO SAINT-GOBAIN	34 avenue Franklin Roosevelt	BP 6	92282	SURESNES	FRANCE			72980070600453	12	669199	6869576	Coordonnée précise	08.11Z	Extr. pierre ornement. & construct. etc.	Oui	Oui	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non IED - MTD	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Oui	Non	Non	Non	Non		Non	Oui	Oui	Oui	Non	Non	Non					Non						Non	Non	Non						Non		Non		Non","/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP air.csv");
		//System.out.println(TestInsta.toHTML());
	}

}
