package com.avsoftware.integertoromannumerals

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RomanApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber by planting a DebugTree
        Timber.plant(Timber.DebugTree())
    }
}