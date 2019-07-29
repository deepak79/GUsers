package sunday.mobility.gusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sunday.mobility.gusers.data.DataManager
import sunday.mobility.gusers.ui.detail.DetailViewModel
import sunday.mobility.gusers.ui.main.MainViewModel
import sunday.mobility.gusers.ui.splash.SplashViewModel
import sunday.mobility.gusers.utils.rx.SchedulerProvider

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject
constructor(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(dataManager, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataManager, schedulerProvider) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dataManager, schedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}