package pl.edu.pwr.swim.chilczuk.filmcatalog

class Movie() {
    var title: String? = null
    var genre: String? = null
    var year: String? = null
    var imgID: Int = 0

    constructor(title: String, genre: String, year: String, imgID: Int = R.drawable.oczu) : this() {
        this.title = title
        this.genre = genre
        this.year = year
        this.imgID = imgID
    }
}