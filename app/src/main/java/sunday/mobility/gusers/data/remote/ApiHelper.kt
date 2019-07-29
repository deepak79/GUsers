package sunday.mobility.gusers.data.remote

import io.reactivex.Single
import sunday.mobility.gusers.model.UserBean

interface ApiHelper {
    fun getAllUsersNW(): Single<List<UserBean>>
}