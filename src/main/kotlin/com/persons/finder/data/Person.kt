package com.persons.finder.data

import javax.persistence.*

@Entity
class Person(
        val name: String = "",
        @Id
        var id: Long = 0
)