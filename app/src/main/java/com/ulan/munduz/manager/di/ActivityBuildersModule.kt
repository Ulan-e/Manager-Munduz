package com.ulan.munduz.manager.di

import com.ulan.munduz.manager.ui.add.AddModule
import com.ulan.munduz.manager.ui.add.AddProductActivity
import com.ulan.munduz.manager.ui.add.AddScope
import com.ulan.munduz.manager.ui.detail.DetailsActivity
import com.ulan.munduz.manager.ui.detail.DetailsModule
import com.ulan.munduz.manager.ui.detail.DetailsScope
import com.ulan.munduz.manager.ui.main.MainActivity
import com.ulan.munduz.manager.ui.main.MainModule
import com.ulan.munduz.manager.ui.main.MainScope
import com.ulan.munduz.manager.ui.orders.OrdersActivity
import com.ulan.munduz.manager.ui.orders.OrdersModule
import com.ulan.munduz.manager.ui.orders.OrdersScope
import com.ulan.munduz.manager.ui.products.ProductsActivity
import com.ulan.munduz.manager.ui.products.ProductsModule
import com.ulan.munduz.manager.ui.products.ProductsScope
import com.ulan.munduz.manager.ui.slider.AddSliderActivity
import com.ulan.munduz.manager.ui.slider.SliderModule
import com.ulan.munduz.manager.ui.slider.SliderScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @AddScope
    @ContributesAndroidInjector(modules = [AddModule::class])
    abstract fun addProductActivity(): AddProductActivity

    @ProductsScope
    @ContributesAndroidInjector(modules = [ProductsModule::class])
    abstract fun productsActivity(): ProductsActivity

    @DetailsScope
    @ContributesAndroidInjector(modules = [DetailsModule::class])
    abstract fun detailsActivity(): DetailsActivity

    @OrdersScope
    @ContributesAndroidInjector(modules = [OrdersModule::class])
    abstract fun ordersActivity(): OrdersActivity

    @SliderScope
    @ContributesAndroidInjector(modules = [SliderModule::class])
    abstract fun sliderActivity(): AddSliderActivity
}