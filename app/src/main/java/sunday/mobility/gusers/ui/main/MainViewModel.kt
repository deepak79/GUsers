package sunday.mobility.gusers.ui.main

import androidx.lifecycle.MutableLiveData
import sunday.mobility.gusers.data.DataManager
import sunday.mobility.gusers.model.UserBean
import sunday.mobility.gusers.ui.base.BaseViewModel
import sunday.mobility.gusers.utils.rx.SchedulerProvider
import java.util.*


class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    var allUsersLiveData: MutableLiveData<List<UserBean>> = MutableLiveData()

    fun getAllUsers() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.getAllUsers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnEvent { data, throwable ->
                    if (data == null && throwable == null) {
                        setIsLoading(false)
                        getAllUsersAndStore()
                    }
                }
                .subscribe({ list ->
                    setIsLoading(false)
                    if (list != null && list.size > 0) {
                        allUsersLiveData.setValue(list)
                    } else {
                        getAllUsersAndStore()
                    }
                }, { throwable ->
                    handleError(throwable)
                    allUsersLiveData.value = ArrayList()
                    navigator.onHandleError("Failed to get users from database!!")
                })
        )
    }

    private fun getAllUsersAndStore() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.getAllUsersNW()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ list ->
                    setIsLoading(false)
                    addAllUsers(list)
                }, { throwable ->
                    handleError(throwable)
                    allUsersLiveData.value = ArrayList()
                    navigator.onHandleError("Failed to get users from network!!")
                })
        )
    }

    private fun addAllUsers(list: List<UserBean>) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.addUsers(list)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    getAllUsers()
                }, { throwable ->
                    handleError(throwable)
                    navigator.onHandleError("Failed to add users in database!!")
                })
        )
    }

    private fun handleError(error: Throwable?) {
        setIsLoading(false)
        navigator.onHandleError(error?.message!!)
    }

    fun onAddUserClicked(){
        navigator.onAddUserClick()
    }

    fun onAddUser(userBean: UserBean){
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.addUser(userBean)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    navigator.onHandleError("New user has been added")
                    getAllUsers()
                }, { throwable ->
                    handleError(throwable)
                    navigator.onHandleError("Failed to add users in database!!")
                })
        )
    }
}
