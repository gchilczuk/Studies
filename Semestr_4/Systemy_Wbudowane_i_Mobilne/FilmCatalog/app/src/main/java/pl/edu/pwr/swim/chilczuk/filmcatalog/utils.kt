package pl.edu.pwr.swim.chilczuk.filmcatalog

object MoviesKeeper{
    var movieList = loadMovieList()
    var currentPosition = 0
    val actorList = loadActorList()
}

fun loadMovieList() : MutableList <Movie> {
    val movieList = mutableListOf<Movie>()
    val descList = loadDescriptions()
    movieList.add(Movie("Mad Max: Fury Road", "Action & Adventure", "2015", R.drawable.m01, descList[0]))
    movieList.add(Movie("Inside Out", "Animation, Kids & Family", "2015", R.drawable.m11, descList[1]))
    movieList.add(Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015",  R.drawable.m31, descList[2]))
    movieList.add(Movie("Shaun the Sheep", "Animation", "2015",  R.drawable.m41, descList[3]))
    movieList.add(Movie("The Martian", "Science Fiction & Fantasy", "2015",  R.drawable.m02, descList[4]))
    movieList.add(Movie("Mission: Impossible Rogue Nation", "Action", "2015", R.drawable.m12, descList[5]))
    movieList.add(Movie("Up", "Animation", "2009",  R.drawable.oczu, descList[0]))
    movieList.add(Movie("Star Trek", "Science Fiction", "2009", R.drawable.m32, descList[1] ))
    movieList.add(Movie("The LEGO Movie", "Animation", "2014", R.drawable.m42, descList[2]))
    movieList.add(Movie("Iron Man", "Action & Adventure", "2008", description = descList[3]))
    movieList.add(Movie("Aliens", "Science Fiction", "1986", R.drawable.m05, descList[4]))
    movieList.add(Movie("Chicken Run", "Animation", "2000", R.drawable.m45, descList[5]))
    movieList.add(Movie("Back to the Future", "Science Fiction", "1985", R.drawable.m33, descList[0]))
    movieList.add(Movie("Raiders of the Lost Ark", "Action & Adventure", "1981", description = descList[1]))
    movieList.add(Movie("Goldfinger", "Action & Adventure", "1965"))
    movieList.add(Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014"))
    return movieList
}

fun loadActorList() : MutableList<Actor> {
    val actorList = mutableListOf<Actor>()
    actorList.add(Actor("Diana", "Lane", R.drawable.a1))
    actorList.add(Actor("Tom", "Hardy", R.drawable.a2))
    actorList.add(Actor("Charlize", "Theron", R.drawable.a3))
    actorList.add(Actor("Harrison", "Ford", R.drawable.a4))
    actorList.add(Actor("Daisy", "Ridley", R.drawable.a5))
    actorList.add(Actor("Matt", "Damon", R.drawable.a6))
    return actorList
}

fun loadDescriptions() : MutableList<String> {
    val descList = mutableListOf<String>()
    descList.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.   Curabitur lacinia mi at ex condimentum finibus. Quisque sit amet condimentum tellus. Sed aliquet turpis sit amet metus malesuada accumsan.")
    descList.add("Vestibulum feugiat nibh quis libero aliquam fermentum. Praesent viverra odio sit amet laoreet pellentesque.")
    descList.add("Phasellus nec odio mi. Nunc orci felis, ullamcorper vel augue eu, efficitur aliquam metus.  Nulla lectus orci, consequat eu placerat at, dignissim ut nisl. Sed a bibendum odio.")
    descList.add("Sed aliquam luctus nibh id aliquam. Integer eleifend, libero a ultrices venenatis. Lorem ipsum dolor sit amet, consectetur adipiscing elit.Duis varius tellus lectus, eget sollicitudin risus aliquam ullamcorper. Suspendisse euismod aliquet tortor sit amet sodales.")
    descList.add("Etiam tincidunt, augue ac scelerisque pulvinar, arcu lectus convallis ante. Curabitur at tortor leo. Nullam volutpat ligula in orci vulputate, et posuere massa tincidunt. Integer vitae mi neque.")
    descList.add("Etiam tincidunt, augue ac scelerisque pulvinar, arcu lectus convallis ante. Curabitur at tortor leo. Nullam volutpat ligula in orci vulputate, et posuere massa tincidunt. Integer vitae mi neque.")
    return descList
}
