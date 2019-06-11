package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo

class InMemoryMovieStore : IMovieStore
{
    private var _movieInfos : ArrayList<MovieInfo>
    private var _fallbackMovieInfos : ArrayList<MovieInfo> = ArrayList()

    init
    {
        _movieInfos = _fallbackMovieInfos
    }

    override fun GetMovies(): List<MovieInfo>
    {
        return _movieInfos
    }
}
