package sunday.mobility.gusers.ui.detail

import sunday.mobility.gusers.model.UserBean

interface DetailNavigator {
    fun onHandleError(error: String)

    fun onDeleteClicked()

    fun onDelete(mUserBean: UserBean)

    fun onDeleted()
}
