package com.jjj.mvvm_sample.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jjj.mvvm_sample.Dao.ContactDao
import com.jjj.mvvm_sample.Entity.Contact

@Database(version = 1, entities = [Contact::class])
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactDao

    companion object {
        // Repository에서 호출해서 사용할 DB 인스턴스
        private var INSTANCE: ContactDatabase? = null

        fun getInstacne(context: Context): ContactDatabase? {
            if(INSTANCE == null) {
                // 여러 스레드가 동시에 접근 못하도록 차단
                synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact.db"
                    )
                        .fallbackToDestructiveMigration() // DB가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                        .build()
                }
            }
            return INSTANCE
        }
    }
}