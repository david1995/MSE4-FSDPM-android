package at.eiwen.omdbexercise.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Movies",
    indices = [
        Index(value = ["Title"], unique = true),
        Index(value = ["IsOnWatchList"], unique = false),
        Index(value = ["IsFavorite"], unique = false)]
)
class Movie(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val ImdbID : String,

    @ColumnInfo(name = "Title")
    val Title : String,

    @ColumnInfo(name = "Year")
    val Year : Int,

    @ColumnInfo(name = "Type")
    val Type : String,

    @ColumnInfo(name = "Poster")
    val Poster : String,

    @ColumnInfo(name = "IsOnWatchList")
    var IsOnWatchList : Boolean,

    @ColumnInfo(name = "IsFavorite")
    var IsFavorite : Boolean
)
