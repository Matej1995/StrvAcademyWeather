package cz.matej.app.strvacademyweather.utils;

import cz.matej.app.strvacademyweather.entity.UnitsFormat;


public class UnitsUtility
{


	public static String getUnitForSystem(UnitsFormat format)
	{
		switch(format)
		{
			case IMPERIAL:
				return "F";
			default:
				return "C";
		}
	}

}
