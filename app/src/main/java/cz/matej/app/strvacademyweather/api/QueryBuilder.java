package cz.matej.app.strvacademyweather.api;

import java.util.LinkedHashMap;
import java.util.Map;

import cz.matej.app.strvacademyweather.entity.UnitsFormat;


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


	public QueryBuilder addUnitsFormat(UnitsFormat unitsFormat)
	{
		String units;
		switch(unitsFormat)
		{
			case METRIC:
				units = "metric";
				break;
			default:
				units = "imperial";
				break;
		}

		mQueryMap.put("units", units);
		return this;
	}


	public Map<String, String> getQueryMap()
	{
		return mQueryMap;
	}

}
