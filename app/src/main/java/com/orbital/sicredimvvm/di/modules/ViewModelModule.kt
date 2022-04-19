package com.orbital.sicredimvvm.di.modules

import com.orbital.sicredimvvm.viewmodel.DetailsViewModel
import com.orbital.sicredimvvm.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
}
