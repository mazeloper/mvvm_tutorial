package com.jschoi.develop.mvvm_pattern_tutorial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jschoi.develop.mvvm_pattern_tutorial.adapter.ContactAdapter
import com.jschoi.develop.mvvm_pattern_tutorial.room.Contact
import com.jschoi.develop.mvvm_pattern_tutorial.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.main_recycleview)
    }
    private val btnMain: Button by lazy {
        findViewById(R.id.main_button)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initRecyclerView()
    }

    private fun initViews() {

        btnMain.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }


    private fun initRecyclerView() {
        val contactAdapter = ContactAdapter({ contact ->
            Toast.makeText(this@MainActivity, contact.toString(), Toast.LENGTH_SHORT).show()
        }, { contact ->
            deleteDialog(contact)
        })

        recyclerView.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            contactAdapter.setContacts(contacts!!)
        })
    }


    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }

    /*
// https://thdev.tech/androiddev/2020/07/13/Android-Fragment-ViewModel-Example/
 private val contactViewModel: ContactViewModel by lazy {
     ViewModelProvider(this@MainActivity, object : ViewModelProvider.Factory {
         override fun <T : ViewModel?> create(modelClass: Class<T>): T {
             return ContactViewModel(application) as T
         }
     }).get(ContactViewModel::class.java)
 }

 */

}