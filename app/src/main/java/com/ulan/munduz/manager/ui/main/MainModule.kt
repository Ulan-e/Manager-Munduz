package com.ulan.munduz.manager.ui.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainModule{

    @MainScope
    @Binds
    abstract fun provideMainView(activity: MainActivity): MainView

    @MainScope
    @Binds
    abstract fun providePresenter(presenter: MainPresenterImpl): MainPresenter
}