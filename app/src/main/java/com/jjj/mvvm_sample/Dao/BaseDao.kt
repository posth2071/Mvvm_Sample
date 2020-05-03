package com.jjj.mvvm_sample.Dao

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj :T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}