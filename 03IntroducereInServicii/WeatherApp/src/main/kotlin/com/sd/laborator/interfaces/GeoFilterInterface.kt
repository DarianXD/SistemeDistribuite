package com.sd.laborator.interfaces

interface GeoFilterInterface {
    fun isLocationAllowed(latitude: Double, longitude: Double): Boolean
}
