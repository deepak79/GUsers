package sunday.mobility.gusers.data.remote

import sunday.mobility.gusers.BuildConfig.BASE_URL

class ApiEndPoint {
    companion object {
        val OK = 200
        val ENDPOINT_GET_ALL_USERS = BASE_URL + "users"
    }
}
