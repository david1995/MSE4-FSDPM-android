package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.Movie

interface IMovieDetailsProvider
{
    fun GetMovieDetails(imdbId : String) : Movie?
}