package com.example.databaselibrary.reciepts

import androidx.room.DatabaseView

@DatabaseView("SELECT CompanyAccount.Logo, CompanyAccount.Location, CompanyAccount.CompanyName, TaxInvoice.UnitPrice, TaxInvoice.AmountLitres, TaxInvoice.DiscountedPrice" +
"department.name AS departmentName FROM CompanyAccount, TaxInvoice " +
"INNER JOIN department ON user.departmentId = department.id")
data class ReceiptsDataView (
    var Logo: Int,
    var Location: String,
    var CompanyName: String,
    var UnitPrice: Number,
    var AmountLitres: Number,
    var DiscountedPrice: Number
    )