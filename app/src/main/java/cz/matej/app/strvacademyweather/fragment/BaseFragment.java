package cz.matej.app.strvacademyweather.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment
{

	/* Abstract members */


	/**
	 * Method which returns fragment layout's ID.
	 */
	public abstract int getLayoutID();

	/* Normal members */

	/**
	 * Root view that represents the base layout view instance.
	 */
	private View rootView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{

		setRootView(inflater.inflate(getLayoutID(), container, false));
		initComponents();
		onFragmentReady();


		return getRootView();
	}


	/**
	 * Method to be overridden if there is a need for component assigning.
	 */
	public void initComponents()
	{
	}


	public void onFragmentReady()
	{
	}

	/* ---- Getters && Setters ---- */


	/**
	 * Returns root view that represents the base layout view instance.
	 *
	 * @return Root view of the fragment
	 */
	public View getRootView()
	{
		return rootView;
	}


	public void setRootView(View rootView)
	{
		this.rootView = rootView;
	}

}