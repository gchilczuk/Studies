package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_film_detail.*
import java.util.ArrayList

class FilmDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title:String
        val movie:Movie
        setContentView(R.layout.activity_film_detail)
        filmImage.setImageResource(R.drawable.oczu)
        if (savedInstanceState == null){
            val extras = intent.extras
            if (extras == null){
                title = "Such error"
                movie = Movie("suchError", "suchError", "suchError", 0)
            } else {
                title = extras.getString("title")
                movie = extras.getSerializable("movie") as Movie
            }
        } else {
            title = savedInstanceState.getString("title")
            movie = savedInstanceState.getSerializable("movie") as Movie
        }
        filmTitle.text = movie.title
        if (movie.rating != null) {
            ratingBar.rating = movie.rating as Float
        }
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> movie.rating = rating
        filmTitle.text = rating.toString() + " | " + ratingBar.rating.toString() }

    }


}

