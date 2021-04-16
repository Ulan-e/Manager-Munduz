package com.ulan.munduz.manager.di

import com.ulan.munduz.manager.ui.message.MessageModule
import com.ulan.munduz.manager.ui.main.MainScope
import com.ulan.munduz.manager.ui.message.SendMessageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MessageModule::class])
    abstract fun messageFragment(): SendMessageFragment
}