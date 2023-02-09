package com.example.myapplication.ui.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getCustomTitle(): Int
}