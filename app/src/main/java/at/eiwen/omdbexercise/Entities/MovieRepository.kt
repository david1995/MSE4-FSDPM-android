package at.eiwen.omdbexercise.Entities

import android.app.Application
import androidx.lifecycle.LiveData
import at.eiwen.omdbexercise.DataAccess.OmdbMoviesLoader
import at.eiwen.omdbexercise.Extensions.MovieRepositoryExtensions
import at.eiwen.omdbexercise.Models.MovieInfo
import at.eiwen.omdbexercise.R
import org.jetbrains.anko.doAsync

class MovieRepository(val application : Application)
{
    private val _dal : IMovieDal
    private val _liveDataList : LiveData<List<Movie>>

    init
    {
        val db = MovieDatabase.GetInstance(application)
        _dal = db?.GetMovieDal()!!
        Populate()
        _liveDataList = _dal.FindAll()
    }

    fun FindAll(): LiveData<List<Movie>>
    {
        return _dal.FindAll()
    }

    fun FindById(id: String): LiveData<Movie>
    {
        return _dal.FindById(id)
    }

    fun Search(term: String): LiveData<List<Movie>>
    {
        return _dal.Search(MovieRepositoryExtensions.GetQueryTerm(term))
    }

    fun GetMoviesToWatch(): LiveData<List<Movie>>
    {
        return _dal.GetMoviesToWatch()
    }

    fun GetFavoriteMovies(): LiveData<List<Movie>>
    {
        return _dal.GetFavoriteMovies()
    }

    private fun Create(movie: MovieInfo): Long
    {
        return _dal.Create(Movie(movie.imdbID, movie.Title, movie.Year, movie.Type, movie.Poster, false, false))
    }

    private fun Create(movies: List<MovieInfo>)
    {
        for (movie in movies)
        {
            Create((movie))
        }
    }

    fun Update(movie: Movie)
    {
        _dal.Update(movie)
    }

    private fun Populate()
    {
        doAsync {
            val fetchedMovies = OmdbMoviesLoader(application.getString(R.string.omdbApiKey)).FetchMovies()
            Create(fetchedMovies)
        }
    }
}
