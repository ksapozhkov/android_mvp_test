package com.napoleontest

import android.app.Application
import androidx.room.Room
import com.lifehacktestapp.android.data.db.AppDatabase
import com.lifehacktestapp.android.data.db.OfferDao
import com.napoleontest.data.network.BannerApi
import com.napoleontest.data.network.OfferApi
import com.napoleontest.data.repository.BannerRepositoryImp
import com.napoleontest.data.repository.OfferRepositoryImp
import com.napoleontest.domain.exception.ApiErrorHandler
import com.napoleontest.domain.repository.BannerRepository
import com.napoleontest.domain.repository.OfferRepository
import com.napoleontest.domain.usecase.GetBannersUseCase
import com.napoleontest.domain.usecase.GetOffersUseCase
import com.napoleontest.presentation.main.presenter.MainPresenter
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXContextTranslators
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override val kodein = Kodein {
        import(androidXContextTranslators)
        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                .client(OkHttpClient().newBuilder().build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        bind<AppDatabase>() with singleton {
            Room.databaseBuilder(baseContext, AppDatabase::class.java, "napoleontest_db")
                .build()
        }
        bind<OfferApi>() with singleton { instance<Retrofit>().create(OfferApi::class.java) }
        bind<BannerApi>() with singleton { instance<Retrofit>().create(BannerApi::class.java) }
        bind<OfferRepository>() with singleton { OfferRepositoryImp(instance()) }
        bind<BannerRepository>() with singleton { BannerRepositoryImp(instance()) }
        bind<ApiErrorHandler>() with singleton { ApiErrorHandler() }
        bind<GetOffersUseCase>() with singleton { GetOffersUseCase(instance(), instance()) }
        bind<GetBannersUseCase>() with singleton { GetBannersUseCase(instance(), instance()) }
        bind<OfferDao>() with singleton { instance<AppDatabase>().offerDao() }
//        bind<MainPresenter>() with singleton { MainPresenter(instance(), instance()) }
    }

}