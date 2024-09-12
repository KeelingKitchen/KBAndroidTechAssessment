package com.example.kbandroidtechassessment

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class KBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }
}
