import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ICPE.Site;
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
				String PlaceMark = "<Style id=\"My_Style\"><IconStyle><Icon><href>https://home.pjeremie.org/img/";
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
				PlaceMark = PlaceMark +"<styleUrl>#My_Style</styleUrl><Point><coordinates>";
				PlaceMark = PlaceMark+CurrentSite.getLongitude()+","+CurrentSite.getLatitude()+"</coordinates></Point>";
				PlaceMark = PlaceMark+"<description><![CDATA["+CurrentSite.toHTML()+"]]></description>"+"</Placemark>\n";
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KMLMaker("/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC-b.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP air.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP eau.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/testKML.kml");
		//Site TestInsta = new Site("0065.09490	Placo 93 (Bernouille -Delta)	PLACOPLATRE	En fonctionnement	A	NS - Non Seveso	COUBRON	93015	93470	Bernouille Delta	93074002			SEINE-SAINT-DENIS	77-5 - Carrières Nord	34 Avenue Franklin Roosevelt		92282	SURESNES	FRANCE			SAINT-GOBAIN	PLACO SAINT-GOBAIN	34 avenue Franklin Roosevelt	BP 6	92282	SURESNES	FRANCE			72980070600453	12	669199	6869576	Coordonnée précise	08.11Z	Extr. pierre ornement. & construct. etc.	Oui	Oui	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non IED - MTD	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Oui	Non	Non	Non	Non		Non	Oui	Oui	Oui	Non	Non	Non					Non						Non	Non	Non						Non		Non		Non","/home/pjere/JavaProjects/IICMapper/src/XLSFiles/Extraction S3IC.csv", "/home/pjere/JavaProjects/IICMapper/src/XLSFiles/GEREP air.csv");
		//System.out.println(TestInsta.toHTML());
	}

}
