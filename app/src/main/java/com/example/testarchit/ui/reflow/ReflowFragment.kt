package com.example.testarchit.ui.reflow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testarchit.databinding.FragmentReflowBinding

class ReflowFragment : Fragment() {
    private var _binding: FragmentReflowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reflowViewModel =
            ViewModelProvider(this).get(ReflowViewModel::class.java)

        _binding = FragmentReflowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReflow
        reflowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //https://wsa.jianshu.io/p/dd3a9323b81a
        //https://developer.android.google.cn/develop/connectivity/network-ops/connecting?hl=zh-cn
        //https://developer.android.google.cn/kotlin/coroutines?hl=zh-cn
        textView.setOnClickListener {
            reflowViewModel.getConnectResponse()
        }
        reflowViewModel.resultResponse.observe(viewLifecycleOwner) {
            Log.i(TAG, "onSuccessResponse-code=" + it.resultCode + " msg=" + it.message)
            if (it.resultCode == "0") {
                // 处理成功响应
                //handleSuccessResponse(result)
            } else {
                // 处理失败响应
                //handleFailureResponse(result)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ReflowFragment"
    }
}