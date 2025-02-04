package com.example.mywishlist

import android.content.Context
import androidx.room.Room
import com.example.mywishlist.data.WishDatabase
import com.example.mywishlist.data.WishRepository

object Graph {

    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database= Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()

    }

}