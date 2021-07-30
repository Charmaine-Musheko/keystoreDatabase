package com.example.databaselibrary

import com.example.databaselibrary.reciepts.ReceiptsTax
import com.example.databaselibrary.dao.ReceiptsTaxDao
import org.koin.core.KoinComponent
import org.koin.core.inject

class TaxRemoteDao() : KoinComponent {

    private val dao by inject<ReceiptsTaxDao>()

    fun insert(item: ReceiptsTax) {
        dao.insert(item)
    }

    fun getTaxCompanies(): List<ReceiptsTax> {
        return dao.getAllReceiptsTax()


    }
}