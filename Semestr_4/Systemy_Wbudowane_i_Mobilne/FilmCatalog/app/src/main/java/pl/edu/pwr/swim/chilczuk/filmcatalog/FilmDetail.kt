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
    var allmovies : MutableList<Movie> = mutableListOf()
    var position:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        val movie : Movie
        if (savedInstanceState == null){
            val extras = intent.extras
            if (extras == null){

            } else {
                allmovies.addAll(extras.getSerializable("movies") as MutableList<Movie>)
                position = extras.getInt("position")
            }
        } else {
            allmovies.addAll(savedInstanceState.getSerializable("movies") as MutableList<Movie>)
            position = savedInstanceState.getInt("position")
            toast("FD: SAVED is NOT NULL")
        }
        if (position == -1){
            movie = Movie("suchError", "suchError", "suchError", 0)
        } else movie = allmovies[position]


        filmImage.setImageResource(movie.imgID)
        filmTitle.text = movie.title
        ratingBar.rating = movie.rating
        toast("FD: rB.r = "+ratingBar.rating + "and mo.r = "+movie.rating + " + wat = "+movie.watched)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> allmovies[position].rating = rating }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        toast("OLABOGA persistantBundle!!!")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable("movies", allmovies as Serializable)
        outState?.putInt("position", position)
        toast("FD: oSIS zwykły Bundle!!!")
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.putExtra("movies", allmovies as Serializable)
        setResult(Activity.RESULT_OK, intent)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        val intent = Intent()
        intent.putExtra("movies", allmovies as Serializable)
        toast("FD: oP: "+allmovies[1].rating +" ←rat|wat→ "+ allmovies[1].watched)
        setResult(Activity.RESULT_OK, intent)
        super.onPause()

    }


}

