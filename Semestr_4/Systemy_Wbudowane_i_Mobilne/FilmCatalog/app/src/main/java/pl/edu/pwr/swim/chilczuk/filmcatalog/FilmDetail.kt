package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_film_detail.*


class DetailsFragmentClass: Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_film_detail, container, false)
    }
}

class FilmDetail : AppCompatActivity() {
    private val movieList = MoviesKeeper.movieList
    var position:Int = MoviesKeeper.currentPosition


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_film_detail)
        val movie : Movie
        movie = movieList[position]

        filmImage.setImageResource(movie.imgID)
        filmTitle.text = movie.title
        ratingBar.rating = movie.rating
        filmDescription.text = movie.description
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> movie.rating = rating }
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(view.context, More::class.java)
                view.context.startActivity(intent)
            }
        })
    }
}
