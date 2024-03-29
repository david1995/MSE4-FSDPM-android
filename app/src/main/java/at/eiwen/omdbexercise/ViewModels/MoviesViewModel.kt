package at.eiwen.omdbexercise.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import at.eiwen.omdbexercise.Entities.Movie
import at.eiwen.omdbexercise.Entities.MovieRepository

class MovieViewModel(application : Application) : AndroidViewModel(application)
{
    private val _repository = MovieRepository(application)
    val _movies = MediatorLiveData<List<Movie>>()
    val _selectedMovie = MediatorLiveData<Movie>()

    fun FindAll()
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
