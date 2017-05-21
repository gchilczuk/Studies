package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_film_detail.*
import java.io.Serializable
import java.util.ArrayList

class FilmDetail : AppCompatActivity() {
    private val movieList = MoviesKeeper.movieList
    var position:Int = MoviesKeeper.currentPosition


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        val movie : Movie
        movie = movieList[position]

        filmImage.setImageResource(movie.imgID)
        filmTitle.text = movie.title
        ratingBar.rating = movie.rating
        filmDescription.text = movie.description
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> movie.rating = rating }
    }
}

