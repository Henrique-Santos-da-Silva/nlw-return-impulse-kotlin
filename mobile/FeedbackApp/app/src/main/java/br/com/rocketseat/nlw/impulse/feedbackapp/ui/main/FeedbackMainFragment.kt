package br.com.rocketseat.nlw.impulse.feedbackapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rocketseat.nlw.impulse.feedbackapp.R
import br.com.rocketseat.nlw.impulse.feedbackapp.databinding.FragmentFeedbackMainBinding
import br.com.rocketseat.nlw.impulse.feedbackapp.ui.adapters.FeedbackMainCardAdapter
import br.com.rocketseat.nlw.impulse.feedbackapp.ui.ui_utils.FeedbackCardItems
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

class FeedbackMainFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFeedbackMainBinding? = null
    private val binding: FragmentFeedbackMainBinding? get() = _binding

    private val feedbackCardAdapter: FeedbackMainCardAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFeedbackMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedbackCardSetup()
    }

    private fun feedbackCardSetup() {
        val cards: MutableList<FeedbackCardItems> = mutableListOf(
            FeedbackCardItems(R.drawable.bug, getString(R.string.issue)),
            FeedbackCardItems(R.drawable.idea, getString(R.string.idea)),
            FeedbackCardItems(R.drawable.thought, getString(R.string.other)),
        )

        binding?.rvFeedbacksCards?.adapter = feedbackCardAdapter
        binding?.rvFeedbacksCards?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        feedbackCardAdapter.submitList(cards)

        feedbackCardAdapter.onItemClickListener = { cardsItemsView ->
            val feedbackMainDirections: NavDirections = FeedbackMainFragmentDirections.actionFeedbackMainFragmentToFeedbackDetailsFragment(cardsItemsView)
            findNavController().navigate(feedbackMainDirections)
        }
    }
}