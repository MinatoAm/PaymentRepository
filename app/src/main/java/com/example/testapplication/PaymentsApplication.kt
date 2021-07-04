package com.example.testapplication.ui

import android.app.Application
import com.example.testapplication.api.AuthInterceptor
import com.example.testapplication.api.HeaderInterceptor
import com.example.testapplication.api.NetworkService
import com.example.testapplication.repositories.LoginRepository
import com.example.testapplication.repositories.LoginRepositoryImpl
import com.example.testapplication.ui.viewModel.LoginViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PaymentsApplication)
            androidLogger(level = Level.DEBUG)

            modules(
                mutableListOf(diModule)
            )
        }
    }

    private val diModule = module {
        single { provideRetrofit() }
        single { AuthInterceptor() }
        factory { provideNetworkApi(get()) }
        single<LoginRepository> { LoginRepositoryImpl(get()) }

        viewModel {
            LoginViewModel(get(), get())
        }

    }


    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://82.202.204.94/api-test/").client(
            OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(HeaderInterceptor())
                .build()
        )
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideNetworkApi(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

}