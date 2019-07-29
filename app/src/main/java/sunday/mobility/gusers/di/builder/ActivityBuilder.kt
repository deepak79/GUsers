package sunday.mobility.gusers.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sunday.mobility.gusers.ui.detail.DetailActivity
import sunday.mobility.gusers.ui.main.adapter.UserModule
import sunday.mobility.gusers.ui.main.MainActivity
import sunday.mobility.gusers.ui.splash.SplashActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity


    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity
}
