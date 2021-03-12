package com.github.mrbean355.android.parcelbug

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val KEY_TEST_MODEL = "TEST_MODEL"

class DestinationActivity : AppCompatActivity(R.layout.activity_destination) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.result).text = intent.getParcelableExtra<TestModel>(KEY_TEST_MODEL).toString()
    }

    companion object {

        fun createIntent(context: Context, testModel: TestModel): Intent =
            Intent(context, DestinationActivity::class.java)
                .putExtra(KEY_TEST_MODEL, testModel)
    }
}