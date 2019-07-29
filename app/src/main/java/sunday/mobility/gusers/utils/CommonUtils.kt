package sunday.mobility.gusers.utils

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import sunday.mobility.gusers.R


object CommonUtils {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun canAccessBluetooth(context: Context?): Boolean {
        return if (context == null) {
            false
        } else hasPermission(context, Manifest.permission.BLUETOOTH) && hasPermission(
            context,
            Manifest.permission.BLUETOOTH_ADMIN
        )
    }

    private fun hasPermission(context: Context?, perm: String): Boolean {
        return if (context == null) {
            false
        } else PackageManager.PERMISSION_GRANTED == checkSelfPermission(context, perm)
    }

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun makeText(context: Context?, message: String) {
        if (context == null) {
            return
        }
        if (context is Activity) {
            if (context.isFinishing) {
                return
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
