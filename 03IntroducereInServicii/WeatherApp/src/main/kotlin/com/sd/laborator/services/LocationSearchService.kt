package com.sd.laborator.services
import com.sd.laborator.interfaces.LocationSearchInterface
import org.json.JSONArray
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import org.slf4j.LoggerFactory
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
@Service
class LocationSearchService : LocationSearchInterface {
    private val logger = LoggerFactory.getLogger(LocationSearchService::class.java)

    override fun getLocationCoordinates(locationName: String): Pair<Double, Double>? {
// codificare parametru URL (deoarece poate conţine caracterespeciale)
        val encodedLocationName = URLEncoder.encode(locationName,StandardCharsets.UTF_8.toString())

// construire obiect de tip URL
        val locationSearchURL = URL("https://nominatim.openstreetmap.org/search?q=$encodedLocationName&format=json&limit=1")

// preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = locationSearchURL.readText()

        //parsare raspuns json
        val responseArray = JSONArray(rawResponse);
        if(responseArray.length() == 0) {
            return null
        }

        val locationObject = responseArray.getJSONObject(0)
        val latitude = locationObject.getDouble("lat")
        val longitude = locationObject.getDouble("lon")
        // Logare coordonate
        logger.info("Coordonatele pentru locația '$locationName': latitudine = $latitude, longitudine = $longitude")

        return Pair(latitude, longitude)

// parsare obiect JSON
        //val responseRootObject = JSONObject("{\"data\": ${rawResponse}}")
        //val responseContentObject = responseRootObject.getJSONArray("data").takeUnless { it.isEmpty}?.getJSONObject(0)
        //return responseContentObject?.getInt("woeid") ?: -1
    }
}