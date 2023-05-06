package com.persons.finder.data

import jdk.jfr.DataAmount
import lombok.Data
import javax.persistence.Entity
import javax.persistence.Id

@Data
@Entity
data class Location(
    // Tip: Person's id can be used for this field
        @Id
        val referenceId: Long = 0,
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
)
