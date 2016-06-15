package cz.matej.app.strvacademyweather.api;

import java.util.Map;

import cz.matej.app.strvacademyweather.WeatherAppConfig;
import cz.matej.app.strvacademyweather.api.listener.RequestListener;
import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity;
import cz.matej.app.strvacademyweather.entity.UnitsFormat;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestFactory implements ApiConfig
{
	private static RequestFactory sInstance;
	private OpenWeatherAPI mOpenWeatherAPI;


	public static RequestFactory getInstance()
	{
		if(sInstance == null) sInstance = new RequestFactory();
		return sInstance;
	}


	private RequestFactory()
	{
		init();
	}


	private void init()
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(getHttpClient())
				.build();
		mOpenWeatherAPI = retrofit.create(OpenWeatherAPI.class);
	}


	private OkHttpClient getHttpClient()
	{
		final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

		if(WeatherAppConfig.DEVELOPMENT)
			clientBuilder.addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BODY));

		return clientBuilder.build();
	}


	public void getWeatherIcon()
	{

	}


	public void getCurrentWeather(String location, final RequestListener<CurrentWeatherEntity> requestListener)
	{
		mOpenWeatherAPI.getCurrentWeather(getCurrentWeatherQueryMap(location)).enqueue(new Callback<CurrentWeatherEntity>()
		{
			@Override
			public void onResponse(Call<CurrentWeatherEntity> call, Response<CurrentWeatherEntity> response)
			{
				if(response.code() == HTTP_STATUS_OK)
				{
					requestListener.onResponse(response.body());
				}
				else
				{
					requestListener.onResponse(null);
				}
			}


			@Override
			public void onFailure(Call<CurrentWeatherEntity> call, Throwable t)
			{
				requestListener.onResponse(null);
			}
		});
	}


	private Map<String, String> getCurrentWeatherQueryMap(String location)
	{
		return new QueryBuilder().addAppId(APP_ID).addLocation(location).addUnitsFormat(UnitsFormat.METRIC).getQueryMap();
	}

}
