package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.movie_list_row_even.view.*
import kotlinx.android.synthetic.main.movie_list_row_odd.view.*

abstract class MovieViewHolder(view :View) : RecyclerView.ViewHolder(view){
    var movieTitle :TextView? = null
    var movieGenre :TextView? = null
    var movieYear :TextView? = null
    var movieImage :ImageView? = null
    var movieSeen :ImageView? = null

}
class MovieViewHolderEven(view :View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleE
        movieGenre = view.TVMgenreE
        movieYear = view.TVMyearE
        movieImage = view.MimgE
        movieSeen = view.IVseenE
    }
}

class MovieViewHolderOdd(view :View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleO
        movieGenre = view.TVMgenreO
        movieYear = view.TVMyearO
        movieImage = view.MimgO
        movieSeen = view.IVseenO
    }
}

