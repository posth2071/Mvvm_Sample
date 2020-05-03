package com.jjj.mvvm_sample.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjj.mvvm_sample.ContactAdapter
import com.jjj.mvvm_sample.Entity.Contact
import com.jjj.mvvm_sample.R
import com.jjj.mvvm_sample.ViewModel.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val contactViewModel by lazy {
        ViewModelProvider(this, ContactViewModel.Factory(application)).get(
            ContactViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactAdapter({ contact ->
            // item 클릭시 실행될 함수
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
            startActivity(intent)
        }, { contact ->
            // Item 길게 클릭 시 실행될 함수
            deleteDialog(contact)
        })

        main_recycleview.adapter = adapter
        main_recycleview.setHasFixedSize(true)

//        contactViewModel = ViewModelProvider(this, ContactViewModel.Factory(application))[contactViewModel::class.java]
        contactViewModel.getAll().observe(this , Observer<List<Contact>> { contacts ->
            // Update UI
            adapter.setContacts(contacts!!)
        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
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
}
