package ar.unlam.nohaapp

import android.app.Application
import ar.unlam.nohaapp.core.RetrofitHelper
import ar.unlam.nohaapp.data.ActivitiesRepository
import ar.unlam.nohaapp.data.ApiRepository
import ar.unlam.nohaapp.data.DayRepository
import ar.unlam.nohaapp.data.network.API
import ar.unlam.nohaapp.domain.GetDay
import ar.unlam.nohaapp.domain.GetTodosDias
import ar.unlam.nohaapp.domain.GetWeather
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.DatabaseProvider
import ar.unlam.nohaapp.ui.viewmodel.HomeFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.*

class NohaApp: Application() {
    private val appModule= module {
        single<RoomNohaDB>{DatabaseProvider().getInstanceDatabase(get())}
        single<Retrofit> {RetrofitHelper.getRetrofit()}
        single<API>{API(get())}
        single<ApiRepository>{ ApiRepository((get())) }
        single<GetWeather>{ GetWeather((get())) }
        single<DayRepository>{DayRepository(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))}
        single<GetDay>{ GetDay(get())}
        single<ActivitiesRepository>{ ActivitiesRepository(get()) }
        single<GetTodosDias>{GetTodosDias(get())}
        viewModel { HomeFragmentViewModel(get(), get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NohaApp)
            modules(appModule)
        }
    }
}