package com.github.mrbean355.android.parcelbug

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

private const val KEY_DESTINATION = "DESTINATION"

class IntermediateActivity : AppCompatActivity(R.layout.activity_intermediate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = IntermediateFragment().apply {
            arguments = bundleOf(KEY_DESTINATION to intent.getParcelableExtra(KEY_DESTINATION))
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        fun createIntent(context: Context, destination: Intent): Intent =
            Intent(context, IntermediateActivity::class.java)
                .putExtra(KEY_DESTINATION, destination)
    }
}