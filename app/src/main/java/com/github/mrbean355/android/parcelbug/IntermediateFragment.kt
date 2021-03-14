package com.github.mrbean355.android.parcelbug

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

private const val KEY_DESTINATION = "DESTINATION"

class IntermediateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startActivityForResult(requireArguments().getParcelable(KEY_DESTINATION), 321)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 321) {
            activity?.finish()
        }
    }

    companion object {
        fun newInstance(destination: Intent?): IntermediateFragment =
            IntermediateFragment().apply {
                arguments = bundleOf(KEY_DESTINATION to destination)
            }
    }
}