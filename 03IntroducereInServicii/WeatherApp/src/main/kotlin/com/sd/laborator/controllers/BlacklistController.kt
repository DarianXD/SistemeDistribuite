package com.sd.laborator.controllers

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class BlacklistController {
    @RequestMapping("/blacklist.json", method = [RequestMethod.GET])
    @ResponseBody
    fun getBlacklist(): ResponseEntity<String> {
        // Creezi o listă de zone interzise în format JSON
        val blacklistJson = """
        [
            {"latitude": 45.697274, "longitude": 27.1856921},  
            {"latitude": 48.8566, "longitude": 2.3522},   
            {"latitude": 35.6895, "longitude": 139.6917}  
        ]
        """
        return ResponseEntity.ok(blacklistJson)
    }
}
// New York City
// Paris
// Tokyo