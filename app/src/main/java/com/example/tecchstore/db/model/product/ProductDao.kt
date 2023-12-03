package com.example.tecchstore.db.model.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("select * from ProductEntity")
    fun getAll(): List<ProductEntity>

    @Query("select * from ProductEntity p where p.code = :code")
    fun getByCode(code: String): ProductEntity

    @Insert
    fun insert(product: ProductEntity)

    @Update
    fun update(product: ProductEntity)
}