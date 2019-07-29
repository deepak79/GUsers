package sunday.mobility.gusers.ui.splash

import org.json.JSONException
import org.json.JSONObject
import sunday.mobility.gusers.data.DataManager
import sunday.mobility.gusers.ui.base.BaseViewModel
import sunday.mobility.gusers.utils.rx.SchedulerProvider

import java.util.Date


class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    private fun handleError(error: String) {
        setIsLoading(false)
        navigator.onHandleError(error)
    }
}
