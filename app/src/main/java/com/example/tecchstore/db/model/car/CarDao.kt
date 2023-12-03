package com.example.tecchstore.db.model.car

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CarDao {
    @Query("select * from CarEntity")
    fun getAll(): List<CarEntity>

    @Query("select * from CarEntity c where c.code = :code")
    fun findById(code: String): CarEntity

    @Delete
    fun delete(car: CarEntity)

    @Upsert
    fun upsert(car: CarEntity)
}