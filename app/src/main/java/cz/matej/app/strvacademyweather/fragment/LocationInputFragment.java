package cz.matej.app.strvacademyweather.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import cz.matej.app.strvacademyweather.R;


public class LocationInputFragment extends BaseFragment
{
	public static final String TAG = WeatherFragment.class.toString();


	public static LocationInputFragment getInstance()
	{
		return new LocationInputFragment();
	}


	private TextInputEditText mLocationInput;


	@Override
	public void initComponents()
	{
		super.initComponents();
		mLocationInput = (TextInputEditText) getRootView().findViewById(R.id.fragment_location_input_edit_text);
		mLocationInput.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if(actionId == EditorInfo.IME_ACTION_DONE)
				{
					findWeather();
					return true;
				}
				return false;
			}
		});
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
		if(!mLocationInput.getText().toString().isEmpty()){
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.main_container, WeatherFragment.getInstance(getLocationViaInput()), WeatherFragment.TAG)
				.addToBackStack(WeatherFragment.TAG)
				.commit();

		mLocationInput.setText("");
		}else{	Toast.makeText(getActivity(), "Ahojky musíš zadat nějaké město",Toast.LENGTH_LONG).show();
		}
	}


	@NonNull
	private String getLocationViaInput()
	{
		return mLocationInput.getText().toString();
	}

}
