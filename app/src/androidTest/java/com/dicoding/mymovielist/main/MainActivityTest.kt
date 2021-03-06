package com.dicoding.mymovielist.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.uiautomator.UiDevice
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.utils.EspressoIdlingResources
import com.dicoding.mymovielist.utils.MoviesTvShowsData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val movieData = MoviesTvShowsData.generateMoviesData()
    private val showsData = MoviesTvShowsData.generateTvShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rvShowsMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieData.size))
    }

    @Test
    fun loadShows(){
        onView(withText(R.string.tvShows)).perform(click())
        onView(withId(R.id.rvShowsMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(showsData.size))
    }

    @Test
    fun loadDetailMovies(){
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((withId(R.id.itemImage))).check(matches(isDisplayed()))
        onView((withId(R.id.itemBackdrop))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(withText(movieData[0].title)))
        onView((withId(R.id.tvOverview))).check(matches(isDisplayed()))
        onView((withId(R.id.tvOverview))).check(matches(withText(movieData[0].overview)))
        onView((withId(R.id.tvReleaseDate))).check(matches(isDisplayed()))
        onView((withId(R.id.tvReleaseDate))).check(matches(withText(movieData[0].releaseDate)))
        onView((withId(R.id.tvGenre))).check(matches(isDisplayed()))
        onView((withId(R.id.tvGenre))).check(matches(withText(movieData[0].genre)))
        onView((withId(R.id.tvDuration))).check(matches(isDisplayed()))
        onView((withId(R.id.tvDuration))).check(matches(withText(movieData[0].duration)))
        onView(withId(R.id.floatingFavorite)).perform(click())
        onView((withId(R.id.share))).perform(click())
    }

    @Test
    fun loadDetailShows(){
        onView(withText(R.string.tvShows)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((withId(R.id.itemImage))).check(matches(isDisplayed()))
        onView((withId(R.id.itemBackdrop))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(withText(showsData[0].title)))
        onView((withId(R.id.tvOverview))).check(matches(isDisplayed()))
        onView((withId(R.id.tvOverview))).check(matches(withText(showsData[0].overview)))
        onView((withId(R.id.tvReleaseDate))).check(matches(isDisplayed()))
        onView((withId(R.id.tvReleaseDate))).check(matches(withText(showsData[0].releaseDate)))
        onView((withId(R.id.tvSeason))).check(matches(isDisplayed()))
        onView((withId(R.id.tvSeason))).check(matches(withText(showsData[0].seasons)))
        onView((withId(R.id.tvGenre))).check(matches(isDisplayed()))
        onView((withId(R.id.tvGenre))).check(matches(withText(showsData[0].genre)))
        onView((withId(R.id.tvDuration))).check(matches(isDisplayed()))
        onView((withId(R.id.tvDuration))).check(matches(withText(showsData[0].duration)))
        onView(withId(R.id.floatingFavorite)).perform(click())
        onView((withId(R.id.share))).perform(click())
    }

    @Test
    fun trailerButtonShow(){
        onView(withText(R.string.tvShows)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btnTrailer)).perform(click())
    }

    @Test
    fun trailerButtonMovie(){
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btnTrailer)).perform(click())
    }

    @Test
    fun MoviesShowFavorite(){
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.string.tvShows)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}