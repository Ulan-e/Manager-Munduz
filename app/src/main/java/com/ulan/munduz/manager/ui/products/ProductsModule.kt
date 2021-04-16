package com.ulan.munduz.manager.ui.products

import com.ulan.munduz.manager.ui.adapter.ProductsAdapter
import com.ulan.munduz.manager.listeners.OnItemClickListener
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ProductsModule {

    @ProductsScope
    @Binds
    abstract fun provideProductsView(activity: ProductsActivity): ProductsView

    @ProductsScope
    @Binds
    abstract fun providePresenter(presenter: ProductsPresenterImpl): ProductsPresenter

    @ProductsScope
    @Binds
    abstract fun provideListener(activity: ProductsActivity): OnItemClickListener

    @Module
    companion object{

        @JvmStatic
        @Provides
        fun provideAdapter(activity: ProductsActivity, listener: OnItemClickListener): ProductsAdapter{
            return ProductsAdapter(activity, listener)
        }
    }
}