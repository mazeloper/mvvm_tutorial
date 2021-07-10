package com.jschoi.develop.mvvm_pattern_tutorial.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    // LiveData 를 감싸다.
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun gatAll(): LiveData<List<Contact>>

    // onConflict : 중복 데이터를 어케 처리 할것인가 , REPLACE : 교체
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}