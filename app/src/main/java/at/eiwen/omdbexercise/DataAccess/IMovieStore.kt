package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieCollection

interface IMovieStore {
    fun GetMovies() : List<MovieCollection>
    fun FindMovie(searchTerm : String) : List<MovieCollection>
}