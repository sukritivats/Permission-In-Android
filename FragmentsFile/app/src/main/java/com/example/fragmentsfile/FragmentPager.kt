//package com.example.fragmentsfile
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//
//class FragmentPager(val fragmentPager: FragmentManager) :
//    FragmentStatePagerAdapter(fragmentPager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    private val listOfFragment: ArrayList<Fragment> = ArrayList()
//
//    fun setFragments(list: List<Fragment>) {
//        listOfFragment.clear()
//        listOfFragment.addAll(list)
//    }
//
//
//    override fun getCount(): Int {
//        return listOfFragment.size
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return listOfFragment[position]
//    }
//
//}