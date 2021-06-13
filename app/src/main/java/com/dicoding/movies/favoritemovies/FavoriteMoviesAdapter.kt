package com.dicoding.movies.favoritemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movies.ListMovies.MoviesAdapter
import com.dicoding.movies.ListMovies.MoviesFragmentCallback
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.databinding.ItemMoviesBinding

class FavoriteMoviesAdapter(private val callback: MoviesFragmentCallback) : PagedListAdapter<Movies, FavoriteMoviesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    //private var listMovies = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null


    /*fun setMovies(movies: List<Movies>?){
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }*/

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>(){
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.moviesId == newItem.moviesId
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesAdapter.ListViewHolder {
        val moviesBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(moviesBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesAdapter.ListViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class ListViewHolder(private val binding: ItemMoviesBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                tvItemTitle.text = movies.title
                scoreDummy.text = itemView.resources.getString(R.string.score)
                tvItemScore.text = movies.score.toString()
                tvItemAired.text = movies.aired
                imgShare.setOnClickListener { callback.onShareClick(movies) }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + movies.poster)
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition)!!)
            }
        }
    }


}