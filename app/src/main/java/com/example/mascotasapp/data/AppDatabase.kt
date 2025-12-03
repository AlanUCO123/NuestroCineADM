package com.example.mascotasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
entities = [
    ProductoEntity:: class,
    CarritoEntity:: class,
],
        version = 1,
        exportSchema = false
)
abstract class  AppDatabase : RoomDatabase(){

    abstract fun productoDao(): ProductoDao
    abstract fun CarritoDao(): CarritoDao
}
