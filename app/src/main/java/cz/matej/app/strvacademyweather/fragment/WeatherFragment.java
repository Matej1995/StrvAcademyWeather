package cz.matej.app.strvacademyweather.fragment;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import cz.matej.app.strvacademyweather.R;
import cz.matej.app.strvacademyweather.api.RequestFactory;
import cz.matej.app.strvacademyweather.api.listener.RequestListener;
import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity;


public class WeatherFragment extends BaseFragment implements RequestListener<CurrentWeatherEntity>
{

	public static final String TAG = WeatherFragment.class.toString();

	public static WeatherFragment getInstance(String location)
	{
		WeatherFragment fragment = new WeatherFragment();

		Bundle bundle = new Bundle();
		bundle.putString("location", location);

		fragment.setArguments(bundle);

		return fragment;
	}



	private TextView Humidity;
	private TextView Temp;
	private TextView Pressure;
	private TextView TempMax;
	private TextView TempMin;
	private ImageView mIconImageView;

	@Override
	public void initComponents()
	{
		this.mIconImageView = (ImageView) getRootView().findViewById(R.id.mIconImageView);
		this.Humidity = (TextView) getRootView().findViewById(R.id.Humidity);
		this.Temp = (TextView) getRootView().findViewById(R.id.Temp);
		this.Pressure = (TextView) getRootView().findViewById(R.id.Pressure);
		this.TempMax = (TextView) getRootView().findViewById(R.id.TempMax);
		this.TempMin = (TextView) getRootView().findViewById(R.id.TempMin);

	}


	@Override
	public void onFragmentReady()
	{
		parseBundle();
	}


	@Override
	public void onResponse(CurrentWeatherEntity entity)
	{
		setWeatherIcon("10d");
		Humidity.setText(String.valueOf(entity.getMain().getHumidity()));
		Temp.setText(String.valueOf(entity.getMain().getTemp()));
		Pressure.setText(String.valueOf(entity.getMain().getPressure()));
		TempMax.setText(String.valueOf(entity.getMain().getTempMax()));
		TempMin.setText(String.valueOf(entity.getMain().getTempMin()));

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
	public static String getIconUrl(String iconID)
	{
		return "http://api.openweathermap.org/img/w/" +iconID+ ".png";
	}

	public void setWeatherIcon(String iconID)
	{
		Glide.with(this).load(getIconUrl(iconID)).into(this.mIconImageView);

	}

}
