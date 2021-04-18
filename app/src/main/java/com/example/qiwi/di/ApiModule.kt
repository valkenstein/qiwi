package com.example.qiwi.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.aldredo.core.base.mvvm.ModelFactory
import com.example.qiwi.data.api.FormApi
import com.example.qiwi.domain.useCase.QiwiUseCase
import com.example.qiwi.presentation.viewModel.QiwiViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {
    @Provides
    @ActivityScope
    fun provideFormApi(retrofit: Retrofit): FormApi {
        return retrofit.create(FormApi::class.java)
    }

    @Provides
    @ActivityScope
    fun provideViewModel(qiwiUseCase: QiwiUseCase, context: ViewModelStoreOwner): QiwiViewModel {
        return ViewModelProvider(
            context,
            ModelFactory(QiwiViewModel(qiwiUseCase))
        ).get(QiwiViewModel::class.java)
    }
}