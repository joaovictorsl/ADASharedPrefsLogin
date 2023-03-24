package me.joaovictorsl.practicing

import android.app.Application

val prefs: Prefs by lazy {
    me.joaovictorsl.practicing.Application.prefs!!
}

class Application: Application()
{
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}