package com.github.mrbean355.android.parcelbug

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestModel(
    val value: Int
) : Parcelable