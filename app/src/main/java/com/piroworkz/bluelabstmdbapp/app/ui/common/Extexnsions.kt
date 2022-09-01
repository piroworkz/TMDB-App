package com.piroworkz.bluelabstmdbapp.app.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collectFlow(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@collectFlow.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

inline fun <T : Any> diffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun ViewGroup.inflate(layout: Int, attachedToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(layout, this, attachedToRoot)