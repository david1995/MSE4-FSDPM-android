package at.eiwen.omdbexercise.DataAccess

import at.eiwen.omdbexercise.Models.MovieInfo

class InMemoryMovieStore : IMovieStore
{
    companion object {
        val Instance : InMemoryMovieStore = InMemoryMovieStore()
    }

    private var _movieInfos : ArrayList<MovieInfo>
    private var _fallbackMovieInfos : ArrayList<MovieInfo> = ArrayList()

    init
    {
        _fallbackMovieInfos.add(
            MovieInfo(
                "Bee Movie",
                2007,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMjE1MDYxOTA4MF5BMl5BanBnXkFtZTcwMDE0MDUzMw@@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Epic Movie",
                2007,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMTA3NDM5ODU3NzleQTJeQWpwZ15BbWU3MDgyMjgyNDE@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Movie 43",
                2013,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMTg4NzQ3NDM1Nl5BMl5BanBnXkFtZTcwNjEzMjM3OA@@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Scary Movie",
                2000,
                "movie",
                "tt0175142",
                "https://m.media-amazon.com/images/M/MV5BMGEzZjdjMGQtZmYzZC00N2I4LThiY2QtNWY5ZmQ3M2ExZmM4XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Scary Movie 2",
                2001,
                "movie",
                "tt0257106",
                "https://m.media-amazon.com/images/M/MV5BMzQxYjU1OTUtYjRiOC00NDg2LWI4MWUtZGU5YzdkYTcwNTBlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Scary Movie 3",
                2003,
                "movie",
                "tt0306047",
                "https://m.media-amazon.com/images/M/MV5BNDE2NTIyMjg2OF5BMl5BanBnXkFtZTYwNDEyMTg3._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "Scary Movie 4",
                2006,
                "movie",
                "tt0362120",
                "https://m.media-amazon.com/images/M/MV5BZmFkMzc2NTctN2U1Ni00MzE5LWJmMzMtYWQ4NjQyY2MzYmM1XkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "The Lego Batman Movie",
                2017,
                "movie",
                "tt4116284",
                "https://m.media-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "The Lego Movie",
                2014,
                "movie",
                "tt1490017",
                "https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "The Shawshank Redemption",
                1996,
                "movie",
                "tt0111161",
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"))
        _fallbackMovieInfos.add(
            MovieInfo(
                "The Simpsons Movie",
                2007,
                "movie",
                "tt0462538",
                "https://m.media-amazon.com/images/M/MV5BMTgxMDczMTA5N15BMl5BanBnXkFtZTcwMzk1MzMzMw@@._V1_SX300.jpg"))

        _movieInfos = _fallbackMovieInfos
    }

    override fun GetMovies(): List<MovieInfo>
    {
        return _movieInfos
    }
}
