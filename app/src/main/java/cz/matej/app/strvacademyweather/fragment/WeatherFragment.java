package cz.matej.app.strvacademyweather.fragment;

import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cz.matej.app.strvacademyweather.R;
import cz.matej.app.strvacademyweather.api.RequestFactory;
import cz.matej.app.strvacademyweather.api.listener.RequestListener;
import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity;
import cz.matej.app.strvacademyweather.entity.UnitsFormat;
import cz.matej.app.strvacademyweather.utils.UnitsUtility;


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


	private TextView mHeaderTextView;
	private TextView mMinimumTempTextView;
	private TextView mMaximumTempTextView;
	private TextView mCurrentTempTextView;

	private ImageView mCurrentWeatherStateIcon;


	@Override
	public void initComponents()
	{
		mHeaderTextView = (TextView) getRootView().findViewById(R.id.fragment_weather_header_text);
		mMinimumTempTextView = (TextView) getRootView().findViewById(R.id.fragment_weather_min_temp);
		mMaximumTempTextView = (TextView) getRootView().findViewById(R.id.fragment_weather_max_temp);
		mCurrentTempTextView = (TextView) getRootView().findViewById(R.id.fragment_weather_current_temp);

		mCurrentWeatherStateIcon = (ImageView) getRootView().findViewById(R.id.fragment_weather_icon);
	}


	@Override
	public void onFragmentReady()
	{
		parseBundle();
	}


	@Override
	public void onResponse(CurrentWeatherEntity entity)
	{
		if(entity != null)
		{
			mHeaderTextView.setText(entity.getName());
			mCurrentTempTextView.setText("Current: ".concat(String.valueOf(entity.getMain().getTemp()).concat(UnitsUtility.getUnitForSystem(UnitsFormat.METRIC))));
			mMinimumTempTextView.setText("Minimum: ".concat(String.valueOf(entity.getMain().getTempMin()).concat(UnitsUtility.getUnitForSystem(UnitsFormat.METRIC))));
			mMaximumTempTextView.setText("Maximum: ".concat(String.valueOf(entity.getMain().getTempMax()).concat(UnitsUtility.getUnitForSystem(UnitsFormat.METRIC))));

			setWeatherIcon(entity.getWeatherItem().getIcon());
		}
		else
		{
			Log.v(TAG + ": onResponse (failure)", "Empty body received");
		}
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
		return "http://api.openweathermap.org/img/w/" + iconID + ".png";
	}


	public void setWeatherIcon(String iconID)
	{
		Glide.with(this).load(getIconUrl(iconID)).into(this.mCurrentWeatherStateIcon);
	}

}
