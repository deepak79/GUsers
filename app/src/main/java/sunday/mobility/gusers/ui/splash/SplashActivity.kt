package sunday.mobility.gusers.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import sunday.mobility.gusers.BR
import sunday.mobility.gusers.R
import sunday.mobility.gusers.ViewModelProviderFactory
import sunday.mobility.gusers.databinding.ActivityMainBinding
import sunday.mobility.gusers.databinding.ActivitySplashBinding
import sunday.mobility.gusers.ui.base.BaseActivity
import sunday.mobility.gusers.ui.main.MainActivity
import sunday.mobility.gusers.utils.CommonUtils
import javax.inject.Inject


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator,
    HasSupportFragmentInjector {
    override fun onShowNext() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override val viewModel: SplashViewModel
        get() = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var binding: ActivitySplashBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = viewDataBinding
        viewModel.navigator = this

        Handler().postDelayed({
            viewModel.navigator.onShowNext()
        }, 2000)
    }

    override fun onHandleError(error: String) {
        CommonUtils.makeText(this, error)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
