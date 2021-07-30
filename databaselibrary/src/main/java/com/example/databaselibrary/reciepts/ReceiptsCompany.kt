package com.example.databaselibrary.reciepts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CompanyAccount")
data class ReceiptsCompany (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo var companyid: Int? = 0,
        @ColumnInfo var Logo: String,
        @ColumnInfo var Location: String,
        @ColumnInfo var CompanyName: String


)