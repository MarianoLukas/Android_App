package com.example.tecchstore.db.model.car

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["code"], unique = true)
    ]
)
data class CarEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "code") var code: String
)