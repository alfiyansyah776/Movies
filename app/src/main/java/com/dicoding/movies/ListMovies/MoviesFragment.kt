package com.dicoding.movies.ListMovies


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movies.Detail.DetailActivity
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.databinding.FragmentMoviesBinding
import com.dicoding.movies.viewmodel.ViewModelFactory
import com.dicoding.movies.vo.Status


class MoviesFragment : Fragment(), MoviesFragmentCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false )
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            val moviesAdapter = MoviesAdapter(this)
            moviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIES, selectedData)
                startActivity(intent)
            }

           viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
               if (movies != null){
                   when (movies.status){
                       Status.LOADING -> fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
                       Status.SUCCESS -> {
                           fragmentMoviesBinding.progressBar.visibility = View.GONE
                           moviesAdapter.submitList(movies.data)

                       }

                       Status.ERROR -> {
                           fragmentMoviesBinding.progressBar.visibility = View.GONE
                           Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                       }
                   }
               }

           })

            with(fragmentMoviesBinding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onShareClick(movies : Movies){
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share this movies now.")
                .setText(resources.getString(R.string.share_text, movies.title))
                .startChooser()
        }
    }
}
