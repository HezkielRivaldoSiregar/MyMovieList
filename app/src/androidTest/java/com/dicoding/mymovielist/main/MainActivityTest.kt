package com.dicoding.mymovielist.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.data.MoviesTvShowsData
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val movieData = MoviesTvShowsData.generateMoviesData()
    private val showsData = MoviesTvShowsData.generateTvShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withText(R.string.movies)).perform(click())
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
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((withId(R.id.itemImage))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(withText(movieData[0].title)))
        onView((withId(R.id.tvOverview))).check(matches(isDisplayed()))
        onView((withId(R.id.tvOverview))).check(matches(withText(movieData[0].overview)))
        onView((withId(R.id.tvDirector))).check(matches(isDisplayed()))
        onView((withId(R.id.tvDirector))).check(matches(withText(movieData[0].director)))
        onView((withId(R.id.tvRating))).check(matches(isDisplayed()))
        onView((withId(R.id.tvRating))).check(matches(withText(movieData[0].rating)))
        onView((withId(R.id.tvReleaseDate))).check(matches(isDisplayed()))
        onView((withId(R.id.tvReleaseDate))).check(matches(withText(movieData[0].releaseDate)))
        onView((withId(R.id.tvGenre))).check(matches(isDisplayed()))
        onView((withId(R.id.tvGenre))).check(matches(withText(movieData[0].genre)))
        onView((withId(R.id.tvStatus))).check(matches(isDisplayed()))
        onView((withId(R.id.tvStatus))).check(matches(withText(movieData[0].status)))
        onView((withId(R.id.tvDuration))).check(matches(isDisplayed()))
        onView((withId(R.id.tvDuration))).check(matches(withText(movieData[0].duration)))
    }

    @Test
    fun loadDetailShows(){
        onView(withText(R.string.tvShows)).perform(click())
        onView(withId(R.id.rvShowsMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView((withId(R.id.itemImage))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(isDisplayed()))
        onView((withId(R.id.tvTitle))).check(matches(withText(showsData[0].title)))
        onView((withId(R.id.tvOverview))).check(matches(isDisplayed()))
        onView((withId(R.id.tvOverview))).check(matches(withText(showsData[0].overview)))
        onView((withId(R.id.tvCreator))).check(matches(isDisplayed()))
        onView((withId(R.id.tvCreator))).check(matches(withText(showsData[0].creator)))
        onView((withId(R.id.tvRating))).check(matches(isDisplayed()))
        onView((withId(R.id.tvRating))).check(matches(withText(showsData[0].rating)))
        onView((withId(R.id.tvReleaseDate))).check(matches(isDisplayed()))
        onView((withId(R.id.tvReleaseDate))).check(matches(withText(showsData[0].releaseDate)))
        onView((withId(R.id.tvSeasons))).check(matches(isDisplayed()))
        onView((withId(R.id.tvSeasons))).check(matches(withText(showsData[0].seasons)))
        onView((withId(R.id.tvGenre))).check(matches(isDisplayed()))
        onView((withId(R.id.tvGenre))).check(matches(withText(showsData[0].genre)))
        onView((withId(R.id.tvStatus))).check(matches(isDisplayed()))
        onView((withId(R.id.tvStatus))).check(matches(withText(showsData[0].status)))
        onView((withId(R.id.tvDuration))).check(matches(isDisplayed()))
        onView((withId(R.id.tvDuration))).check(matches(withText(showsData[0].duration)))
    }



}