package cz.matej.app.strvacademyweather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cz.matej.app.strvacademyweather.R;
import cz.matej.app.strvacademyweather.fragment.LocationInputFragment;


public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}


	private void init()
	{
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.main_container, LocationInputFragment.getInstance())
				.commit();
	}

}
