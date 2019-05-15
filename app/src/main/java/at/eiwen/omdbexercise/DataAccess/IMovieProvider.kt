package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo

interface IMovieProvider
{
    fun GetMovies() : List<MovieInfo>
    fun FindMovies(searchTerm : String) : List<MovieInfo>
}
