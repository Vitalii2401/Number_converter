package com.example.myapplication.utility

import android.view.View
import androidx.constraintlayout.widget.Group

fun Group.setAllOnClickListener(listener: (View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}