package com.orbital.sicredimvvm.di.modules

import android.content.Context
import com.orbital.sicredimvvm.db.service.CheckInService
import com.orbital.sicredimvvm.repository.CheckInRepository
import com.orbital.sicredimvvm.repository.imple.CheckInRepositoryImple
import com.orbital.sicredimvvm.viewmodel.DetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit



val checkInRepositoryModule = module {
    fun provideRepositoryCheckIn(
        checkInService: CheckInService,
        contex:Context
    ): CheckInRepository {
        return CheckInRepositoryImple(checkInService,contex)
    }
    single { provideRepositoryCheckIn(get(),androidContext()) }
}
val checkInServiceModule = module {
    fun provideServiceCheckIn(
        retrofit: Retrofit
    ):CheckInService{
        return retrofit.create(CheckInService::class.java)
    }
    single { provideServiceCheckIn(get()) }
}
val checkInViewModelModule = module {
    viewModel {
        DetailsViewModel(detailsRepository = get<CheckInRepository>())
    }
}
