package com.dicoding.movies.ListTv

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
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.databinding.FragmentTvBinding
import com.dicoding.movies.viewmodel.ViewModelFactory
import com.dicoding.movies.vo.Status


class TvFragment : Fragment(), TvFragmentCallback {
    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false )

        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val tvAdapter = TvAdapter(this)

            tvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TV, selectedData)
                startActivity(intent)

            }

            viewModel.tvShows.observe(this, { tv ->
                if (tv != null){
                    when (tv.status) {
                        Status.LOADING -> fragmentTvBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            tvAdapter.submitList(tv.data)
                        }
                        Status.ERROR -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvBinding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onShareClick(tvShows : TvShows){
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