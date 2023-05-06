package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.repository.LocationRepository
import com.persons.finder.repository.PersonRepository
import org.springframework.stereotype.Service
import java.lang.Math.abs
import java.util.*

@Service
class LocationsServiceImpl(
        private val locationRepository: LocationRepository,
        private val personRep : PersonRepository) : LocationsService {

    override fun addLocation(location: Location) {
        try{
            if(Optional.empty<Person> () != personRep.findById(location.referenceId.toLong())){
                if(Optional.empty<Location>() == locationRepository.findById(location.referenceId.toLong())){
                    locationRepository.save(location)
                }else if(Optional.empty<Location>()  != locationRepository.findById(location.referenceId.toLong())){
                    locationRepository.save(location)
                }
            }else{
                throw  Exception("Person Id not existing!")
            }
        }catch(exception : Exception){
            throw exception
        }

    }

    override fun removeLocation(locationReferenceId: Long) {
        locationRepository.deleteById(locationReferenceId)
    }

    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double, id : Long): Double {
            val locDto : Optional<Location> = locationRepository.findById(id.toLong())

            var miles : Double =  1.15077945
            var lat1: Double = Math.toRadians(latitude)
            var lat2: Double = Math.toRadians(locDto.get().latitude)
            var lon1: Double = Math.toRadians(longitude)
            var lon2: Double = Math.toRadians(locDto.get().longitude)

            var angle: Double = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                    + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2))
            var nauticalMiles: Double = 60 * Math.toDegrees(angle)
            var statuteMiles: Double = miles * nauticalMiles
            var distanceinKm: Double = statuteMiles * 1.60934

        return distanceinKm
    }

}