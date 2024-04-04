package com.example.cofferassignment

import ViewModel
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cofferassignment.databinding.FragmentExploreBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentExplore : Fragment() {

    private var binding: FragmentExploreBinding? = null
    private var isExpanded = false
    private val viewModel: ViewModel by activityViewModels()


    private val fromBottomFabAnim:android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_fab)
    }
    private val toBottomFabAnim: android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_fab)
    }
    private val rotateClockWiseFabAnim: android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim)
    }
    private val rotateAntiClockWiseFabAnim: android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim)
    }
    private val fromBottomBgAnim: android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim)
    }
    private val toBottomBgAnim: android.view.animation.Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.setOnTouchListener { _, event ->
            if (event?.action == MotionEvent.ACTION_DOWN) {
                if (isExpanded) {
                    val outRect = Rect()
                    binding?.fabConstraint?.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        shrinkFab()
                        return@setOnTouchListener true
                    }
                }
            }
            return@setOnTouchListener false
        }
        viewModel.fabVisible.observe(viewLifecycleOwner) { isVisible ->
            if (isVisible) {
                binding?.mainFab?.show()
            } else {
                binding?.mainFab?.hide()
            }
        }
        setupViewPager()

        binding?.mainFab?.setOnClickListener {

            if (isExpanded) {
                shrinkFab()
            } else {
                expandFab()
            }

        }

    }

    private fun setupViewPager() {
        binding?.viewPager2?.adapter = MyPagerAdapter(this)
        binding?.tabLayout?.let {
            binding?.viewPager2?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = when (position) {
                        0 -> "Personal"
                        1 -> "Business"
                        2 -> "Merchant"
                        else -> throw IllegalArgumentException("Invalid position: $position")
                    }
                }.attach()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0, 1, 2 -> FragmentPersonal()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
    private fun shrinkFab() {

        binding?.viewPager2?.startAnimation(toBottomBgAnim)

        binding?.mainFab?.startAnimation(rotateAntiClockWiseFabAnim)
        binding?.fabNotes?.startAnimation(toBottomFabAnim)
        binding?.fabJobs?.startAnimation(toBottomFabAnim)
        binding?.fabNetclanGroups?.startAnimation(toBottomFabAnim)
        binding?.fabBusiness?.startAnimation(toBottomFabAnim)
        binding?.fabSell?.startAnimation(toBottomFabAnim)
        binding?.fabMatrimony?.startAnimation(toBottomFabAnim)
        binding?.fabDating?.startAnimation(toBottomFabAnim)

        binding?.tvfabNotes?.startAnimation(toBottomFabAnim)
        binding?.tvfabJobs?.startAnimation(toBottomFabAnim)
        binding?.tvfabNetclanGroups?.startAnimation(toBottomFabAnim)
        binding?.tvfabBusiness?.startAnimation(toBottomFabAnim)
        binding?.tvfabSell?.startAnimation(toBottomFabAnim)
        binding?.tvfabMatrimony?.startAnimation(toBottomFabAnim)
        binding?.tvfabDating?.startAnimation(toBottomFabAnim)

        isExpanded = !isExpanded
    }

    private fun expandFab() {

        binding?.viewPager2?.startAnimation(fromBottomBgAnim)

        binding?.mainFab?.startAnimation(rotateClockWiseFabAnim)
        binding?.fabNotes?.startAnimation(fromBottomFabAnim)
        binding?.fabJobs?.startAnimation(fromBottomFabAnim)
        binding?.fabNetclanGroups?.startAnimation(fromBottomFabAnim)
        binding?.fabBusiness?.startAnimation(fromBottomFabAnim)
        binding?.fabSell?.startAnimation(fromBottomFabAnim)
        binding?.fabMatrimony?.startAnimation(fromBottomFabAnim)
        binding?.fabDating?.startAnimation(fromBottomFabAnim)

        binding?.tvfabNotes?.startAnimation(fromBottomFabAnim)
        binding?.tvfabJobs?.startAnimation(fromBottomFabAnim)
        binding?.tvfabNetclanGroups?.startAnimation(fromBottomFabAnim)
        binding?.tvfabBusiness?.startAnimation(fromBottomFabAnim)
        binding?.tvfabSell?.startAnimation(fromBottomFabAnim)
        binding?.tvfabMatrimony?.startAnimation(fromBottomFabAnim)
        binding?.tvfabDating?.startAnimation(fromBottomFabAnim)

        isExpanded = !isExpanded
    }
}
