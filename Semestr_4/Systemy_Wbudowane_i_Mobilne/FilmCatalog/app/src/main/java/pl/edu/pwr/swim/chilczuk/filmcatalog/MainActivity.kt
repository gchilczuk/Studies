package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    val FILM_DETAIL = 0
    private val movieList = mutableListOf<Movie>()
    private var mAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mAdapter = MoviesAdapter(movieList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        RVmovielist.layoutManager = mLayoutManager
        RVmovielist.itemAnimator = DefaultItemAnimator()
        val divider = DividerItemDecoration(RVmovielist.context, mLayoutManager.orientation)
        RVmovielist.addItemDecoration(divider)
        RVmovielist.adapter = mAdapter

        RVmovielist.addOnItemTouchListener(RecyclerTouchListener(applicationContext, RVmovielist, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                val movie = movieList[position]
                toast(movie.title + " is selected!")
                val intent = Intent(applicationContext, FilmDetail::class.java)
                intent.putExtra("position", position)
                intent.putExtra("movies", movieList as Serializable)
//                intent.putExtra("position",position)
                startActivityForResult(intent, FILM_DETAIL)
            }

            override fun onLongClick(view: View, position: Int) {
                movieList[position].watched = !movieList[position].watched
                mAdapter?.notifyDataSetChanged()
                toast("LONG")
            }

            override fun onFun(view: View, position: Int) {
                toast("FUN :)")
            }
        }))
        val wasMoviesSaved = savedInstanceState?.containsKey("movies")
        if (wasMoviesSaved != null && wasMoviesSaved){
            movieList.addAll(savedInstanceState?.getSerializable("movies") as MutableList<Movie>)
        } else if (intent.extras != null){
            movieList.addAll(intent.extras.getSerializable("movies") as MutableList<Movie>)
            toast("Są extrasy!")
        } else {
            toast("Nie ma extrasów!")
            movieList.addAll(loadMovieList())
//            toast("fail odczytu onCreate $wasMoviesSaved")
        }
    }


    override fun onSaveInstanceState(outState: Bundle?, persistableBundle: PersistableBundle) {
        super.onSaveInstanceState(outState)
        toast("OLABOGA persistantBundle!!!")
        outState?.putSerializable("movies", movieList as Serializable)
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("movies", movieList as Serializable)
        toast("zamykamy kram")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        toast(""+requestCode)
        if (requestCode == FILM_DETAIL){
            if (resultCode == Activity.RESULT_OK){
                movieList.clear()
                movieList.addAll(data!!.extras.getSerializable("movies") as MutableList<Movie>)
                toast("oAR"+movieList[1].rating)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            toast("Not implemented")
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}


fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()