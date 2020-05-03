package com.jjj.mvvm_sample.View

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jjj.mvvm_sample.Entity.Contact
import com.jjj.mvvm_sample.R
import com.jjj.mvvm_sample.ViewModel.ContactViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity: AppCompatActivity() {
    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        Log.d("intent", "intent != null [${intent != null}]")
        Log.d("intent", "intent.hasExtra(NAME) [${intent.hasExtra(EXTRA_CONTACT_NAME)}]")
        Log.d("intent", "intent.hasExtra(NUMBER) [${intent.hasExtra(EXTRA_CONTACT_NUMBER)}]")
        Log.d("intent", "intent.hasExtra(ID) [${intent.hasExtra(EXTRA_CONTACT_ID)}]")
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER
            ) && intent.hasExtra(EXTRA_CONTACT_ID)) {
            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim()
            val number = add_edittext_number.text.toString().trim()

            if(name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "이름과 번호를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact =
                    Contact(id, name, number, initial)
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