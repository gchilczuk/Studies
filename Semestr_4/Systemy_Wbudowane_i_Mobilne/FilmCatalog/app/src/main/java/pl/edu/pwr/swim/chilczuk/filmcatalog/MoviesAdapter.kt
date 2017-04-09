package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.R.attr.button
import android.text.Layout
import android.view.Gravity
import android.widget.RelativeLayout



class MoviesAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    var odd = false

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var year: TextView
        var genre: TextView
        var image: ImageView
//
        init {
            title = view.findViewById(R.id.TVMtitle) as TextView
            genre = view.findViewById(R.id.TVMgenre) as TextView
            year = view.findViewById(R.id.TVMyear) as TextView
            image = view.findViewById(R.id.Mimg) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.title.text = movie.title
        holder.genre.text = movie.genre
        holder.year.text = movie.year
        holder.image.setImageResource(movie.imgID)

        if (odd) oddPosition(holder)
        else evenPosition(holder)
//        evenPosition(holder)

        odd = !odd
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun evenPosition(holder : MyViewHolder){
        val imgParam = holder.image.layoutParams as RelativeLayout.LayoutParams
        val yearParam = holder.year.layoutParams as RelativeLayout.LayoutParams

        imgParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        imgParam.addRule(RelativeLayout.ALIGN_PARENT_END)
        holder.image.setLayoutParams(imgParam)

        yearParam.addRule(RelativeLayout.LEFT_OF, R.id.Mimg)
        holder.year.layoutParams = yearParam
    }

    fun oddPosition(holder : MyViewHolder){
        val imgParam = holder.image.layoutParams as RelativeLayout.LayoutParams
        val yearParam = holder.year.layoutParams as RelativeLayout.LayoutParams
        val genreParam = holder.genre.layoutParams as RelativeLayout.LayoutParams
        val titleParam = holder.title.layoutParams as RelativeLayout.LayoutParams

        imgParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        imgParam.addRule(RelativeLayout.ALIGN_PARENT_START)

        yearParam.addRule(RelativeLayout.ALIGN_PARENT_END)
        yearParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)

        genreParam.addRule(RelativeLayout.RIGHT_OF, R.id.Mimg)

        titleParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        titleParam.addRule(RelativeLayout.ALIGN_PARENT_END)
//        titleParam.addRule(RelativeLayout.TEXT_ALIGNMENT_GRAVITY)
//        titleParam.addRule(RelativeLayout.TEXT_ALIGNMENT_VIEW_END)
//        titleParam.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER)
//        titleParam.addRule(Gravity.END)
//        titleParam.addRule(RelativeLayout.GR)

        holder.image.setLayoutParams(imgParam)
        holder.year.layoutParams = yearParam
        holder.genre.layoutParams = genreParam
        holder.title.layoutParams = titleParam
    }
}