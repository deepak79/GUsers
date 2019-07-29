package sunday.mobility.gusers.data.local.db

import io.reactivex.Completable
import io.reactivex.Maybe
import sunday.mobility.gusers.model.UserBean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject
constructor(
    val mAppDatabase: AppDatabase
) : DbHelper {

    override fun getAllUsers(): Maybe<List<UserBean>> {
        return mAppDatabase.usersDao().getAllUsers()
    }

    override fun deleteUser(userBean: UserBean): Completable {
        return Completable.fromAction { mAppDatabase.usersDao().deleteUser(userBean) }
    }

    override fun addUsers(listUserBean: List<UserBean>): Completable {
        return Completable.fromAction { mAppDatabase.usersDao().addUsers(listUserBean) }
    }

    override fun addUser(mUserBean: UserBean): Completable {
        return Completable.fromAction { mAppDatabase.usersDao().addUser(mUserBean) }
    }
}