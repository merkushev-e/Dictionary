package ru.gb.dictionary.view.searchdialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.gb.dictionary.databinding.FragmentSearchDialogBinding


class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    private var onSearchClickListener: OnSearchClickListener? = null

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (binding.searchEditText.text != null && !binding.searchEditText.text.toString()
                    .isEmpty()
            ) {
                binding.searchButtonTextview.isEnabled = true
                binding.clearTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchButtonTextview.isEnabled = false
                binding.clearTextImageview.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSearchDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButtonTextview.isEnabled = false
        }
    }

    interface OnSearchClickListener {

        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance() =
            SearchDialogFragment()
    }
}
