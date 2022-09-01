package com.piroworkz.bluelabstmdbapp.app.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.piroworkz.bluelabstmdbapp.R

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var ratio: Float = DEFAULT_RATIO

    init {
        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView)
            with(a) {
                ratio = getFloat(R.styleable.CustomImageView_ratio, DEFAULT_RATIO)
                recycle()
            }
        }
    }

    fun setRatio(ratio: Float){
        this.ratio = ratio
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if (width > 0) {
            height = (width * ratio).toInt()
        } else {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }

    companion object {
        const val DEFAULT_RATIO = 1F
    }
}