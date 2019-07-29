package sunday.mobility.gusers.data.remote

import com.androidnetworking.common.Priority
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import sunday.mobility.gusers.model.UserBean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject
constructor(
) : ApiHelper {
    override fun getAllUsersNW(): Single<List<UserBean>> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_ALL_USERS)
            .setPriority(Priority.HIGH)
            .build()
            .getObjectListSingle(UserBean::class.java)
    }
}