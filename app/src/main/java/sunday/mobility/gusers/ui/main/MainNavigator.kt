package sunday.mobility.gusers.ui.main

import sunday.mobility.gusers.model.UserBean

interface MainNavigator {
    fun onHandleError(error: String)

    fun loadAllUsers()

    fun onAddUserClick()

    fun onAddUser(userBean: UserBean)
}
