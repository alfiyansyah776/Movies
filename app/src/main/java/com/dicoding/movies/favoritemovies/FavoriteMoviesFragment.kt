package com.dicoding.movies.favoritemovies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movies.Detail.DetailActivity
import com.dicoding.movies.ListMovies.MoviesFragmentCallback
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.databinding.FragmentFavoriteMoviesBinding
import com.dicoding.movies.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment(), MoviesFragmentCallback {

    private lateinit var binding: FragmentFavoriteMoviesBinding
    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favoriteMoviesAdapter = FavoriteMoviesAdapter(this)
            favoriteMoviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIES, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteMoviesViewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]

            favoriteMoviesViewModel.getFavoritedMovies().observe(viewLifecycleOwner, { movies ->
                favoriteMoviesAdapter.submitList(movies)
                binding.viewEmpty.root.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE

            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMoviesAdapter
            }
        }
    }

    override fun onShareClick(movies: Movies) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share this Tv Shows now.")
                .setText(resources.getString(R.string.share_text, movies.title))
                .startChooser()
        }
    }


}