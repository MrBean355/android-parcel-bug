package com.github.mrbean355.android.parcelbug

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize

private const val KEY_INNER_MODEL = "INNER_MODEL"
private const val KEY_OUTER_MODEL = "OUTER_MODEL"
private const val KEY_NESTED_INTENT = "NESTED_INTENT"

class TestActivity : AppCompatActivity(R.layout.activity_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lineOne = findViewById<TextView>(R.id.line_one)
        val lineTwo = findViewById<TextView>(R.id.line_two)
        val lineThree = findViewById<TextView>(R.id.line_three)

        val outerModel = intent.getParcelableExtra<TestModel>(KEY_OUTER_MODEL)
        lineOne.text = outerModel.toString()

        val nestedIntent = intent.getParcelableExtra<Intent>(KEY_NESTED_INTENT)
        lineTwo.text = nestedIntent.toString()

        runCatching { nestedIntent?.getParcelableExtra<TestModel>(KEY_INNER_MODEL) }
            .onSuccess { lineThree.text = it.toString() }
            .onFailure {
                lineThree.text = it.toString()
                Log.e(TestActivity::class.java.simpleName, "Error getting inner model", it)
            }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TestActivity::class.java)
                .putExtra(KEY_OUTER_MODEL, TestModel())
                .putExtra(KEY_NESTED_INTENT, Intent().putExtra(KEY_INNER_MODEL, TestModel()))
        }
    }
}

@Parcelize
class TestModel : Parcelable