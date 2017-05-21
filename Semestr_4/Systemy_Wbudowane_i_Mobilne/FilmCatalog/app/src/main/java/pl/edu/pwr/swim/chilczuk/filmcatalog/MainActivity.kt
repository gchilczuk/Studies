package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
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


class MainActivity : AppCompatActivity() {
    private val movieList = MoviesKeeper.movieList
    private var mAdapter: MoviesAdapter? = null


    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

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
                MoviesKeeper.currentPosition = position
                startActivity(intent)
            }

            override fun onLongClick(view: View, position: Int) {
                movieList[position].watched = !movieList[position].watched
                mAdapter?.notifyDataSetChanged()
            }
        }))
        itemTouchHelper.attachToRecyclerView(RVmovielist);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            toast("Not implemented")
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}


fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()