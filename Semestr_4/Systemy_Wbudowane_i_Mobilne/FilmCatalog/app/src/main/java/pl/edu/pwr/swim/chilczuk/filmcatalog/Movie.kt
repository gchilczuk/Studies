package pl.edu.pwr.swim.chilczuk.filmcatalog

class Movie() {
    var title: String? = null
    var genre: String? = null
    var year: String? = null

    constructor(title: String, genre: String, year: String) : this() {
        this.title = title
        this.genre = genre
        this.year = year
    }
}