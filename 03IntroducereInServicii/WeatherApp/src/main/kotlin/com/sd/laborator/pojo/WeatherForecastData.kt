package com.sd.laborator.pojo

data class WeatherForecastData(
    var location: String,
    var date: String,
    var weatherDescription: String,
    var temperature: Double, // Current temperature
    var windSpeed: Double,   // Wind speed in km/h
    var windDirection: String,
    var humidity: Double,
    var minTemp: Double,
    var maxTemp: Double
)

/*data class WeatherForecastData (
    var location: String,
    var date: String,
    var weatherState: String,
    var weatherStateIconURL: String,
    var windDirection: String,
    var windSpeed: Int, // km/h
    var minTemp: Int, // grade celsius
    var maxTemp: Int,
    var currentTemp: Int,
    var humidity: Int // procent
)*/
