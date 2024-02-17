package com.example.di

import com.example.data.CartoonApiService
import com.example.data.Repository
import com.example.homework2_6.BuildConfig
import com.example.ui.MainViewModel
import com.example.ui.SecondViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartoonModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .callTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideCartoonApiService(retrofit: Retrofit): CartoonApiService =
        retrofit.create(CartoonApiService::class.java)

    val networkModule = module {
        single {
            provideRetrofit(get())
        }

        single {
            provideOkHttpClient(get())
        }

        single {
            provideInterceptor()
        }

        single {
            provideCartoonApiService(get())
        }
    }

    val repositoryModule = module {
        single {
            Repository(get())
        }
    }

    val viewModelModule = module {

        viewModel {
            MainViewModel(get())
        }

        viewModel {
            SecondViewModel(get())
        }
    }

    val cartoonModule = listOf(networkModule, repositoryModule, viewModelModule)
}
