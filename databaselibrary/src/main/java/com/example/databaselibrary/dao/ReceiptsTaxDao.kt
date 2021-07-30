package com.example.databaselibrary.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.databaselibrary.reciepts.ReceiptsTax
@Dao
 internal interface ReceiptsTaxDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(receiptsTax: ReceiptsTax)

    @Query("SELECT * FROM TaxInvoice ORDER BY id_tax ASC")
    fun readAllData(): LiveData<List<ReceiptsTax>>
    @Query("SELECT * FROM TaxInvoice")
    fun getAllReceiptsTax(): List<ReceiptsTax>
}