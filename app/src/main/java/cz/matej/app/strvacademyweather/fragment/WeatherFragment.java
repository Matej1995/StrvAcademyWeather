package cz.matej.app.strvacademyweather.fragment;

import android.os.Bundle;
import android.widget.TextView;

import cz.matej.app.strvacademyweather.R;
import cz.matej.app.strvacademyweather.api.RequestFactory;
import cz.matej.app.strvacademyweather.api.listener.RequestListener;
import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity;


public class WeatherFragment extends BaseFragment implements RequestListener<CurrentWeatherEntity>
{
	public static WeatherFragment getInstance(String location)
	{
		WeatherFragment fragment = new WeatherFragment();

		Bundle bundle = new Bundle();
		bundle.putString("location", location);

		fragment.setArguments(bundle);

		return fragment;
	}


	private TextView sample;


	@Override
	public void initComponents()
	{
		this.sample = (TextView) getRootView().findViewById(R.id.sample);
	}


	@Override
	public void onFragmentReady()
	{
		parseBundle();
	}


	@Override
	public void onResponse(CurrentWeatherEntity entity)
	{
		sample.setText(entity.getWeather().get(0).getDescription());
	}


	private void parseBundle()
	{
		if(getArguments() != null)
		{
			RequestFactory.getInstance().getCurrentWeather(getArguments().getString("location"), this);
		}
	}


	@Override
	public int getLayoutID()
	{
		return R.layout.fragment_weather;
	}
}
