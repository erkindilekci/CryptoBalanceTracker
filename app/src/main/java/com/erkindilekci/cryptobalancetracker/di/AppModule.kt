package com.erkindilekci.cryptobalancetracker.di

import android.content.Context
import androidx.room.Room
import com.erkindilekci.data.local.FavoritesDao
import com.erkindilekci.data.local.FavoritesDatabase
import com.erkindilekci.data.remote.ApiService
import com.erkindilekci.data.repository.*
import com.erkindilekci.domain.repository.AuthRepository
import com.erkindilekci.domain.repository.CoinRepository
import com.erkindilekci.domain.repository.DataStoreRepository
import com.erkindilekci.domain.repository.FavoritesRepository
import com.erkindilekci.domain.repository.PortfolioRepository
import com.erkindilekci.domain.usecase.worker.WorkerRequest
import com.erkindilekci.util.Constants.Companion.BASE_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDao(favoritesDatabase: FavoritesDatabase) = favoritesDatabase.getDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FavoritesDatabase::class.java,
        "favorites_database"
    ).build()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(apiService: ApiService): CoinRepository {
        return CoinRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(dao: FavoritesDao): FavoritesRepository {
        return FavoritesRepositoryImp(dao)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImp(firebaseAuth, firestore)
    }

    @Provides
    @Singleton
    fun providePortfolioRepository(firebaseFirestore: FirebaseFirestore): PortfolioRepository {
        return PortfolioRepositoryImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideWorkerRequest(@ApplicationContext context: Context) = WorkerRequest(context)

    @Provides
    @Singleton
    fun provideNotificationRepository(@ApplicationContext context: Context): DataStoreRepository {
        return NotificationRepositoryImp(context)
    }
}
