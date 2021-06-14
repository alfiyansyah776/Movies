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
                337404,
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "en",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "Cruella",
                "2021-05-26",
                false,
                8.6
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
                423108,
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                "en",
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "The Conjuring: The Devil Made Me Do It",
                "2021-05-25",
                false,
                8.2
        )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShows>{
        val tvShows = ArrayList<TvShows>()

        tvShows.add(
            TvShows(
                1399,
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "en",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Game of Thrones",
                "2011-04-17",
                false,
                8.4
        )
        )

        tvShows.add(
            TvShows(
                1416,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "en",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Gray Anatomy",
                "2005-03-27",
                false,
                8.2
            )
        )

        tvShows.add(
            TvShows(
                60735,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "en",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "The Flash",
                "2014-10-07",
                false,
                7.7
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
            "en",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "Cruella",
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "2021-05-26",
            8.6,
            337404
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
            "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
            "en",
            "The Conjuring: The Devil Made Me Do It",
            "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
            "2021-05-25",
            8.2,
            423108

        ))

        return movies
    }

    fun generateRemoteTvShows(): List<ResultsTvShows>{
        val tvShows = ArrayList<ResultsTvShows>()

        tvShows.add(ResultsTvShows(
            "2011-04-17",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            "en",
            "u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "Game of Thrones",
            1399,
            8.4



        ))

        tvShows.add(ResultsTvShows(
            "2005-03-27",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "en",
            "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "Gray Anatomy",
            1416,
            8.2

        ))

        tvShows.add(ResultsTvShows(
            "2014-10-07",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "en",
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "The Flash",
            60375,
            7.7
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