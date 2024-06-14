package com.example.testarchit.ui.slideshow

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testarchit.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("SlideshowFragment", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        val recyclerViewShow = binding.rvShow
        val adapterShow = TestShowAdapter()
        recyclerViewShow.adapter = adapterShow
        slideshowViewModel.texts.observe(viewLifecycleOwner) {
            adapterShow.submitList(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i("SlideshowFragment", "onDestroyView")
    }

    override fun onDestroy() {
        Log.i("SlideshowFragment", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("SlideshowFragment", "onDetach")
        super.onDetach()
    }
}