package sunday.mobility.gusers.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import sunday.mobility.gusers.model.UserBean

object Converter {
    @TypeConverter
    fun stringToJSON(value: String): JSONObject {
        return Gson().fromJson(value, object : TypeToken<JSONObject>() {

        }.type)
    }

    @TypeConverter
    fun JSONToString(list: JSONObject): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToAppointment(json: String): UserBean {
        val gson = Gson()
        val type = object : TypeToken<UserBean>() {

        }.type
        return gson.fromJson<UserBean>(json, type)
    }

    @TypeConverter
    fun appointmentToString(list: UserBean): String {
        val gson = Gson()
        val type = object : TypeToken<UserBean>() {

        }.type
        return gson.toJson(list, type)
    }
}

