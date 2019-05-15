package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.Movie
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class OmdbApiMovieDetailsProvider(val apiKey : String) : IMovieDetailsProvider
{
    override fun GetMovieDetails(imdbId: String): Movie?
    {
        try
        {
            var url = URL("https://www.omdbapi.com/?i=$imdbId&apiKey=$apiKey")
            var content = url.readText()
            return jacksonObjectMapper().readValue<Movie>(content)
        }
        catch (e: Exception)
        {
            return null
        }
    }
}
