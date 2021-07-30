package com.example.databaselibrary.reciepts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.databaselibrary.reciepts.ReceiptsCompany
import java.time.DateTimeException

@Entity( tableName = "TaxInvoice")
// ,
//        foreignKeys = arrayOf(ForeignKey(  entity = ReceiptsCompany::class,
//        parentColumns = arrayOf ("companyid"),
//        childColumns = arrayOf ("id_tax"),
//        onDelete = ForeignKey.CASCADE)
//)

data class ReceiptsTax (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo var id_tax: Int = 0,
        @ColumnInfo var NetPOSVersion: String,
        @ColumnInfo var POSID: Int,
        @ColumnInfo var ShiftNumber: Number,
        @ColumnInfo var Date: DateTimeException,
        @ColumnInfo var GreadePump: Number,
        @ColumnInfo var Attendant: String,
        @ColumnInfo var UnitPrice: Number,
        @ColumnInfo var AmountLitres: Number,
        @ColumnInfo var DiscountedPrice: Number,
        @ColumnInfo var Total: Number,
        @ColumnInfo var VAT: Number,

        )