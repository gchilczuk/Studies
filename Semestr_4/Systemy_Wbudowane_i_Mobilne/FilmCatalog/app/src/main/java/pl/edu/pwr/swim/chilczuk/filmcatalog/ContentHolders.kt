package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.movie_list_row_even.view.*
import kotlinx.android.synthetic.main.movie_list_row_odd.view.*
import kotlinx.android.synthetic.main.actor_row.view.*

class Movie(val title: String,
            val genre: String,
            val year: String,
            val imgID: Int = R.drawable.oczu,
            val description: String = "Lorem ipsum dolor sit amet",
            val starring: List<Actor> = listOf(),
            val moreimgs: List<Int> = listOf(),
            var rating: Float = 0f,
            var watched: Boolean = false)

class Actor(val name: String,
            val surname: String,
            val imgID: Int = R.drawable.oczu)



abstract class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
    var movieTitle : TextView? = null
    var movieGenre : TextView? = null
    var movieYear : TextView? = null
    var movieImage : ImageView? = null
    var movieSeen : ImageView? = null

}
class MovieViewHolderEven(view : View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleE
        movieGenre = view.TVMgenreE
        movieYear = view.TVMyearE
        movieImage = view.MimgE
        movieSeen = view.IVseenE
    }
}

class MovieViewHolderOdd(view : View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleO
        movieGenre = view.TVMgenreO
        movieYear = view.TVMyearO
        movieImage = view.MimgO
        movieSeen = view.IVseenO
    }
}

class ActorHolder(view : View) : RecyclerView.ViewHolder(view){
    val name : TextView = view.actorNameTV
    val surname : TextView = view.actorSurnameTV
    val image : ImageView = view.actorImgV
}
