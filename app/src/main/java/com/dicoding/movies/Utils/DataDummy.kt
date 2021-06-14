package com.dicoding.movies.Utils

import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.source.remote.response.ResultsMovies
import com.dicoding.movies.data.source.remote.response.ResultsTvShows

object DataDummy {
    fun generateDummyMovies(): List<Movies>{
        val movies = ArrayList<Movies>()

        movies.add(
            Movies(
                460465,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "en",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Mortal Kombat",
                "2021-04-07",
                false,
                7.8
        )
        )
        movies.add(
            Movies(
                399566,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "en",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Godzilla vs. Kong",
                "2021-03-24",
                false,
                8.1
        )
        )
        movies.add(
            Movies(
                615457,
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "en",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Nobody",
                "2021-03-26",
                false,
                8.5
        )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShows>{
        val tvShows = ArrayList<TvShows>()

        tvShows.add(
            TvShows(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "en",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "The Falcon and the Winter Soldier",
                "2017-09-25",
                false,
                7.9
        )
        )

        tvShows.add(
            TvShows(
                71712,
                "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
                "en",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "The Good Doctor",
                "2017-09-25",
                false,
                8.6
        )
        )

        tvShows.add(
            TvShows(
                79008,
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "es",
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "Luis Miguel : La Serie",
                "2018-04-22",
                false,
                8.0
        )
        )

        tvShows.add(
            TvShows(
                62286,
                "/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "en",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "Fear the walking dead",
                "2015-08-23",
                false,
                7.6
        )
        )
        return tvShows


    }

    fun generateRemoteDummyMovies(): List<ResultsMovies> {
        val movies = ArrayList<ResultsMovies>()

        movies.add(ResultsMovies(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "2021-04-07",
                7.8,
                460465
        ))

        movies.add(ResultsMovies(
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "en",
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                8.1,
                399566

        ))

        movies.add(ResultsMovies(
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind",
                "en",
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26",
                8.5,
                615457

        ))

        return movies
    }

    fun generateRemoteTvShows(): List<ResultsTvShows>{
        val tvShows = ArrayList<ResultsTvShows>()

        tvShows.add(ResultsTvShows(
                "2017-09-25",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                88396,
                7.9
        ))

        tvShows.add(ResultsTvShows(
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "en",
                "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
                "The Good Doctor",
                71712,
                8.6

        ))

        tvShows.add(ResultsTvShows(
                "2018-04-22",
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "es",
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "Luis Miguel : La Serie",
                79008,
                8.0
        ))

        tvShows.add(ResultsTvShows(
                "2015-08-23",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "en",
                "/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "Fear the walking dead",
                62286,
                7.6

        ))
        return tvShows
    }
}