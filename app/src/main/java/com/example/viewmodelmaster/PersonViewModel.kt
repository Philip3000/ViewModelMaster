package com.example.viewmodelmaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {

    private var _persons = mutableListOf<Person>()
    private var mutableLiveData: MutableLiveData<List<Person>> = MutableLiveData()

    val persons: LiveData<List<Person>> = mutableLiveData

    fun add(person: Person) {
        _persons.add(person)
        mutableLiveData.value = _persons
    }
    fun update(person: Person) {
        val updatedPerson = person
        updatedPerson.age = person.age
        updatedPerson.name = person.name
        _persons.remove(person)
        _persons.add(updatedPerson)
        mutableLiveData.value = _persons
    }
    operator fun get(position: Int): Person { // [] operator overloading
        return _persons[position]
    }
    fun remove(position: Int) {
        _persons.removeAt(position)
        mutableLiveData.value = _persons
    }
}