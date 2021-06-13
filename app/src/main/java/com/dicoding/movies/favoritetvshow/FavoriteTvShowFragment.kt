package com.dicoding.movies.favoritetvshow

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
import com.dicoding.movies.ListTv.TvFragmentCallback
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.movies.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment(), TvFragmentCallback {

    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel
    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val favoriteTvShowAdapter = FavoriteTvShowAdapter(this)
            favoriteTvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TV, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteTvShowViewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            favoriteTvShowViewModel.favoriteTvShow.observe(viewLifecycleOwner, { dataTvShow ->
                favoriteTvShowAdapter.submitList(dataTvShow)
                binding.viewEmpty.root.visibility = if(dataTvShow.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }
    }

    override fun onShareClick(tvShows: TvShows) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share this Tv Shows now.")
                .setText(resources.getString(R.string.share_text, tvShows.title))
                .startChooser()
        }
    }

}