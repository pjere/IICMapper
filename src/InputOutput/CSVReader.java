package InputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class CSVReader {
	public static void ReadFile (String path) 
	{
		BufferedReader rdr ;

		try
		{
			rdr = new BufferedReader(new FileReader(path)) ;
			while (rdr.ready()==true) 
			{
				System.out.println( rdr.readLine() ); 
				
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
}
	
	public static Vector<String> ParseCSVLine(String CSVLine)
	{
		Vector<String> Result = new Vector<String>();
		if(!CSVLine.contains("\t"))
		{

			Result.add(0,CSVLine);
			return Result;
		}
		int Count = 0;
		while(CSVLine.contains("\t"))
		{		
		int j=CSVLine.indexOf("\t");
		Result.add(Count, CSVLine.substring(0, j));
		//System.out.println(CSVLine.substring(0, j));
		//System.out.println(CSVLine);
		CSVLine=CSVLine.substring(j+1, CSVLine.length());
		Count++;
		}
		Result.add(Count,CSVLine);
		return Result;
	}
	
}