package at.eiwen.omdbexercise.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import at.eiwen.omdbexercise.DataAccess.InMemoryMovieStore
import at.eiwen.omdbexercise.DataAccess.OmdbMovieProvider
import at.eiwen.omdbexercise.Entities.Movie
import at.eiwen.omdbexercise.Entities.MovieRepository
import at.eiwen.omdbexercise.R

class MoviesViewModel(application : Application) : AndroidViewModel(application)
{
    private val _repository = MovieRepository(application)
    private val _movieProvider = OmdbMovieProvider(InMemoryMovieStore.Instance, application.getString(R.string.omdbApiKey))
    val _movies = MediatorLiveData<List<Movie>>()
    val _selectedMovie = MediatorLiveData<Movie>()

    fun FindAllMovies()
    {
        _movies.addSource(_repository.FindAll()) { _movies.value = it }
    }

    fun Search(term : String)
    {
        _movies.addSource(_repository.Search(term)) { _movies.value = it }
    }

    fun GetMoviesToWatch()
    {
        _movies.addSource(_repository.GetMoviesToWatch()) { _movies.value = it }
    }

    fun GetFavoriteMovies()
    {
        _movies.addSource(_repository.GetFavoriteMovies()) { _movies.value = it }
    }

    fun FindById(id : String)
    {
        _selectedMovie.addSource(_repository.FindById(id)) { _selectedMovie.value = it }
    }

    fun Update(movie : Movie)
    {
        _repository.Update(movie)
    }
}
