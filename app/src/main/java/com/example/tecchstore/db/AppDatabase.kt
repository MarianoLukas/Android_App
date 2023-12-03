package com.example.tecchstore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tecchstore.db.model.car.CarDao
import com.example.tecchstore.db.model.car.CarEntity
import com.example.tecchstore.db.model.product.ProductDao
import com.example.tecchstore.db.model.product.ProductEntity

@Database(
    version = 1,
    entities = [
        ProductEntity::class,
    CarEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance != null) return instance!!

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                this.instance = instance
                return instance
            }
        }
    }
}