package com.dicoding.movies.ListMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.databinding.ItemMoviesBinding

class MoviesAdapter(private val callback: MoviesFragmentCallback) : PagedListAdapter<Movies, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Movies) -> Unit)? = null

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
    ): MoviesAdapter.MoviesViewHolder {
        val moviesBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(moviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }


    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movies){
            with(binding) {
                tvItemTitle.text = movies.title
                scoreDummy.text = itemView.resources.getString(R.string.score)
                tvItemScore.text = movies.score.toString()
                tvItemAired.text = movies.aired
                imgShare.setOnClickListener{callback.onShareClick(movies)}
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