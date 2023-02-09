package com.example.myapplication.ui.contract

import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

interface HasCustomMenu {

    fun getCustomMenu(): CustomMenu
}

data class CustomMenu(
    val provider: MenuProvider,
    val owner: LifecycleOwner,
    val state: Lifecycle.State
)