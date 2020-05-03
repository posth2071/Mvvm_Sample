package com.jjj.mvvm_sample.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.jjj.mvvm_sample.Dao.ContactDao
import com.jjj.mvvm_sample.DataBase.ContactDatabase
import com.jjj.mvvm_sample.Entity.Contact

class ContactRepository(application: Application) {

    // INSTANCE는 ? 키워드(Nullable이므로 !!로 Null이 아님을 해줘야함)
    private val contactDatabase = ContactDatabase.getInstacne(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {  // Room DB에 MainThread로 접근 시 크래쉬 발생생
               contactDao.insert(contact)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        } catch (e: Exception) { }
    }
}