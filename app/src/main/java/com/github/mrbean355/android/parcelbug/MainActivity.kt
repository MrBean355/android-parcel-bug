package com.github.mrbean355.android.parcelbug

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(R.id.test).setOnClickListener {
            val destination = DestinationActivity.createIntent(this, TestModel(999))
            val intermediate = IntermediateActivity.createIntent(this, destination)
            startActivity(intermediate)
        }
    }
}