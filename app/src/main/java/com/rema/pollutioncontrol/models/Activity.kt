package com.rema.pollutioncontrol.models

import com.rema.pollutioncontrol.R
import java.io.Serializable

class Activity(var name: Int, var icon: Int, var weather: Weather, var description: Int, var sensitivity: Int = 1, var allowsBadWeather: Boolean = false) : Serializable {

    fun isFavorable(): Boolean {
        return if (sensitivity == 1 && weather.qualityIndex.level() == 1) {
            checkBadWeather()
        } else if (sensitivity == 2 && weather.qualityIndex.level() <= 2) {
            checkBadWeather()
        } else if (sensitivity == 3 && weather.qualityIndex.level() <= 3) {
            checkBadWeather()
        } else if (sensitivity == 4 && weather.qualityIndex.level() <= 4) {
            checkBadWeather()
        } else {
            false
        }
    }


    fun isFailyFavorable(): Boolean {
        return if (sensitivity == 1 && weather.qualityIndex.level() == 2) {
            checkBadWeather()
        } else if (sensitivity == 2 && weather.qualityIndex.level() == 3) {
            checkBadWeather()
        } else if (sensitivity >= 3 && weather.qualityIndex.level() == 4) {
            checkBadWeather()
        } else {
            false
        }
    }

    fun isNotFavorable(): Boolean {
        return if (sensitivity == 1 && weather.qualityIndex.level() >= 3) {
            checkBadWeather()
        } else if (sensitivity >= 2 && weather.qualityIndex.level() >= 4) {
            checkBadWeather()
        } else {
            false
        }
    }

    fun badgeColor(): Int {
        return when {
            isFavorable() -> R.color.colorActivityFavorable
            isFailyFavorable() -> R.color.colorActivityFairlyFavorable
            else -> R.color.colorActivityNotFavorable
        }
    }

    fun condition(): Int {
        return when {
            isFavorable() -> R.string.good
            isFailyFavorable() -> R.string.fair
            else -> R.string.bad
        }
    }


    private fun checkBadWeather(): Boolean {
        if (!allowsBadWeather) {
            return weather.condition <= 1
        }
        return true
    }

}
