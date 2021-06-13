package com.dicoding.movies.favoritetvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movies.ListTv.TvAdapter
import com.dicoding.movies.ListTv.TvFragmentCallback
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.databinding.ItemTvBinding

class FavoriteTvShowAdapter(private val callback: TvFragmentCallback) : PagedListAdapter<TvShows, FavoriteTvShowAdapter.ListViewHolder>(DIFF_CALLBACK) {
    //private var listTv = ArrayList<TvShows>()
    var onItemClick: ((TvShows) -> Unit)? = null

    /*fun setTvShows(tv: List<TvShows>?){
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }*/

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShows>(){
            override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvShowAdapter.ListViewHolder {
        val itemTvBinding = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.ListViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }


    inner class ListViewHolder(private val binding: ItemTvBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(tv: TvShows){
            with(binding) {
                tvItemTitle.text = tv.title
                scoreDummy.text = itemView.resources.getString(R.string.score)
                tvItemScore.text = tv.score.toString()
                tvItemAired.text = tv.aired
                imgShare.setOnClickListener { callback.onShareClick(tv) }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + tv.poster)
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