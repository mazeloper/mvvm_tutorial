package com.jschoi.develop.mvvm_pattern_tutorial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jschoi.develop.mvvm_pattern_tutorial.room.Contact
import com.jschoi.develop.mvvm_pattern_tutorial.room.ContactRepository

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    // AndroidViewModel 에서 인자로 application 을 받는다.
    // Context 를 인자로 보낼 시 화면이 Destroy 되었을 때, 메모리 릭이 발생할 수 있다.

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}