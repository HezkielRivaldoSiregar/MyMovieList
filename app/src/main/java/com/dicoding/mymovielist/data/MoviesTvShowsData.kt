package com.dicoding.mymovielist.data

import com.dicoding.mymovielist.R

object MoviesTvShowsData {

    fun generateMoviesData(): List<Movies> {

        val movies = ArrayList<Movies>()

        movies.add(
            Movies(
                R.drawable.poster_a_start_is_born,
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Bradley Cooper",
                "75%",
                "05/10/2018",
                "Drama, Romance, Music",
                "Released",
                "2 hours 2 minutes",
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_alita,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                "72%",
                "14/02/2019",
                "Action, Science Fiction, Adventure",
                "Released",
                "2 hours 16 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_aquaman,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                "69%",
                "21/12/2018",
                "Action, Adventure, Fantasy",
                "Released",
                "2 hours 23 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_bohemian,
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Bryan Singer",
                "80%",
                "02/11/2018",
                "Music, Drama, History",
                "Released",
                "2 hours 15 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_cold_pursuit,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Hans Petter Moland",
                "57%",
                "08/02/2019",
                "Action, Crime, Thriller",
                "Released",
                "1 hour 59 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_creed,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Steven Caple Jr.",
                "69%",
                "21/11/2018",
                "Drama",
                "Released",
                "2 hours 10 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_crimes,
                "Fantastic Beasts: The Crimes of Grindelwald ",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "David Yates",
                "69%",
                "16/11/2018",
                "Adventure, Fantasy, Drama",
                "Released",
                "2 hours 14 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_glass,
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
                "67%",
                "18/01/2019",
                "Thriller, Drama, Science Fiction",
                "Released",
                "2 hours 9 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_how_to_train,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Dean DeBlois",
                "78%",
                "22/02/2019",
                "Animation, Family, Adventure",
                "Released",
                "1 hours 44 minutes"
            )
        )

        movies.add(
            Movies(
                R.drawable.poster_infinity_war,
                "Avengers: Infinity War ",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Joe Russo & Anthony Russo",
                "83%",
                "27/04/2018",
                "Adventure, Action, Science Fiction",
                "Released",
                "2 hours 29 minutes"
            )
        )

        return movies
    }

    fun generateTvShowsData(): List<TvShows> {

        val tvShows = ArrayList<TvShows>()

        tvShows.add(
            TvShows(
                R.drawable.poster_arrow,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Greg Berlanti, Andrew Kreisberg",
                "66%",
                "10/10/2012",
                "8",
                "Crime, Drama, Mystery, Action & Adventure",
                "Ended",
                "42 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_doom_patrol,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Jeremy Carver",
                "76%",
                "15/02/2019",
                "2",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "Returning Series",
                "49 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_dragon_ball,
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Akira Toriyama",
                "81%",
                "26/02/1986",
                "1",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "Ended",
                "25 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_family_guy,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Seth MacFarlane",
                "70%",
                "31/01/1999",
                "21",
                "Animation,Comedy",
                "Returning Series",
                "22 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_got,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "David Benioff, D. B. Weiss",
                "84%",
                "17/04/2011",
                "8",
                "Sci-Fi & Fantasy, Drama, Adventure",
                "Ended",
                "1 hour"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_gotham,
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Bruno Heller",
                "75%",
                "22/09/2014",
                "5",
                "Sci-Fi & Fantasy, Drama, Action & Adventure\n",
                "Ended",
                "43 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_grey_anatomy,
                "Grey Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Shonda Rhimes",
                "82%",
                "27/03/2005",
                "17",
                "Drama",
                "Returning Series",
                "43 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_hanna,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "David Farr",
                "75%",
                "28/03/2019",
                "2",
                "Action & Adventure, Drama",
                "Returning Series",
                "50 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_iron_fist,
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "66%",
                "17/03/2017",
                "2",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Canceled",
                "55 minutes"
            )
        )

        tvShows.add(
            TvShows(
                R.drawable.poster_ncis,
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "Donald P.Bellisario, Don McGill",
                "74%",
                "23/09/2003",
                "18",
                "Crime, Action & Adventure, Drama",
                "Returning Series",
                "45 minutes"
            )
        )

        return tvShows
    }
}