package com.example.viewmodelmaster

class Person(

    var name: String,
    var age: Int = 0
) {
    override fun toString( ): String {
        return "Name: ${name} age: ${age}"    }
}
