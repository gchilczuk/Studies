package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.movie_list_row_left.view.*
import kotlinx.android.synthetic.main.movie_list_row_right.view.*
import org.w3c.dom.Text

abstract class MovieViewHolder(view :View) : RecyclerView.ViewHolder(view){
    var movieTitle :TextView? = null
    var movieGenre :TextView? = null
    var movieYear :TextView? = null
    var movieImage :ImageView? = null

}
class MovieViewHolderLeft(view :View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleL
        movieGenre = view.TVMgenreL
        movieYear = view.TVMyearL
        movieImage = view.MimgL
    }
}

class MovieViewHolderRight(view :View) : MovieViewHolder(view) {
    init {
        movieTitle = view.TVMtitleR
        movieGenre = view.TVMgenreR
        movieYear = view.TVMyearR
        movieImage = view.MimgR
    }
}