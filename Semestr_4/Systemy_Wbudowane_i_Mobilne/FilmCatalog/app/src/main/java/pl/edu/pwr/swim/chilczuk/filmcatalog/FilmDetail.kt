package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_film_detail.*
import java.util.ArrayList

class FilmDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val movieList = savedInstanceState?.get("movieList") as ArrayList<Movie>
//        val position = savedInstanceState?.get("position") as Int

//        val film:Movie = movieList[position]
        setContentView(R.layout.activity_film_detail)
        filmImage.setImageResource(R.drawable.oczu)
    }
}
