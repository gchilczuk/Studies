package pl.edu.pwr.swim.chilczuk.filmcatalog

import java.io.Serializable

class Movie(var title:String,
            var genre: String,
            var year: String,
            var imgID: Int = R.drawable.oczu) : Serializable{
    var rating:Float? = null
}



