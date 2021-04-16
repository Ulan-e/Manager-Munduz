package com.ulan.munduz.manager

import com.google.firebase.database.FirebaseDatabase
import com.ulan.munduz.manager.di.component.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(false)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent.builder().application(this).build()
    }
}