package com.ulan.munduz.manager.ui.add

import dagger.Binds
import dagger.Module

@Module
abstract class AddModule {

    @AddScope
    @Binds
    abstract fun provideAddView(activity: AddProductActivity): AddView

    @AddScope
    @Binds
    abstract fun provideAddPresenter(presenter: AddPresenterImpl): AddPresenter
}