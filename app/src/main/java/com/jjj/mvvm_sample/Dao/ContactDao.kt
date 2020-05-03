package com.jjj.mvvm_sample.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jjj.mvvm_sample.Entity.Contact

@Dao
interface ContactDao : BaseDao<Contact> {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

}