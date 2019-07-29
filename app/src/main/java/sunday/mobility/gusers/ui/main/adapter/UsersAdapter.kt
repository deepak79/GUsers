package sunday.mobility.gusers.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sunday.mobility.gusers.databinding.RowUsersBinding
import sunday.mobility.gusers.databinding.RowUsersEmptyBinding
import sunday.mobility.gusers.model.UserBean
import sunday.mobility.gusers.ui.base.BaseViewHolder

class UsersAdapter(private val mUserBeanList: MutableList<UserBean>?) : RecyclerView.Adapter<BaseViewHolder>() {
    private var listener: onUserRowClicked? = null

    override fun getItemCount(): Int {
        return if (mUserBeanList != null && mUserBeanList.size > 0) {
            mUserBeanList.size
        } else {
            1
        }
    }

    fun setListener(listener: onUserRowClicked) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return if (mUserBeanList != null && !mUserBeanList.isEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    fun addItems(userBeanList: List<UserBean>) {
        mUserBeanList!!.addAll(userBeanList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mUserBeanList!!.clear()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val rowAppointmentsBinding = RowUsersBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                RowViewHolder(rowAppointmentsBinding)
            }
            else -> {
                val emptyViewBinding = RowUsersEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmptyViewHolder(emptyViewBinding)
            }
        }
    }

    interface onUserRowClicked {
        fun onUserRowClicked(userBean: UserBean)
    }

    inner class RowViewHolder(private val mBinding: RowUsersBinding) : BaseViewHolder(mBinding.root),
        UserItemViewModel.UserItemViewClickListener {
        private var mUserItemViewModel: UserItemViewModel? = null

        override fun onBind(position: Int) {
            val mUserBean = mUserBeanList!![position]
            mUserItemViewModel = UserItemViewModel(mUserBean, this,mBinding.root.context)
            mBinding.viewModel = mUserItemViewModel
            // Immediate Binding
            mBinding.executePendingBindings()
        }

        override fun onUserRowClicked(userBean: UserBean) {
            listener!!.onUserRowClicked(userBean)
        }
    }

    inner class EmptyViewHolder(private val mBinding: RowUsersEmptyBinding) : BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {}
    }

    companion object {

        val VIEW_TYPE_EMPTY = 0

        val VIEW_TYPE_NORMAL = 1
    }
}
