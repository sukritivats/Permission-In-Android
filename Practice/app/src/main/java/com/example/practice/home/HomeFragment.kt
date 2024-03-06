package com.example.practice.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.practice.R
import com.example.practice.databinding.FragmentHomeContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var drawer:DrawerLayout
    private lateinit var binding: FragmentHomeContentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Home"
        binding = FragmentHomeContentBinding.inflate(inflater, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMbs.setOnClickListener(){
//            findNavController().navigate(R.id.action_homeFragment_to_fragmentApp1)
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.modal_bottom_sheet, null)
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        }

        binding.btnPbs.setOnClickListener(){
//            findNavController().navigate(R.id.action_homeFragment_to_fragmentApp1)
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.modal_bottom_sheet, null)
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        }
        binding.btnDrawer.setOnClickListener(){
            openDrawer()
        }
    }

    private fun openDrawer() {
        try {
            drawer = requireActivity().findViewById(R.id.drawerLayout)
            drawer.openDrawer(GravityCompat.START)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DDD","DDD")
        }
    }
}