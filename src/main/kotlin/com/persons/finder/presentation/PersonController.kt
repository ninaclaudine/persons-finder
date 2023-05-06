package com.persons.finder.presentation

import com.persons.finder.data.Person
import com.persons.finder.domain.services.PersonsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/persons")
class PersonController @Autowired constructor
(
        private val perImpl : PersonsServiceImpl
        ) {
    @PostMapping("create-person")
    fun createPerson(@RequestBody person: Person): Long {
        perImpl.save(person)
        return person.id.toLong()
    }

    @GetMapping("person")
    fun getAllPerson(@RequestParam(defaultValue = "1") page : Int,
                     @RequestParam(defaultValue = "5") size : Int): List<Person> {
        return perImpl.getAll(page, size)
    }

    @GetMapping("person/{id}")
    fun getById(@PathVariable @Param("id") id : Long): Person {
        return perImpl.getById(id.toLong())
    }
}