package com.dicoding.movies.Utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoidlingResources {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}