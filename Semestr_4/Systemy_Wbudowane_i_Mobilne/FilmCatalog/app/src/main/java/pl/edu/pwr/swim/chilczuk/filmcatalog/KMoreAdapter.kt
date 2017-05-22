package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

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

class ImgsAdapter()