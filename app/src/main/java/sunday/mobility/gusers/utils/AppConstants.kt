package sunday.mobility.gusers.utils

import android.Manifest

class AppConstants {
    companion object {
        const val DB_NAME = "GUSERS.db"
        const val NULL_INDEX = -1L
        const val IMAGE_PLACEHOLDER = "https://via.placeholder.com/30"
        public val PERMISSIONS = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}
