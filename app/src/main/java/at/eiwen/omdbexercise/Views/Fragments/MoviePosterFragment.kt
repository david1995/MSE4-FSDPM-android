package at.eiwen.omdbexercise.Views.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import at.eiwen.omdbexercise.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_poster.*

class MoviePosterFragment : Fragment()
{
    private val args: MoviePosterFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_movie_poster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        Picasso.get().load(args.poster).into(moviePoster)
    }
}
