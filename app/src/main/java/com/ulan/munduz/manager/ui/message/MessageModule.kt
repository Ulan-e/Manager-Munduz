package com.ulan.munduz.manager.ui.message

import android.content.Context
import com.ulan.app.munduz.helpers.SendEmailHelper
import com.ulan.munduz.manager.ui.main.MainScope
import dagger.Module
import dagger.Provides

@Module
class MessageModule{

    @MainScope
    @Provides
    fun provideMessageView(fragment: SendMessageFragment): SendMessageView{
        return fragment
    }

    @MainScope
    @Provides
    fun provideMessagePresenter(view: SendMessageView):SendMessagePresenter{
        return SendMessagePresenterImpl(view)
    }

    @MainScope
    @Provides
    fun provideSendEmail(context: Context): SendEmailHelper {
        return SendEmailHelper(context)
    }
}