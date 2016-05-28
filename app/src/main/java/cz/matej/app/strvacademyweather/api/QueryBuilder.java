package cz.matej.app.strvacademyweather.api;

import java.util.LinkedHashMap;
import java.util.Map;


public class QueryBuilder
{
	private final Map<String, String> mQueryMap;


	public QueryBuilder()
	{
		this.mQueryMap = new LinkedHashMap<>();
	}


	public QueryBuilder addAppId(String appID)
	{
		mQueryMap.put("appid", appID);
		return this;
	}


	public QueryBuilder addLocation(String location)
	{
		mQueryMap.put("q", location);
		return this;
	}


	public Map<String, String> getQueryMap()
	{
		return mQueryMap;
	}

}
