package com.julkar.mymovie.presentation.util

import android.view.View

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface ListItemListener<T> {
    fun onClick(view: View, position: Int, data: T)
}