package at.eiwen.omdbexercise.Views.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.eiwen.omdbexercise.DataAccess.IMovieProvider
import at.eiwen.omdbexercise.DataAccess.InMemoryMovieStore
import at.eiwen.omdbexercise.DataAccess.OmdbMovieProvider
import at.eiwen.omdbexercise.Entities.MovieRepository
import at.eiwen.omdbexercise.Models.MovieInfo
import at.eiwen.omdbexercise.R
import at.eiwen.omdbexercise.ViewModels.MoviesListViewModel
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MoviesListFragment : Fragment()
{
    private lateinit var _movieProvider : IMovieProvider
    private lateinit var _moviesListViewModel : MoviesListViewModel
    private lateinit var _moviesRepository : MovieRepository

    override fun onCreateView(inflater: LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener { Navigation.findNavController(view).navigate(R.id.movieDetailFragment) }

        InitializeDataSources()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.favoritesMenuItem ->
            {
                val favoriteMoviesLiveData = _moviesRepository.GetFavoriteMovies()
                val favoriteMovies = favoriteMoviesLiveData.value!!.map {
                    MovieInfo(
                        it.Title,
                        it.Year,
                        it.Type,
                        it.ImdbID,
                        it.Poster
                    )
                }

                _moviesListViewModel.SetItems(favoriteMovies)

            }
            R.id.toWatchMenuItem ->
            {
                val toWatchMoviesLiveData = _moviesRepository.GetMoviesToWatch()
                val toWatch = toWatchMoviesLiveData.value!!.map {
                    MovieInfo(
                        it.Title,
                        it.Year,
                        it.Type,
                        it.ImdbID,
                        it.Poster
                    )
                }

                _moviesListViewModel.SetItems(toWatch)
            }
            R.id.allMoviesMenuItem ->
            {
                var movies = _movieProvider.GetMovies()
                _moviesListViewModel.SetItems(movies)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun InitializeDataSources()
    {
        _movieProvider = OmdbMovieProvider(InMemoryMovieStore(), getString(R.string.omdbApiKey))
        _moviesListViewModel = MoviesListViewModel(ArrayList(), requireContext())
        _moviesRepository = MovieRepository(activity!!.application)
        doAsync {

            var movies = _movieProvider.GetMovies()
            uiThread {
                _moviesListViewModel.SetItems(movies)
            }
        }

        SetupRecyclerView()
        SetupSearchListener()
    }

    fun SetupSearchListener()
    {
        movieSearchTextBox.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?)
            {
                FindMovie(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }
        })
    }

    fun SetupRecyclerView()
    {
        movieListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieListRecyclerView.adapter = _moviesListViewModel
        movieListRecyclerView.addItemDecoration(DividerItemDecoration(movieListRecyclerView.context, DividerItemDecoration.VERTICAL))
    }

    fun FindMovie(searchTerm : String)
    {
        if (searchTerm.length < 2)
        {
            var movies = _movieProvider.GetMovies()
            _moviesListViewModel.SetItems(movies)
        }
        else
        {
            var foundMovies = _movieProvider.FindMovies(searchTerm)
            _moviesListViewModel.SetItems(foundMovies)
        }
    }
}
