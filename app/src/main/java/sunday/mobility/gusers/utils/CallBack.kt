package sunday.mobility.gusers.utils

import android.app.Dialog
import sunday.mobility.gusers.model.UserBean

interface CallBack {
    fun onAdd(dialog : Dialog,UserBean: UserBean)

    fun onCancel(dialog : Dialog)
}