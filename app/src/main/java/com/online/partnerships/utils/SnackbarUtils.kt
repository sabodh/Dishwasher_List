package com.online.partnerships.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarUtils {

    fun createSnackbar(view: View, message: String): Snackbar {
        return Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    }
}