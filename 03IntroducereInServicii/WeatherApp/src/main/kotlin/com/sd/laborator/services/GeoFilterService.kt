package com.sd.laborator.services

import com.sd.laborator.interfaces.GeoFilterInterface
import org.json.JSONArray
import org.springframework.stereotype.Service
import java.net.URL

@Service
class GeoFilterService : GeoFilterInterface {

    override fun isLocationAllowed(latitude: Double, longitude: Double): Boolean {
        // URL-ul black list-ului poate fi un link către un fișier JSON sau o bază de date
        val blacklistURL = URL("http://localhost:8080/blacklist.json") // Presupunând că aplicația rulează pe portul 8080
        val rawResponse = blacklistURL.readText()

        // Parsarea răspunsului ca fiind o listă de zone interzise (în exemplu, latitudine și longitudine sunt într-un JSON)
        val blacklistedZones = JSONArray(rawResponse)

        for (i in 0 until blacklistedZones.length()) {
            val zone = blacklistedZones.getJSONObject(i)
            val blacklistedLatitude = zone.getDouble("latitude")
            val blacklistedLongitude = zone.getDouble("longitude")

            // Simplificăm verificarea ca fiind o potrivire exactă; putem extinde cu verificări mai complexe
            if (latitude == blacklistedLatitude && longitude == blacklistedLongitude) {
                return false
            }
        }
        return true
    }
}
