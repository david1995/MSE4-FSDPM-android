package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieCollection

class InMemoryMovieStore : IMovieStore
{
    private var _movieCollections : List<MovieCollection> = ArrayList()

    override fun GetMovies(): List<MovieCollection>
    {
        return _movieCollections;
    }

    override fun FindMovie(searchTerm: String): List<MovieCollection>
    {
        return _movieCollections.filter { movieCollection -> movieCollection.Title.contains(searchTerm) }
    }
}
