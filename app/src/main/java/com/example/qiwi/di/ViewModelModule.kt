package com.example.qiwi.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.aldredo.core.base.mvvm.ModelFactory
import com.example.qiwi.domain.useCase.QiwiUseCase
import com.example.qiwi.presentation.viewModel.QiwiViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    @ActivityScope
    fun provideViewModel(qiwiUseCase: QiwiUseCase, context: ViewModelStoreOwner): QiwiViewModel {
        return ViewModelProvider(
            context,
            ModelFactory(QiwiViewModel(qiwiUseCase))
        ).get(QiwiViewModel::class.java)
    }
}