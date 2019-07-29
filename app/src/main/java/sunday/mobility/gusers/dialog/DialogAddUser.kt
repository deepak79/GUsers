package sunday.mobility.gusers.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import sunday.mobility.gusers.R
import sunday.mobility.gusers.databinding.DialogAddUserBinding
import sunday.mobility.gusers.model.UserBean
import sunday.mobility.gusers.utils.AppConstants
import sunday.mobility.gusers.utils.CallBack
import sunday.mobility.gusers.utils.CommonUtils
import java.util.*


class DialogAddUser(
    private val mainActivity: AppCompatActivity,
    private val callBack: CallBack?
) : Dialog(mainActivity) {
    private var binding: DialogAddUserBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_user, null, false)
        setContentView(binding!!.root)

        binding!!.btnCancel.setOnClickListener { v ->
            callBack?.onCancel(this)
        }
        binding!!.btnAddUser.setOnClickListener { v ->
            if (validate()) {
                callBack?.onAdd(
                    this,
                    UserBean(
                        null,
                        binding?.etName?.text.toString(),
                        (0..1000).random(),
                        UUID.randomUUID().toString().replace("-", ""),
                        AppConstants.IMAGE_PLACEHOLDER
                    )
                )
            }
        }
    }

    fun validate(): Boolean {
        if (binding?.etName?.text.toString() == "") {
            CommonUtils.makeText(mainActivity, "Please enter user name")
            return false
        }
        return true
    }

    public override fun onStart() {
        super.onStart()
        setHeightWidth()
    }

    private fun setHeightWidth() {
        val wm = mainActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        val win = window
        if (win != null) {
            win.setBackgroundDrawableResource(android.R.color.white)
            win.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            val width = (metrics.widthPixels * 0.8).toInt()
            val height = (metrics.heightPixels * 0.5).toInt()
            win.setLayout(width, height)
        }
    }
}
