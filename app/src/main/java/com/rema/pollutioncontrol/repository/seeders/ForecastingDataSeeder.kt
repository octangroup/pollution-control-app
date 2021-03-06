package com.rema.pollutioncontrol.repository.seeders

import android.content.Context
import com.rema.pollutioncontrol.models.AirQualityIndex
import com.rema.pollutioncontrol.models.Forecast
import com.rema.pollutioncontrol.models.Weather
import org.joda.time.DateTime
import kotlin.collections.ArrayList

class ForecastingDataSeeder {
    companion object {
        fun run(context: Context): ArrayList<Forecast> {
            val array = ArrayList<Forecast>()
//            var previousDate = DateTime()
//            for (i in 1..50) {
//               previousDate = previousDate.minusHours(2)
//                array.add(Forecast(previousDate, Weather(AirQualityIndex((10..300).random()), ((10..100).random()).toDouble(),  ((12..30).random()).toDouble(), ((60..150).random()).toDouble(), context.getString(R.string.weather_cloudy))))
//            }
            var nextDate = DateTime()
            for (i in 1..100) {
                nextDate = nextDate.plusHours(2)
                array.add(Forecast(nextDate, WeatherSeeder.run()))
            }
            return array
        }
    }
}

