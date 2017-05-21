package pl.edu.pwr.swim.chilczuk.filmcatalog

import java.io.Serializable

class Movie(val title: String,
            val genre: String,
            val year: String,
            val imgID: Int = R.drawable.oczu,
            var rating: Float = 0f,
            var watched: Boolean = false) : Serializable