package Geography;

import java.io.BufferedReader;

import InputOutput.WebPageParser;


public class GeoCode {
	private String formattedaddress;
	private String latitude;
	private String longitude;
	private GeoCodeStatus Status;
	protected String GoogleRequestString="http://maps.googleapis.com/maps/api/geocode/json?address=";
	public GeoCode(){}
	
	public GeoCode(String a, String lat, String lon, GeoCodeStatus Stat)
	{
		formattedaddress =a;
		latitude = lat;
		longitude = lon;
		Status = Stat;
	}
	
	
	
	public GeoCode(String CrudeAddress)
	{
		String GeoCodeURL = GoogleRequestString+CrudeAddress.replaceAll(" ", "+").replaceAll(",","")+"&sensor=false";
		System.out.println("GeoCodeURL :"+GeoCodeURL);
		latitude = "NOT_ATTR";
		longitude = "NOT_ATTR";
		formattedaddress = "NOT_ATTR";
		Status = GeoCodeStatus.GEOCODE_REQUEST_DENIED;
		boolean flag = true;//permet de savoir si on a déjà trouvé une latitude et une longitude.
		try {
			System.out.println("Reading : "+GeoCodeURL);
			BufferedReader reader = WebPageParser.read(GeoCodeURL);
			String line = reader.readLine();
 	
			while(line!=null)
				{
				//System.out.println(line);
				if(line.contains("\"lat\" : ")&&(flag==true))
					{
					System.out.println("Found Latitude");
					this.latitude = line.replaceAll("\"lat\" : ", "").replaceAll(",","").trim();
					line = reader.readLine();
					this.longitude = line.replaceAll("\"lng\" : ", "").replaceAll(",","").trim();
					flag=false;
					}
				if(line.contains("\"formatted_address\" : \""))
					{
					this.formattedaddress = line.replaceAll("\"formatted_address\" : \"", "").replaceAll("\",",""); 
					}
				if(line.contains("status"))
					{					
					if(line.contains("OK"))
						{
							this.Status=GeoCodeStatus.GEOCODE_OK;
						}
						else if (line.contains("ZERO_RESULTS"))
						{
							this.Status=GeoCodeStatus.GEOCODE_ZERO_RESULTS;
							System.out.println("Zero Results : "+CrudeAddress);
						}
						else if (line.contains("OVER_QUERY_LIMIT"))
						{
							this.Status=GeoCodeStatus.GEOCODE_OVER_QUERY_LIMIT;
							System.out.println("Over Query Limit : "+CrudeAddress);
						}
						else if (line.contains("REQUEST_DENIED"))
						{
							this.Status=GeoCodeStatus.GEOCODE_REQUEST_DENIED;
							System.out.println("Request Denied : "+CrudeAddress);
						}
						else if (line.contains("INVALID_REQUEST"))
						{
							this.Status=GeoCodeStatus.GEOCODE_INVALID_REQUEST;
							System.out.println("Invalid Request : "+CrudeAddress);
						}
					}
				line = reader.readLine();
				}
				
	
			}
	 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		
	}
}

	public String getFormattedAddress(){return this.formattedaddress;}
	public String getLatitude(){return this.latitude;}
	public String getLongitude(){return this.longitude;}
	public GeoCodeStatus getStatus(){return this.Status;}
	public double getNumLatitude(){return Double.parseDouble(getLatitude());}
	public double getNumLongitude(){return Double.parseDouble(getLongitude());}
}