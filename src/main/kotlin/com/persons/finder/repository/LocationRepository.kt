package com.persons.finder.repository

import com.persons.finder.data.Location
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository

@EnableJpaRepositories
@ComponentScan
@EntityScan
interface LocationRepository : CrudRepository<Location, Long> {
}