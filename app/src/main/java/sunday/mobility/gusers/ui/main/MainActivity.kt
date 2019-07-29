package sunday.mobility.gusers.ui.main

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import sunday.mobility.gusers.BR
import sunday.mobility.gusers.R
import sunday.mobility.gusers.ViewModelProviderFactory
import sunday.mobility.gusers.databinding.ActivityMainBinding
import sunday.mobility.gusers.dialog.DialogAddUser
import sunday.mobility.gusers.model.UserBean
import sunday.mobility.gusers.ui.base.BaseActivity
import sunday.mobility.gusers.ui.detail.DetailActivity
import sunday.mobility.gusers.ui.main.adapter.UsersAdapter
import sunday.mobility.gusers.utils.AppConstants.Companion.PERMISSIONS
import sunday.mobility.gusers.utils.CallBack
import sunday.mobility.gusers.utils.CommonUtils
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator,
    HasSupportFragmentInjector,
    MultiplePermissionsListener,
    UsersAdapter.onUserRowClicked,
    CallBack {

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var mUsersAdapter: UsersAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this
        checkPermissions(PERMISSIONS)
        mUsersAdapter.setListener(this)
        setup()
    }

    override fun onHandleError(error: String) {
        CommonUtils.makeText(this, error)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    private fun checkPermissions(permissions: Array<String>) {
        Dexter.withActivity(this)
            .withPermissions(*permissions)
            .withListener(this)
            .check()
    }

    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
        if (!report.areAllPermissionsGranted()) {
            onHandleError(resources.getString(R.string.permission))
            finish()
        }
    }

    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
        checkPermissions(PERMISSIONS)
        onHandleError(resources.getString(R.string.permission))
    }

    override fun loadAllUsers() {
        viewModel.getAllUsers()
    }

    override fun onResume() {
        super.onResume()
        loadAllUsers()
    }

    override fun onUserRowClicked(userBean: UserBean) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("data",userBean)
        startActivity(intent)
    }

    fun setup() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        binding?.rvUsers?.itemAnimator = DefaultItemAnimator()
        binding?.rvUsers?.adapter = mUsersAdapter

        viewModel.allUsersLiveData.observe(this, Observer { list ->
            mUsersAdapter.clearItems()
            mUsersAdapter.addItems(list)
            binding?.rvUsers?.scrollToPosition(list.size.minus(1))
        })
    }

    override fun onAddUserClick() {
        val dialogAddUser = DialogAddUser(this, this)
        dialogAddUser.setCancelable(false)
        dialogAddUser.show()
    }

    override fun onAddUser(userBean: UserBean) {
        viewModel.onAddUser(userBean)
    }

    override fun onAdd(dialog: Dialog, UserBean: UserBean) {
        dialog.dismiss()
        onAddUser(UserBean)
    }

    override fun onCancel(dialog: Dialog) {
        dialog.dismiss()
    }
}
