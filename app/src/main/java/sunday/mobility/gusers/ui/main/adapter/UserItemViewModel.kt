package sunday.mobility.gusers.ui.main.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import sunday.mobility.gusers.R
import sunday.mobility.gusers.model.UserBean


class UserItemViewModel(
    var mUserBean: UserBean,
    var mListener: UserItemViewClickListener,
    var context: Context
) {
    var mLogin: ObservableField<String> = ObservableField(mUserBean.login!!)
    var mAvatarURL: ObservableField<Drawable>
    var bindableFieldTarget: BindableFieldTarget

    init {
        this.mAvatarURL = ObservableField()
        this.bindableFieldTarget = BindableFieldTarget(mAvatarURL, context.resources)
        Picasso.get()
            .load(mUserBean.avatarUrl)
            .placeholder(R.drawable.ic_github)
            .into(bindableFieldTarget)
    }

    fun onUserRowClicked() {
        mListener.onUserRowClicked(mUserBean)
    }

    interface UserItemViewClickListener {
        fun onUserRowClicked(userBean: UserBean)
    }
}

class BindableFieldTarget(
    private val observableField: ObservableField<Drawable>,
    private val resources: Resources
) : Target {
    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        observableField.set(BitmapDrawable(resources, bitmap))
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        observableField.set(errorDrawable)
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        observableField.set(placeHolderDrawable)
    }
}