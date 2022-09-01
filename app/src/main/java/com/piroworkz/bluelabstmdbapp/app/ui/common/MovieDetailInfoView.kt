package com.piroworkz.bluelabstmdbapp.app.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.piroworkz.bluelabstmdbapp.domain.Movie

class MovieDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setMovie(movie: Movie) = movie.apply {
        text = buildSpannedString {

            bold { append("Título: ") }
            appendLine(title)

            bold { append("Reseña: ") }
            appendLine(overview)

            bold { append("Votación promedio: ") }
            appendLine(voteAverage)


        }
    }
}