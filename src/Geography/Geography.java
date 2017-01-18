package Geography;

import java.util.Locale;
import java.util.Vector;



public class Geography {
	
	protected static double EarthRadius = 6367.445;

	public static double toRadians(double DegreeAngle)
	{
		return DegreeAngle*2*Math.PI/360;
		
		
	}
	String[] CountryCodes = Locale.getISOCountries(); 
	
	
	public static double getDistanceBetween(GeoCode o, GeoCode p)
	{
		double Lat_o = toRadians(o.getNumLatitude() );
		double Long_o = toRadians(o.getNumLatitude()) ;
		double Lat_p = toRadians(o.getNumLatitude() );
		double Long_p = toRadians(o.getNumLatitude() );
		
		return EarthRadius*Math.acos(Math.sin(Lat_o)*Math.sin(Lat_p)+Math.cos(Lat_o)*Math.cos(Lat_p)*Math.cos(Long_o-Long_p));
	}
		
		
		
		
	/** Built using the Wikipedia.org longest common substring pseudocode.
	 * @param a
	 * @param b
	 * @return
	 */
	public static String getLongestCommonWord(String S1, String S2)
	{
	    int Start = 0;
	    int Max = 0;
	    for (int i = 0; i < S1.length(); i++)
	    {
	        for (int j = 0; j < S2.length(); j++)
	        {
	            int x = 0;
	            while (S1.charAt(i + x) == S2.charAt(j + x))
	            {
	                x++;
	                if (((i + x) >= S1.length()) || ((j + x) >= S2.length())) break;
	            }
	            if (x > Max)
	            {
	                Max = x;
	                Start = i;
	            }
	         }
	    }
	    return S1.substring(Start, (Start + Max));
	}
		
	
	
	public static String cleanString(String a)
	{
		String a1 = a;
		a1=a1.replace('-', ' ');
		a1=a1.replaceAll("[éèêë]", "e");
		a1=a1.replaceAll("&", " and ");
		a1=a1.replaceAll("[ìîïí]", "i");
		a1=a1.replaceAll("[ûùüú]", "u");
		a1=a1.replaceAll("[öôòøóõ]", "o");
		a1=a1.replaceAll("[àâäå]", "a");
		a1=a1.replaceAll("[àâäå]", "a");
		a1=a1.replaceAll("[ç]", "c");
		a1=a1.replaceAll(" ++", " ");
		a1=a1.toUpperCase();
		return a1;
	}
	
	
	public static double getProximity(String a,String b)
	{
		String a1 = cleanString(a);
		String b1 = cleanString(b);
		String c = getLongestCommonWord(a1,b1);
		double Proximity = 0;
		while(c.length()>2)
		{
			a1=a1.replace(c, "");
			b1=b1.replace(c, "");
			c = getLongestCommonWord(a1,b1);
		}
		double alength = (double) a.length();
		double blength = (double) b.length();
		double a1length = (double) a1.length();
		double b1length = (double) b1.length();
		Proximity = Math.max(1-a1length/alength,1-b1length/blength);
		return Proximity;
	}
	
	
	
	
	}
	

	
	
	
	
	
