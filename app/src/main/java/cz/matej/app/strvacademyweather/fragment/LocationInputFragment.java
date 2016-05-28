package cz.matej.app.strvacademyweather.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import cz.matej.app.strvacademyweather.R;


public class LocationInputFragment extends BaseFragment
{

	public static LocationInputFragment getInstance()
	{
		return new LocationInputFragment();
	}


	private EditText mLocationInput;


	@Override
	public void initComponents()
	{
		super.initComponents();
		mLocationInput = (EditText) getRootView().findViewById(R.id.fragment_location_input_edit_text);
		getRootView().findViewById(R.id.fragment_location_input_button_find_weather).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				findWeather();
			}
		});
	}


	@Override
	public int getLayoutID()
	{
		return R.layout.fragment_location_input;
	}


	public void findWeather()
	{
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.main_container, WeatherFragment.getInstance(getLocationViaInput()))
				.commit();
	}


	@NonNull
	private String getLocationViaInput()
	{
		return mLocationInput.getText().toString();
	}

}
