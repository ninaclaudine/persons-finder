package com.persons.finder.repository

import com.persons.finder.data.Person
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository

@EnableJpaRepositories
@ComponentScan
@EntityScan
interface PersonRepository : CrudRepository<Person, Long> {
     fun findAll(pageable: Pageable): MutableIterable<Person>
}