package com.example.databaselibrary.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.databaselibrary.reciepts.ReceiptsCompany
@Dao
interface ReceiptsCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(receiptsCompany: ReceiptsCompany)



    @Query("SELECT * FROM CompanyAccount")
    fun getAllReceiptsCompanies(): List<ReceiptsCompany>

}