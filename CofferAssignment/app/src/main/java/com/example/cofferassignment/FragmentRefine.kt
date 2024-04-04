package com.example.cofferassignment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cofferassignment.databinding.FragmentRefineBinding

class FragmentRefine : Fragment() {

    private var binding: FragmentRefineBinding? = null
    private var isPurposeSelected = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRefineBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setupStatusEditText()
        setupSeekBar()
        setupClickListeners()
    }

    private fun setupSpinner() {
        val options = arrayOf(
            "Available | Hey Let Us Connect",
            "Away | Stay Discrete And Watch",
            "Busy | Do Not disturb | Will Catch Up Later",
            "SOS | Emergency ! Need Assistance! HELP"
        )
        val adapter = CustomSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            options
        )
        binding?.spinner?.adapter = adapter
    }
    class CustomSpinnerAdapter(
        context: Context,
        resource: Int,
        objects: Array<String>
    ) : ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f) // Set text size to 13sp
            return view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getDropDownView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f) // Set text size to 13sp
            return view
        }
    }

    private fun setupStatusEditText() {
        binding?.etStatus?.filters = arrayOf(android.text.InputFilter.LengthFilter(250))
        binding?.etStatus?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val currentLength = s?.length ?: 0
                binding?.tvCount?.text = "$currentLength/250"

                val textHeight = binding?.etStatus?.let {
                    val layout = it.layout
                    val textHeight = layout?.getLineBottom(layout.lineCount - 1) ?: 0
                    textHeight + it.compoundPaddingTop + it.compoundPaddingBottom
                } ?: 0

                val layoutParams = binding?.etStatus?.layoutParams
                layoutParams?.height = textHeight
                binding?.etStatus?.layoutParams = layoutParams
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupSeekBar() {
        binding?.seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding?.textView?.text = "$progress"

                val thumb = binding?.seekBar?.thumb
                val thumbRect = thumb?.bounds
                thumbRect?.let {
                    val thumbX = thumbRect.centerX()
                    val thumbY = thumbRect.centerY()

                    val layoutParams = binding?.textView?.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.topMargin = thumbY - (binding?.textView?.height ?: 0) / 2
                    layoutParams.leftMargin = thumbX - (binding?.textView?.width ?: 0) / 2
                    binding?.textView?.layoutParams = layoutParams
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupClickListeners() {
        listOf(
            binding?.tvCoffee,
            binding?.tvBusiness,
            binding?.tvHobbies,
            binding?.tvFriendship,
            binding?.tvMovies,
            binding?.tvDinning,
            binding?.tvDating,
            binding?.tvMatrimony
        ).forEach { textView ->
            textView?.setOnClickListener {
                isPurposeSelected = !isPurposeSelected
                togglePurposeSelection(textView)
            }
        }

        binding?.btnSaveExplore?.setOnClickListener {
            findNavController().navigate(R.id.fragmentExplore)
        }
        binding?.ivPrev?.setOnClickListener {
            findNavController().navigate(R.id.fragmentExplore)
        }
    }

    private fun togglePurposeSelection(textView: TextView?) {
        textView?.let {
            if (isPurposeSelected) {
                it.setBackgroundResource(R.drawable.bg_selected)
                it.setTextColor(Color.WHITE)
            } else {
                it.setBackgroundResource(R.drawable.bg_purpose)
                it.setTextColor(Color.parseColor("#181E43"))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideActionBarAndBottomNavigation()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as MainActivity).showActionBarAndBottomNavigation()
    }
}
