package sunday.mobility.gusers.ui.main.adapter

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class UserModule {

    @Provides
    fun provideUserAdapter(): UsersAdapter {
        return UsersAdapter(ArrayList())
    }
}

