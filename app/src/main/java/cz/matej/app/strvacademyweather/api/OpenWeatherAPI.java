package cz.matej.app.strvacademyweather.api;

import java.util.Map;

import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


public interface OpenWeatherAPI
{

	@GET(ApiConfig.CURRENT_WEATHER)
	Call<CurrentWeatherEntity> getCurrentWeather(@QueryMap Map<String, String> map);

}
