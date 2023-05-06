package com.persons.finder.domain.services

import com.persons.finder.data.Person
import org.springframework.data.domain.Pageable

interface PersonsService {
    fun getById(id: Long): Person
    fun save(person: Person)
    fun getAll(page : Int, size :Int) : List<Person>
}