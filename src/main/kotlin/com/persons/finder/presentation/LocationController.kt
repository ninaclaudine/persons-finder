package com.persons.finder.presentation

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.domain.services.LocationsServiceImpl
import com.persons.finder.repository.LocationRepository
import com.persons.finder.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/location")
class LocationController @Autowired constructor(
        private val locImpl : LocationsServiceImpl,
        private val locationRepository: LocationRepository,
        private val personRep : PersonRepository
){
    @PutMapping("create-location")
    fun createLocation(@RequestBody location : Location) : Long {
        locImpl.addLocation(location)
        return location.referenceId.toLong()
    }

    /*
       TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
       TODO API just return a list of persons ids (JSON)
       // Example
       // John wants to know who is around his location within a radius of 10km
       // API would be called using John's id and a radius 10km
    */

    @GetMapping("getAllNearMe/{id}")
    fun getAllLocation(@RequestParam radiusInKm : Double,
                       @PathVariable @Param("id") id : Long): MutableList<Location>? {

        if(Optional.empty<Person>() != personRep.findById(id.toLong())) {

            val locList: MutableList<Location> = locationRepository.findAll().toMutableList()
            locList.removeIf { e->e.referenceId.equals(id.toLong()) }
            val latitudeList: MutableList<Double> = mutableListOf<Double>()
            val longitudeList: MutableList<Double> = mutableListOf<Double>()

            locList.forEach { e -> latitudeList.add(e.latitude) }
            locList.forEach { e -> longitudeList.add(e.longitude) }
            val indexToRemove : MutableList<Long>  = mutableListOf<Long>()

            println(locList)
            for (index in 0..locList.size - 1) {
                var distance: Double = locImpl.findAround(latitudeList.get(index), longitudeList.get(index), radiusInKm,id)
                println(" "+distance+ " is within " + radiusInKm +"?")
                if (radiusInKm < distance) {
                    indexToRemove.add(locList.get(index).referenceId)
                }
            }
            indexToRemove.forEach { forremoval->
                locList.removeIf {
                    e->e.referenceId == forremoval }
            }

            return locList
        }else{
            throw Exception("Person ID Not Found!");
        }
    }
}