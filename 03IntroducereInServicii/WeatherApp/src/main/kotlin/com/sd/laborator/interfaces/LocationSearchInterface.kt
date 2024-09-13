package com.sd.laborator.interfaces
interface LocationSearchInterface {
    fun getLocationCoordinates(locationName: String): Pair<Double, Double>?
}