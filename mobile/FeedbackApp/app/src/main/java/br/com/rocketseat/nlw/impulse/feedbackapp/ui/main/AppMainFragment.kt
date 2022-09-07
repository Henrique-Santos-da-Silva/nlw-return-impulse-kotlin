package br.com.rocketseat.nlw.impulse.feedbackapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rocketseat.nlw.impulse.feedbackapp.databinding.FragmentAppMainBinding

class AppMainFragment : Fragment() {
    private var _binding: FragmentAppMainBinding? = null
    private val binding: FragmentAppMainBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fabFeedback?.setOnClickListener {
            findNavController().navigate(AppMainFragmentDirections.actionAppMainFragmentToFeedbackMainFragment())
        }
    }
}