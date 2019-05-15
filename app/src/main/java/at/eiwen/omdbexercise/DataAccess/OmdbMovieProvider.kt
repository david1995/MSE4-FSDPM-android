package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo
import at.eiwen.omdbexercise.Models.MovieSearchResult
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class OmdbMovieProvider(movieStore : IMovieStore, val apiKey : String) : IMovieProvider
{
    private val _movieStore : IMovieStore = movieStore
    private lateinit var _movieInfos : List<MovieInfo>
    private var _hasFetchedMovies : Boolean = false

    override fun FindMovies(searchTerm: String): List<MovieInfo>
    {
        EnsureMoviesAreFetched()
        val uppercaseSearchTerm = searchTerm.toUpperCase()
        return _movieInfos.filter { movieCollection -> movieCollection.Title.toUpperCase().contains(uppercaseSearchTerm) }
    }

    override fun GetMovies(): List<MovieInfo>
    {
        EnsureMoviesAreFetched()
        return _movieInfos
    }

    private fun EnsureMoviesAreFetched()
    {
        if (_hasFetchedMovies)
        {
            return
        }

        try
        {
            val movies = OmdbMoviesLoader(apiKey).FetchMovies()
            _movieInfos = movies
            _hasFetchedMovies = true
        }
        catch (e: Exception)
        {
            _movieInfos = _movieStore.GetMovies()
        }
    }
}
