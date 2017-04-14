package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.security.InvalidAlgorithmParameterException


class MoviesAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    val EVEN = 0
    val ODD = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder : MovieViewHolder
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            EVEN -> {
                val v1 = inflater.inflate(R.layout.movie_list_row_even, parent, false)
                viewHolder = MovieViewHolderEven(v1)
            }
            ODD -> {
                val v2 = inflater.inflate(R.layout.movie_list_row_odd, parent, false)
                viewHolder = MovieViewHolderOdd(v2)
            }
            else -> {
                throw InvalidAlgorithmParameterException("Only '0' and '1'; given: "+viewType)
            }
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.movieTitle?.text = movie.title
        holder.movieGenre?.text = movie.genre
        holder.movieYear?.text = movie.year
        holder.movieImage?.setImageResource(movie.imgID)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}