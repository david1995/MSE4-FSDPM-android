package at.eiwen.omdbexercise

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import at.eiwen.omdbexercise.DataAccess.IMovieStore
import at.eiwen.omdbexercise.DataAccess.InMemoryMovieStore
import at.eiwen.omdbexercise.ViewModels.MoviesListViewModel
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment()
{
    lateinit var _movieStore : IMovieStore
    lateinit var _moviesListViewModel : MoviesListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener { Navigation.findNavController(view).navigate(R.id.movieDetailFragment) }

        InitializeDataSources()
    }

    fun InitializeDataSources()
    {
        _movieStore = InMemoryMovieStore()
        _moviesListViewModel = MoviesListViewModel(_movieStore, requireContext())
        SetupRecyclerView()
        SetupSearchListener()
    }

    fun SetupSearchListener()
    {
        movieSearchTextBox.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                println(p0.toString())
                FindMovie(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
            }
        })
    }

    fun SetupRecyclerView()
    {
        movieListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieListRecyclerView.adapter = adapter
        movieListRecyclerView.addItemDecoration(DividerItemDecoration(movieListRecyclerView.context, DividerItemDecoration.VERTICAL))
    }

    fun FindMovie(searchTerm: String)
    {
        if (searchTerm.length <= 2)
        {
            _moviesListViewModel.
            adapter.filter(db.movieCollection)
        }
        else
        {
            adapter.filter(db.search(text));
        }
    }
}
