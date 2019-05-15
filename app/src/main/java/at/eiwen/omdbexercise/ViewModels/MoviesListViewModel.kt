package at.eiwen.omdbexercise.ViewModels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import at.eiwen.omdbexercise.Models.MovieInfo
import at.eiwen.omdbexercise.Views.Fragments.MoviesListFragmentDirections
import at.eiwen.omdbexercise.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_list_item.view.*

class MoviesListViewModel(var data : List<MovieInfo>, val context : Context) : RecyclerView.Adapter<MoviesListViewModel.MovieHolder>()
{
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(movieHolder: MovieHolder, position: Int)
    {
        movieHolder.BindMovie(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder
    {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.fragment_movie_list_item, parent, false)
        return MovieHolder(inflatedView)
    }

    fun SetItems(items : List<MovieInfo>)
    {
        data = items
        notifyDataSetChanged()
    }

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener
    {
        private var _view: View = view
        private var _movie: MovieInfo? = null

        init
        {
            _view.setOnClickListener(this)
        }

        override fun onClick(v: View)
        {
            val action = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(_movie!!.Title, _movie!!.Year.toString(), _movie!!.Poster, _movie!!.imdbID)
            Navigation.findNavController(v).navigate(action)
        }

        fun BindMovie(movie: MovieInfo)
        {
            this._movie = movie
            Picasso.get().load(movie.Poster).into(_view.movie_collection_poster)
            _view.movie_collection_title.text = movie.Title
            _view.movie_collection_year.text = _view.context.resources.getString(R.string.movie_published, movie.Year.toString())
        }
    }
}
