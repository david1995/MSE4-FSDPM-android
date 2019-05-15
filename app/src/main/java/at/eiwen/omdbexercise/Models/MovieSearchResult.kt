package at.eiwen.omdbexercise.Models

data class MovieSearchResult(
    val Search: ArrayList<MovieInfo>,
    val totalResults: String,
    val Response: String
)
