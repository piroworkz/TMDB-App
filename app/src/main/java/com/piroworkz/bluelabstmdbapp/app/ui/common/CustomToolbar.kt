package com.piroworkz.bluelabstmdbapp.app.ui.common

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.MaterialToolbar
import com.piroworkz.bluelabstmdbapp.R

class CustomToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialToolbar(context, attrs) {

    override fun setNavigationIconTint(navigationIconTint: Int) {
        super.setNavigationIconTint(context.getColor(R.color.white))
    }

}