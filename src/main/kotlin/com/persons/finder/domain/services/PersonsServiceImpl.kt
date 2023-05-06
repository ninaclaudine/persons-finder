package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.repository.PersonRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonsServiceImpl(private val personRepository: PersonRepository) : PersonsService{


    override fun getById(id: Long): Person {
        return  personRepository.findById(id).get()
    }

    override fun save(person: Person) {
        if(Optional.empty<Person> () == personRepository.findById(person.id.toLong())){
            personRepository.save(person)
        }else{
            throw  Exception("Already Exist!")
        }
    }

    override fun getAll(page : Int, size :Int): List<Person> {
        return personRepository.findAll(PageRequest.of(page - 1, size)).toList()
    }
}