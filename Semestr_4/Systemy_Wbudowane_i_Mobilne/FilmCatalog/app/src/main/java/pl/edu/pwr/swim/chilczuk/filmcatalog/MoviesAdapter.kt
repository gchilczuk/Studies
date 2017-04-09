package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView


class MoviesAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var year: TextView
        var genre: TextView
        var image: ImageView
        var done = false

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

        println("–————————————————————————————————————————————————————————————————————")
        println("–————————————————————————————— $position ————————————————————————————————————")
        println("–————————————————————————————————————————————————————————————————————")
        if (!holder.done) {
            if (position % 2 != 0) { oddPosition(holder) }
            else { evenPosition(holder) }
            holder.done = true
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun evenPosition(holder: MyViewHolder) {
        val imgParam = holder.image.layoutParams as RelativeLayout.LayoutParams
        val yearParam = holder.year.layoutParams as RelativeLayout.LayoutParams
        val genreParam = holder.genre.layoutParams as RelativeLayout.LayoutParams
        val titleParam = holder.title.layoutParams as RelativeLayout.LayoutParams

        imgParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        imgParam.addRule(RelativeLayout.ALIGN_PARENT_END)

//        imgParam.addRule(RelativeLayout.RIGHT_OF, R.id.TVMtitle)
        holder.image.layoutParams = imgParam

        yearParam.addRule(RelativeLayout.LEFT_OF, R.id.Mimg)
        holder.year.layoutParams = yearParam

        genreParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        genreParam.addRule(RelativeLayout.ALIGN_PARENT_START)
        holder.genre.layoutParams = genreParam
//
//
        titleParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        titleParam.addRule(RelativeLayout.ALIGN_PARENT_START)
        titleParam.addRule(RelativeLayout.LEFT_OF, R.id.Mimg)
        holder.title.layoutParams = titleParam

    }

    fun oddPosition(holder: MyViewHolder) {
        val imgParam = holder.image.layoutParams as RelativeLayout.LayoutParams
        val yearParam = holder.year.layoutParams as RelativeLayout.LayoutParams
        val genreParam = holder.genre.layoutParams as RelativeLayout.LayoutParams
        val titleParam = holder.title.layoutParams as RelativeLayout.LayoutParams

        imgParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        imgParam.addRule(RelativeLayout.ALIGN_PARENT_START)
//        imgParam.addRule(RelativeLayout.LEFT_OF, R.id.TVMtitle)
        holder.image.layoutParams = imgParam
//
        yearParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        yearParam.addRule(RelativeLayout.ALIGN_PARENT_END)
        holder.year.layoutParams = yearParam

        genreParam.addRule(RelativeLayout.RIGHT_OF, R.id.Mimg)
        holder.genre.layoutParams = genreParam

        titleParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        titleParam.addRule(RelativeLayout.ALIGN_PARENT_END)
        titleParam.addRule(RelativeLayout.RIGHT_OF, R.id.Mimg)
        holder.title.layoutParams = titleParam

    }
}