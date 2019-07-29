package sunday.mobility.gusers.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import sunday.mobility.gusers.data.AppDataManager
import sunday.mobility.gusers.data.DataManager
import sunday.mobility.gusers.data.local.db.AppDatabase
import sunday.mobility.gusers.data.local.db.AppDbHelper
import sunday.mobility.gusers.data.local.db.DbHelper
import sunday.mobility.gusers.data.remote.ApiHelper
import sunday.mobility.gusers.data.remote.AppApiHelper
import sunday.mobility.gusers.di.DatabaseInfo
import sunday.mobility.gusers.utils.AppConstants
import sunday.mobility.gusers.utils.rx.AppSchedulerProvider
import sunday.mobility.gusers.utils.rx.SchedulerProvider

import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }


    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}