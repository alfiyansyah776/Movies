package com.dicoding.movies.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.movies.R
import com.dicoding.movies.favoritemovies.FavoriteMoviesFragment
import com.dicoding.movies.favoritetvshow.FavoriteTvShowFragment

class SectionPageAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.Movie, R.string.Tv)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMoviesFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])


}