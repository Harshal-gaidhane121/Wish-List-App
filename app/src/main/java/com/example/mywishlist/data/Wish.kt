package com.example.mywishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long=0L,
    @ColumnInfo(name = "wish-title")
    val title : String="",
    @ColumnInfo(name = "wish-desc")
    val description : String=""
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "First Wish", description = "Description of first list"),
        Wish(title = "Second Wish", description = "Description of second list"),
        Wish(title = "Third Wish", description = "Description of third list"),
        Wish(title = "Forth Wish", description = "Description of forth list"),
        Wish(title = "Fifth Wish", description = "Description of fifth list"),

        )
}