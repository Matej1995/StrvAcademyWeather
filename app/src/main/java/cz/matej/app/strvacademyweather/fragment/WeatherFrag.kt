package cz.matej.app.strvacademyweather.fragment

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import cz.matej.app.strvacademyweather.R
import cz.matej.app.strvacademyweather.api.RequestFactory
import cz.matej.app.strvacademyweather.api.listener.RequestListener
import cz.matej.app.strvacademyweather.entity.CurrentWeatherEntity

class WeatherFrag : BaseFragment(), RequestListener<CurrentWeatherEntity> {

    val TAG = WeatherFrag::class.java.name

    private var mHumidityView: TextView? = null
    private var mTempView: TextView? = null
    private var mPressureView: TextView? = null
    private var mTempMaxView: TextView? = null
    private var mTempMinView: TextView? = null
    private var mIconImageView: ImageView? = null


    override fun initComponents() {
        super.initComponents()
        this.mIconImageView = rootView.findViewById(R.id.mIconImageView) as ImageView
        this.mHumidityView = rootView.findViewById(R.id.Humidity) as TextView
        this.mTempView = rootView.findViewById(R.id.Temp) as TextView
        this.mPressureView = rootView.findViewById(R.id.Pressure) as TextView
        this.mTempMaxView = rootView.findViewById(R.id.TempMax) as TextView
        this.mTempMinView = rootView.findViewById(R.id.TempMin) as TextView
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_weather;
    }

    override fun onResponse(entity: CurrentWeatherEntity?) {
        if (entity != null) {
            setWeatherIcon(entity.weather[0].icon)
            mHumidityView!!.text = entity.main.humidity.toString()
            mTempView!!.text = entity.main.temp.toString()
            mPressureView!!.text = entity.main.pressure.toString()
            mTempMaxView!!.text = entity.main.tempMax.toString()
            mTempMinView!!.text = entity.main.tempMin.toString()
        } else {
            Log.v(TAG + ": onResponse (failure)", "Failure entity is null")
        }
    }

    private fun parseBundle() = if (arguments != null) {
        RequestFactory.getInstance().getCurrentWeather(arguments.getString("location"), this)
    } else {
        activity.finish()
    }

    fun getIconUrl(iconID: String): String {
        return "http://api.openweathermap.org/img/w/$iconID.png"
    }


    fun setWeatherIcon(iconID: String) {
        Glide.with(this).load(getIconUrl(iconID)).into(this.mIconImageView)
    }


}