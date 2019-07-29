package sunday.mobility.gusers.ui.detail

import sunday.mobility.gusers.data.DataManager
import sunday.mobility.gusers.model.UserBean
import sunday.mobility.gusers.ui.base.BaseViewModel
import sunday.mobility.gusers.utils.rx.SchedulerProvider


class DetailViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<DetailNavigator>(dataManager, schedulerProvider) {

    private fun handleError(error: Throwable?) {
        setIsLoading(false)
        navigator.onHandleError(error?.message!!)
    }

    fun onDeleteClicked(){
        navigator.onDeleteClicked()
    }

    fun deleteUser(mUserBean: UserBean){
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.deleteUser(mUserBean)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    navigator.onHandleError("User has been deleted successfully!")
                    navigator.onDeleted()
                }, { throwable ->
                    navigator.onHandleError("Failed to delete user!!")
                })
        )
    }
}
