package com.example.databaselibrary

import com.example.databaselibrary.RemoteDao
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteDao() }
 single { TaxRemoteDao() }
}