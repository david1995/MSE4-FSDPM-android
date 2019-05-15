package at.eiwen.omdbexercise.Views.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import at.eiwen.omdbexercise.DataAccess.IMovieDetailsProvider
import at.eiwen.omdbexercise.DataAccess.OmdbApiMovieDetailsProvider
import at.eiwen.omdbexercise.Models.Movie
import at.eiwen.omdbexercise.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MovieDetailFragment : Fragment()
{
    private lateinit var _movieDetailsProvider : IMovieDetailsProvider
    private val _args: MovieDetailFragmentArgs by navArgs()
    private var _movie: Movie? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        detailMoviePoster.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMoviePosterFragment(_args.poster)
            Navigation.findNavController(it).navigate(action)
        }

        _movieDetailsProvider = OmdbApiMovieDetailsProvider(getString(R.string.omdbApiKey))

        PrepareViews()
        FetchMovieDetails()
    }

    private fun PrepareViews()
    {
        activity?.title = _args.title
        detailMovieTitle.text = _args.title
        detailMovieYear.text = resources.getString(R.string.movie_published, _args.year)
        Picasso.get().load(_args.poster).into(detailMoviePoster)
    }

    private fun FetchMovieDetails()
    {
        doAsync {
            val result = _movieDetailsProvider.GetMovieDetails(_args.imdbId)
            uiThread {
                _movie = result
                detailMovieWriter?.text = resources.getString(R.string.movie_writer_content, _movie?.Writer)
                detailMoviePlot?.text = resources.getString(R.string.movie_plot_content, _movie?.Plot)
            }
        }
    }
}
