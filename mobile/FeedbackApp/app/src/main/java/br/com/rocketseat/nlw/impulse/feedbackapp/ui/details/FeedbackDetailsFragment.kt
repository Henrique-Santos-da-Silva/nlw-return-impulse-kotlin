package br.com.rocketseat.nlw.impulse.feedbackapp.ui.details

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nlw.impulse.feedbackapp.R
import br.com.rocketseat.nlw.impulse.feedbackapp.contracts.FeedbackApplicationContract
import br.com.rocketseat.nlw.impulse.feedbackapp.databinding.FragmentFeedbackDetailsBinding
import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import br.com.rocketseat.nlw.impulse.feedbackapp.presenters.feedback_details.FeedbackDetailsPresenter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class FeedbackDetailsFragment : BottomSheetDialogFragment(), FeedbackApplicationContract.View {
    private var _binding: FragmentFeedbackDetailsBinding? = null
    private val binding: FragmentFeedbackDetailsBinding? get() = _binding

    private val feedbackDetailsArgs: FeedbackDetailsFragmentArgs by navArgs()

    private val feedbackDetailsPresenter: FeedbackDetailsPresenter by inject { parametersOf(this)}

    private var savedPathFile: File? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFeedbackDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        binding?.let { fragmentDetailsFeedback ->
            with(fragmentDetailsFeedback) {
                imgFeedbackLogo.setImageDrawable(ContextCompat.getDrawable(requireContext(), feedbackDetailsArgs.feedbackCardInfoArgs.image))
                txtFeedbackTitle.text = feedbackDetailsArgs.feedbackCardInfoArgs.title
                inputValidation()

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnSendFeedback.setOnClickListener {
                    sendFeedback(txtFeedbackTitle.text.toString(), edtFeedbackDetail.text.toString())

                    findNavController().navigate(FeedbackDetailsFragmentDirections.actionFeedbackDetailsFragmentToFeedbackConfirmFragment())
                }

                imgFeedback.setOnClickListener { view ->
                    savedPathFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val file: File = feedbackDetailsPresenter.feedbackPixelCopyScreenshot(root, requireActivity())
                        file
                    } else {
                        val file: File = feedbackDetailsPresenter.feedbackDrawingCacheScreenShot(view)
                        file
                    }

                    imgRemoveScrenshot.visibility = View.VISIBLE
                }

                imgRemoveScrenshot.setOnClickListener {
                    if (imgFeedback.drawable != null)
                        imgFeedback.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_camera_outline))

                    deleteScreenShot()
                    imgRemoveScrenshot.visibility = View.GONE
                }
            }
        }
    }

    override fun setImageView(bitmap: Bitmap) {
        binding?.imgFeedback?.setImageBitmap(bitmap)
    }

    private fun inputValidation() {
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.btnSendFeedback?.isEnabled = s.toString().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding?.edtFeedbackDetail?.addTextChangedListener(textWatcher)
    }

    private fun sendFeedback(feedbackTitle: String, feedbackComment: String) {
        val screenShotByteArray: ByteArray = Files.readAllBytes(Paths.get(savedPathFile?.absolutePath))
        val screenShotBase64String: String = Base64.getEncoder().encodeToString(screenShotByteArray)

        val feedback = Feedback(feedbackTitle,  feedbackComment, savedPathFile?.absolutePath, screenShotBase64String)

        feedbackDetailsPresenter.sendFeedback(feedback)
    }

    private fun deleteScreenShot() {
        if (savedPathFile?.exists() == true) savedPathFile!!.delete()
    }
}
