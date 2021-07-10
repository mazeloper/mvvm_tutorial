package com.jschoi.develop.mvvm_pattern_tutorial

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jschoi.develop.mvvm_pattern_tutorial.room.Contact
import com.jschoi.develop.mvvm_pattern_tutorial.viewmodel.ContactViewModel
import com.jschoi.develop.mvvm_pattern_tutorial.viewmodel.ContactViewModelFactory

class AddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    private val editAddName: EditText by lazy {
        findViewById(R.id.add_edittext_name)
    }
    private val editAddNumber: EditText by lazy {
        findViewById(R.id.add_edittext_number)
    }
    private val btnAdd: Button by lazy {
        findViewById(R.id.add_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER
            )
        ) {
            editAddName.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            editAddNumber.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        btnAdd.setOnClickListener {
            val name = editAddName.text.toString().trim()
            val number = editAddNumber.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact = Contact(id, name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}