package at.eiwen.omdbexercise.ViewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import at.eiwen.omdbexercise.DataAccess.IMovieStore
import at.eiwen.omdbexercise.Models.MovieCollection
import at.eiwen.omdbexercise.R
import com.squareup.picasso.Picasso

class MoviesListViewModel(var movieStore : IMovieStore, val context : Context) : RecyclerView.Adapter<MoviesListViewModel.MovieHolder>()
{
    override fun getItemCount(): Int = movieStore.GetMovies().count()

    override fun onBindViewHolder(p0: MovieHolder, p1: Int)
    {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(inflatedView)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieHolder
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun ApplyFilter()
    {
        notifyDataSetChanged()
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener
    {
        private var _view: View = v
        private var _movie: MovieCollection? = null

        init
        {
            _view.setOnClickListener(this)
        }

        override fun onClick(v: View)
        {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                _movie!!.Title,
                _movie!!.Year.toString(),
                _movie!!.Poster)

            Navigation.findNavController(v).navigate(action)
        }

        fun bindMovie(movie: MovieCollection)
        {
            _movie = movie
            Picasso.get().load(movie.Poster).into(_view.movie_collection_poster)
            _view.movie_collection_title.text = movie.Title
            _view.movie_collection_year.text = "Veröffentlicht: " + movie.Year
        }
    }
}
