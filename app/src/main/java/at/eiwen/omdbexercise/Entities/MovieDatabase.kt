package at.eiwen.omdbexercise.Entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase()
{
    abstract fun GetMovieDal() : IMovieDal

    companion object
    {
        private var _instance : MovieDatabase? = null

        fun GetInstance(context : Context) : MovieDatabase
        {
            synchronized(MovieDatabase::class)
            {
                if (_instance == null)
                {
                    _instance = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "Movies.db")
                                    .build()
                }
            }

            return _instance!!
        }
    }
}
