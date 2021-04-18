package com.example.qiwi.di

import androidx.lifecycle.ViewModelStoreOwner
import com.aldredo.core.base.di.CoreComponent
import com.example.qiwi.presentation.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ApiModule::class]
)
interface ActivityComponent {
    fun inject(app: MainActivity)

    companion object {
        fun create(context: MainActivity): ActivityComponent =
            DaggerActivityComponent.builder()
                .coreComponent(CoreComponent.create(context.application))
                .setViewModelStoreOwner(context)
                .build()
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setViewModelStoreOwner(context: ViewModelStoreOwner): Builder

        fun coreComponent(component: CoreComponent): Builder

        fun build(): ActivityComponent
    }
}