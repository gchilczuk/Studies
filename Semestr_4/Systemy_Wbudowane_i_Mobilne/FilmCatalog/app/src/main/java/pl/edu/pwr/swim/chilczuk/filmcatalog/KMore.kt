package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.app.Fragment
import android.app.ListFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.starring_fragment.*


class MoreStarring : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.starring_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState:Bundle? ) {
        super.onActivityCreated(savedInstanceState)
        val actorAdapter : ActorAdapter = ActorAdapter(MoviesKeeper.movieList[MoviesKeeper.currentPosition].starring)

        val mLayoutManager = LinearLayoutManager(activity.applicationContext)
        recycler_view2.layoutManager = mLayoutManager
        recycler_view2.itemAnimator = DefaultItemAnimator()

        val mDividerItemDecoration = DividerItemDecoration(recycler_view2.getContext(), mLayoutManager.orientation)
        recycler_view2.addItemDecoration(mDividerItemDecoration)
        recycler_view2.adapter = actorAdapter
        actorAdapter.notifyDataSetChanged()
    }
}


class MoreImg : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.more_imgs_fragment, container, false)
    }
}


class More : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kmore_activity)
    }
}
