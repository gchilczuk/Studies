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
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    val FILM_DETAIL = 1
    private val movieList = mutableListOf<Movie>()
    private var mAdapter: MoviesAdapter? = null


    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val pos = viewHolder.adapterPosition
            movieList.removeAt(pos)
            mAdapter!!.notifyDataSetChanged()
        }
    }
    var itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)



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
                val intent = Intent(applicationContext, FilmDetail::class.java)
                intent.putExtra("position", position)
                intent.putExtra("movies", movieList as Serializable)
                startActivityForResult(intent, FILM_DETAIL)
            }

            override fun onLongClick(view: View, position: Int) {
                movieList[position].watched = !movieList[position].watched
                mAdapter?.notifyDataSetChanged()
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
            toast("MA: Są extrasy!")
        } else {
            movieList.addAll(loadMovieList())
//            toast("fail odczytu onCreate $wasMoviesSaved")
        }
        itemTouchHelper.attachToRecyclerView(RVmovielist);
    }


    override fun onSaveInstanceState(outState: Bundle?, persistableBundle: PersistableBundle) {
        super.onSaveInstanceState(outState)
        toast("OLABOGA persistantBundle!!!")
        outState?.putSerializable("movies", movieList as Serializable)
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("movies", movieList as Serializable)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        toast("MA: onActRes with Cod = "+requestCode+", "+resultCode)
        if (requestCode == FILM_DETAIL){
            if (resultCode == Activity.RESULT_OK){
                toast("MA: I'm OK!")
                movieList.clear()
                movieList.addAll(data!!.extras.getSerializable("movies") as MutableList<Movie>)
                toast("MA: oAR "+movieList[1].rating+"<< rat|wat >>"+movieList[1].watched)
            }
        }
        mAdapter?.notifyDataSetChanged()
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