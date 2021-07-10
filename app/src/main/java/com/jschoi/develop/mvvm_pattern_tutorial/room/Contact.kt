package com.jschoi.develop.mvvm_pattern_tutorial.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 연락처 Data Class
 *
 */
@Entity(tableName = "contact")
data class Contact(

    // null 인 경우, 자동으로 생성
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char

) {
    constructor() : this(null, "", "", '\u0000')
}
