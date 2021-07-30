package com.example.databaseinput

import android.app.Application
import com.example.databaselibrary.DatabaseLibrary
import com.example.databaselibrary.RemoteDao
import com.example.databaselibrary.reciepts.ReceiptsCompany
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigitalPetroleumApplication : Application() {

    companion object {
        lateinit var instance: DigitalPetroleumApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@DigitalPetroleumApplication)
            modules(
                listOf(
                    DatabaseLibrary.initialize()
                )
            )
        }



        insertCompany()
    }

    fun insertCompany() {
        val company = ReceiptsCompany( CompanyName = "Shell", Location = "Windhoek", Logo = "")

        val remoteDao = RemoteDao()
        remoteDao.insert(company)

        val c = RemoteDao().getCompanies().first()
        print("company $c")

    }
}