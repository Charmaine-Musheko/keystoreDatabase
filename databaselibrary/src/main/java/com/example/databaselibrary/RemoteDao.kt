package com.example.databaselibrary

import com.example.databaselibrary.reciepts.ReceiptsCompany
import com.example.databaselibrary.dao.ReceiptsCompanyDao
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteDao() : KoinComponent {

    private val dao by inject<ReceiptsCompanyDao>()

    fun insert(receiptsCompany: ReceiptsCompany) {
        dao.insert(receiptsCompany)
    }

    fun getCompanies(): List<ReceiptsCompany> {
        return dao.getAllReceiptsCompanies()


    }
}