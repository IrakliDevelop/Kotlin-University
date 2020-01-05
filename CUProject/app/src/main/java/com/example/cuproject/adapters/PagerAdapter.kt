package com.example.cuproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cuproject.dto.movie.Movie
import com.example.cuproject.fragments.CastFragment
import com.example.cuproject.fragments.InfoFragment

class PagerAdapter(fm: FragmentManager, private val movie: Movie): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InfoFragment.newInstance(movie)
            else -> CastFragment.newInstance(movie)
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "INFO"
            else -> "CAST"
        }
    }
}