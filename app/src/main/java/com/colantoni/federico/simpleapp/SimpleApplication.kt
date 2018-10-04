package com.colantoni.federico.simpleapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**  */
class SimpleApplication : Application() {

    override fun attachBaseContext(base: Context) {

        super.attachBaseContext(base)

        MultiDex.install(this)
    }
}
