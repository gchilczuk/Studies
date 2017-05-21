package pl.edu.pwr.swim.chilczuk.filmcatalog

class Movie(val title: String,
            val genre: String,
            val year: String,
            val imgID: Int = R.drawable.oczu,
            val description: String = "Lorem ipsum dolor sit amet",
            val starring: List<Actor> = listOf(),
            var rating: Float = 0f,
            var watched: Boolean = false)

class Actor(val name: String,
            val surname: String,
            val imgID: Int = R.drawable.oczu)