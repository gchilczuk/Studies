package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView


class ActorAdapter(val actorList : List<Actor>) : RecyclerView.Adapter<ActorHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActorHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent?.context)
        val v = inflater.inflate(R.layout.actor_row, parent, false)
        val actorHolder : ActorHolder = ActorHolder(v)
        return actorHolder
    }

    override fun onBindViewHolder(holder: ActorHolder?, position: Int) {
        val actor = actorList[position]
        holder?.name?.text = actor.name
        holder?.surname?.text = actor.surname
        holder?.image?.setImageResource(actor.imgID)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

}

class ImgsAdapter(val context: Context, val imgsList:List<Int>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(225, 200)
            imageView.setAdjustViewBounds(true)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(imgsList[position])
        return imageView
       }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0}

    override fun getCount(): Int {
        return imgsList.size
    }

}