package pl.edu.pwr.swim.chilczuk.filmcatalog

import android.app.Application

class FCApplication(): Application(){
    override fun onCreate() {
        super.onCreate()
        MoviesKeeper.initializeMovieList()
    }
}

object MoviesKeeper{
    var movieList: MutableList<Movie> = mutableListOf<Movie>()
    var currentPosition: Int = 0
    fun initializeMovieList(){
            movieList.addAll(loadMovieList())
    }
}


fun loadMovieList() : MutableList <Movie> {
    val movieList = mutableListOf<Movie>()
    movieList.add(Movie("Mad Max: Fury Road", "Action & Adventure", "2015"))
    movieList.add(Movie("Inside Out", "Animation, Kids & Family", "2015"))
    movieList.add(Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015"))
    movieList.add(Movie("Shaun the Sheep", "Animation", "2015"))
    movieList.add(Movie("The Martian", "Science Fiction & Fantasy", "2015"))
    movieList.add(Movie("Mission: Impossible Rogue Nation", "Action", "2015"))
    movieList.add(Movie("Up", "Animation", "2009"))
    movieList.add(Movie("Star Trek", "Science Fiction", "2009"))
    movieList.add(Movie("The LEGO Movie", "Animation", "2014"))
    movieList.add(Movie("Iron Man", "Action & Adventure", "2008"))
    movieList.add(Movie("Aliens", "Science Fiction", "1986"))
    movieList.add(Movie("Chicken Run", "Animation", "2000"))
    movieList.add(Movie("Back to the Future", "Science Fiction", "1985"))
    movieList.add(Movie("Raiders of the Lost Ark", "Action & Adventure", "1981"))
    movieList.add(Movie("Goldfinger", "Action & Adventure", "1965"))
    movieList.add(Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014"))
    return movieList
}

