package pl.edu.pwr.swim.chilczuk.filmcatalog

object MoviesKeeper{
    var movieList = loadMovieList()
    var currentPosition = 0
}

fun loadMovieList() : MutableList <Movie> {
    val movieList = mutableListOf<Movie>()
    val actorList = loadActorList()
    val descList = loadDescriptions()
    movieList.add(Movie("Mad Max: Fury Road", "Action & Adventure", "2015", R.drawable.m01, descList[0], listOf(actorList[0], actorList[1], actorList[2], actorList[7]), imgsOf(0)))
    movieList.add(Movie("Inside Out", "Animation, Kids & Family", "2015", R.drawable.m11, descList[1], listOf(actorList[3], actorList[4], actorList[5], actorList[7]), imgsOf(1)))
    movieList.add(Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015",  R.drawable.m31, descList[2], listOf(actorList[6], actorList[0], actorList[2], actorList[7]), imgsOf(2)))
    movieList.add(Movie("Shaun the Sheep", "Animation", "2015",  R.drawable.m41, descList[3], listOf(actorList[4], actorList[6], actorList[0], actorList[7]), imgsOf(3)))
    movieList.add(Movie("The Martian", "Science Fiction & Fantasy", "2015",  R.drawable.m02, descList[4], listOf(actorList[1], actorList[3], actorList[5], actorList[7]), imgsOf(4)))
    movieList.add(Movie("Mission: Impossible Rogue Nation", "Action", "2015", R.drawable.m12, descList[5], listOf(actorList[5], actorList[4], actorList[2], actorList[7]), imgsOf(5)))
    movieList.add(Movie("Up", "Animation", "2009",  R.drawable.oczu, descList[0], listOf(actorList[5], actorList[1], actorList[0], actorList[7]), imgsOf(6)))
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
    actorList.add(Actor("Keira", "Knightley", R.drawable.a7))
    actorList.add(Actor("Marylin", "Monroe", R.drawable.a8))
    return actorList
}

fun loadDescriptions() : MutableList<String> {
    val descList = mutableListOf<String>()
    descList.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.   Curabitur lacinia mi at ex condimentum finibus. Quisque sit amet condimentum tellus. Sed aliquet turpis sit amet metus malesuada accumsan.")
    descList.add("Vestibulum feugiat nibh quis libero aliquam fermentum. Praesent viverra odio sit amet laoreet pellentesque.")
    descList.add("Phasellus nec odio mi. Nunc orci felis, ullamcorper vel augue eu, efficitur aliquam metus.  Nulla lectus orci, consequat eu placerat at, dignissim ut nisl. Sed a bibendum odio.")
    descList.add("Sed aliquam luctus nibh id aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit.Duis varius tellus lectus, eget sollicitudin risus aliquam ullamcorper. Suspendisse euismod aliquet tortor sit amet sodales.")
    descList.add("Etiam tincidunt, augue ac scelerisque pulvinar, arcu lectus convallis ante. Curabitur at tortor leo. Nullam volutpat ligula in orci vulputate, et posuere massa tincidunt. Integer vitae mi neque.")
    descList.add("Etiam tincidunt, augue ac scelerisque pulvinar, arcu lectus convallis ante. Curabitur at tortor leo. Nullam volutpat ligula in orci vulputate, et posuere massa tincidunt. Integer vitae mi neque.")
    return descList
}

fun imgsOf(m :Int):List<Int>{
    when(m){
        0 -> return listOf(R.drawable.m01,R.drawable.m02,R.drawable.m03,R.drawable.m04,R.drawable.m05,R.drawable.m06)
        1 -> return listOf(R.drawable.m11,R.drawable.m12,R.drawable.m13,R.drawable.m14,R.drawable.m15,R.drawable.m16)
        2 -> return listOf(R.drawable.m41,R.drawable.m32,R.drawable.m13,R.drawable.m04,R.drawable.m45,R.drawable.m36)
        3 -> return listOf(R.drawable.m31,R.drawable.m32,R.drawable.m33,R.drawable.m34,R.drawable.m35,R.drawable.m36)
        4 -> return listOf(R.drawable.m41,R.drawable.m42,R.drawable.m43,R.drawable.m44,R.drawable.m45,R.drawable.m46)
        5 -> return listOf(R.drawable.m31,R.drawable.m12,R.drawable.m03,R.drawable.m44,R.drawable.m35,R.drawable.m46)
    }
    return listOf()
}
