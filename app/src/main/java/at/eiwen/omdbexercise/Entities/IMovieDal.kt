package at.eiwen.omdbexercise.Entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IMovieDal
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Create(movie: Movie) : Long

    @Query("select Id, Title, Year, Type, Poster, IsOnWatchList, IsFavorite from Movies")
    fun FindAll() : LiveData<List<Movie>>

    @Query("select Id, Title, Year, Type, Poster, IsOnWatchList, IsFavorite from Movies where id = :id")
    fun FindById(id: String) : LiveData<Movie>

    @Query("select Id, Title, Year, Type, Poster, IsOnWatchList, IsFavorite from Movies where title like :term OR year = cast(:term as NUMBER)")
    fun Search(term: String) : LiveData<List<Movie>>

    @Query("select Id, Title, Year, Type, Poster, IsOnWatchList, IsFavorite from Movies where IsOnWatchList = 1 order by Title")
    fun GetMoviesToWatch() : LiveData<List<Movie>>

    @Query("select Id, Title, Year, Type, Poster, IsOnWatchList, IsFavorite from Movies where IsFavorite = 1 order by Title")
    fun GetFavoriteMovies() : LiveData<List<Movie>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun Update(movie: Movie) : Void

    @Delete
    fun Delete(movie: Movie) : Void
}
