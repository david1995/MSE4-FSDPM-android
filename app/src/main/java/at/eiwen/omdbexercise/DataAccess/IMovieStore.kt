package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo

interface IMovieStore
{
    fun GetMovies() : List<MovieInfo>
}
