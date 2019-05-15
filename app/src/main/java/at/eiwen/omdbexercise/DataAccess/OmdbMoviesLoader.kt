package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo
import at.eiwen.omdbexercise.Models.MovieSearchResult
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class OmdbMoviesLoader(val apiKey : String)
{
    fun FetchMovies() : List<MovieInfo>
    {
        try
        {
            val url = URL("https://www.omdbapi.com/?s=movie&y=2018&apikey=$apiKey")
            val readText = url.readText()
            return jacksonObjectMapper().readValue<MovieSearchResult>(readText).Search
        }
        catch (e: Exception)
        {
            return ArrayList()
        }
    }
}