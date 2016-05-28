package cz.matej.app.strvacademyweather.api.listener;

public interface RequestListener<X extends Object>
{
	void onResponse(X object);
}
