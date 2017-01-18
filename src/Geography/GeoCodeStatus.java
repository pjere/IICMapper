package Geography;

public enum GeoCodeStatus {
CRUDE_ADDRESS,
GEOCODE_OK,
GEOCODE_ZERO_RESULTS,
GEOCODE_OVER_QUERY_LIMIT,
GEOCODE_REQUEST_DENIED,
GEOCODE_INVALID_REQUEST;


public String toString()
{
	switch (this)
	{
	case CRUDE_ADDRESS : return "Crude Address";
	case GEOCODE_OK : return "OK";
	case GEOCODE_ZERO_RESULTS : return "Zero Results";
	case GEOCODE_OVER_QUERY_LIMIT : return "Over Query Limit";
	case GEOCODE_REQUEST_DENIED : return "Request Denied";
	case GEOCODE_INVALID_REQUEST : return "Invalid Request";
	}
	return "";
}


}