package com.sd.laborator.services

import com.sd.laborator.interfaces.GeoFilterInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestratorService {
    @Autowired
    private lateinit var locationSearchService: LocationSearchInterface
    @Autowired
    private lateinit var geoFilterService: GeoFilterInterface
    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

    fun getWeatherForecast(location: String): String {
        // 1. Obținem coordonatele locației
        val coordinates = locationSearchService.getLocationCoordinates(location)
            ?: return "Could not find data for location \"$location\"!"

        val (latitude, longitude) = coordinates

        // 2. Verificăm dacă locația este permisă
        if (!geoFilterService.isLocationAllowed(latitude, longitude)) {
            return "Access to weather data for \"$location\" is forbidden!"
        }

        // 3. Obținem prognoza meteo
        val forecastData = weatherForecastService.getForecastData(latitude, longitude)
            ?: return "Failed to fetch weather data."

        forecastData.location = location
        return forecastData.toString()
    }
}
