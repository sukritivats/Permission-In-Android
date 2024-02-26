package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class AppsFragment : Fragment() {
    lateinit var items:Array<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        items= arrayOf(
            R.drawable.pc6,
            R.drawable.pc1,
            R.drawable.pc3,
            R.drawable.pc4,
            R.drawable.pc5,
            R.drawable.pc2
        )
        val viewPager2: ViewPager2 = view.findViewById(R.id.view_pager_2)
        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)

        val adapter = AppsAdapterViewPager2(items)
        viewPager2.adapter = adapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(viewPager2)
        super.onViewCreated(view, savedInstanceState)
    }
}