package com.example.practice.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.practice.R
import com.example.practice.databinding.FragmentAppsBinding

class AppsFragment : Fragment() {
    private var binding: FragmentAppsBinding? = null
    private val items: Array<Int> by lazy {
        arrayOf(
            R.drawable.pc6,
            R.drawable.pc1,
            R.drawable.pc3,
            R.drawable.pc4,
            R.drawable.pc5,
            R.drawable.pc2
        )
    }
    private val appList: ArrayList<AppsGridData> by lazy { arrayListOf() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AppsAdapterViewPager2(items)
        binding?.viewPager2?.adapter = adapter
        binding?.viewPager2?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding?.indicator?.setViewPager(binding?.viewPager2)

        addDataToList()
    }

    private fun addDataToList() {
        appList.add(AppsGridData(R.drawable.app1, "app1"))
        appList.add(AppsGridData(R.drawable.app2, "app2"))
        appList.add(AppsGridData(R.drawable.app3, "app3"))
        appList.add(AppsGridData(R.drawable.app4, "app4"))
        appList.add(AppsGridData(R.drawable.app5, "app5"))
        appList.add(AppsGridData(R.drawable.app6, "app6"))
        appList.add(AppsGridData(R.drawable.app7, "app7"))

        initRecycler()
    }

    private fun initRecycler() {
        binding?.grid?.adapter = AppsGridAdapter(appList)
    }
}