package com.sd.laborator.services
import com.sd.laborator.interfaces.TimeInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URL
import kotlin.math.roundToInt
@Service
class WeatherForecastService : WeatherForecastInterface {

    @Autowired
    private lateinit var timeService: TimeInterface

    override fun getForecastData(latitude: Double, longitude: Double): WeatherForecastData? {
        // ID-ul locaţiei nu trebuie codificat, deoarece este numeric
        val weatherForecastURL = URL( "https://api.open-meteo.com/v1/forecast?latitude=$latitude&longitude=$longitude&current_weather=true&daily=temperature_2m_max,temperature_2m_min&timezone=auto")

        // preluare conţinut răspuns HTTP la o cerere GET către URL-ul de mai sus
        val rawResponse: String = weatherForecastURL.readText()

        // parsare obiect JSON primit
        val responseObject = JSONObject(rawResponse)

        if (!responseObject.has("current_weather")) {
            return null
        }

        val currentWeather = responseObject.getJSONObject("current_weather")
        val dailyWeather = responseObject.getJSONObject("daily")

        val weatherCode= currentWeather.getInt("weathercode")
        val weatherDescription = getWeatherDescription(weatherCode)

        val weatherData = WeatherForecastData(
            location = "", //we-ll seet the location name in the controller
            date = timeService.getCurrentTime(),
            weatherDescription = weatherDescription,
            temperature = currentWeather.getDouble("temperature"),
            windSpeed = currentWeather.getDouble("windspeed"),
            windDirection = getWindDirection(currentWeather.getDouble("winddirection")),
            humidity = -1.0, // open meteo nu da si detalii despre umiditate
            minTemp = dailyWeather.getJSONArray("temperature_2m_min").getDouble(0),
            maxTemp = dailyWeather.getJSONArray("temperature_2m_max").getDouble(0)
        )

        return weatherData





       /*
        // construire şi returnare obiect POJO care încapsulează datele meteo
        return WeatherForecastData(
            location = responseRootObject.getString("title"),
            date = timeService.getCurrentTime(),
            weatherState =
            weatherDataObject.getString("weather_state_name"),
            weatherStateIconURL =
            "https://www.metaweather.com/static/img/weather/png/${weatherDataObject.getString("weather_state_abbr")}.png",
            windDirection =
            weatherDataObject.getString("wind_direction_compass"),
            windSpeed =
            weatherDataObject.getFloat("wind_speed").roundToInt(),
            minTemp = weatherDataObject.getFloat("min_temp").roundToInt(),maxTemp = weatherDataObject.getFloat("max_temp").roundToInt(),currentTemp =
            weatherDataObject.getFloat("the_temp").roundToInt(),
            humidity = weatherDataObject.getFloat("humidity").roundToInt())
        */
    }
    private fun getWeatherDescription(weatherCode: Int): String {
        return when (weatherCode) {
            0 -> "Clear sky"
            in 1..3 -> "Partly cloudy"
            45, 48 -> "Fog"
            in 51..57 -> "Drizzle"
            in 61..67 -> "Rain"
            in 71..77 -> "Snow"
            in 80..82 -> "Rain showers"
            in 95..99 -> "Thunderstorm"
            else -> "Unknown weather"
        }
    }
    private fun getWindDirection(degrees: Double): String {
        val directions = arrayOf(
            "North", "Northeast", "East", "Southeast",
            "South", "Southwest", "West", "Northwest"
        )
        val index = ((degrees / 45) + 0.5).toInt() % 8
        return directions[index]
    }
}