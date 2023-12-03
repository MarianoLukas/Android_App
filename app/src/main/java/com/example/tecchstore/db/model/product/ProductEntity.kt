package com.example.tecchstore.db.model.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    indices = [
        Index(value = ["product_id"], unique = true),
        Index(value = ["code"], unique = true)
    ]
)
data class ProductEntity(
    @ColumnInfo(name = "product_id") @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "code") var code: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "img_id") val imgId: Int
)