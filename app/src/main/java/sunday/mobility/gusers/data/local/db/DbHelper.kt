package sunday.mobility.gusers.data.local.db

import io.reactivex.Completable
import io.reactivex.Maybe
import sunday.mobility.gusers.model.UserBean

interface DbHelper {
    fun getAllUsers(): Maybe<List<UserBean>>

    fun deleteUser(userBean: UserBean): Completable

    fun addUsers(listUserBean: List<UserBean>): Completable

    fun addUser(mUserBean: UserBean): Completable
}