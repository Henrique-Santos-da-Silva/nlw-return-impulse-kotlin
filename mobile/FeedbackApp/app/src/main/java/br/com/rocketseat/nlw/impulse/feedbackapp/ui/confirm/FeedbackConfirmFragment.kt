package br.com.rocketseat.nlw.impulse.feedbackapp.ui.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rocketseat.nlw.impulse.feedbackapp.databinding.FragmentFeedbackConfirmBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FeedbackConfirmFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFeedbackConfirmBinding? = null
    private val binding: FragmentFeedbackConfirmBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFeedbackConfirmBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.btnSendOtherFeedback?.setOnClickListener {
            findNavController().navigate(FeedbackConfirmFragmentDirections.actionFeedbackConfirmFragmentToFeedbackMainFragment())
        }
    }
}