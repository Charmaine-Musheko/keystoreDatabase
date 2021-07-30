package com.example.databaselibrary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databaselibrary.reciepts.ReceiptsCompany
import com.example.databaselibrary.dao.ReceiptsCompanyDao
import com.example.databaselibrary.reciepts.ReceiptsDataView
import com.example.databaselibrary.dao.ReceiptsTaxDao

import org.koin.core.module.Module
import org.koin.dsl.module



@Database(
    entities = [
        // Authentication::class,
        ReceiptsCompany::class,

    ], views = arrayOf(ReceiptsDataView::class),
    version = 1,
    exportSchema = false
)


abstract class DatabaseLibrary : RoomDatabase() {

    internal abstract fun getReceiptsTaxDao(): ReceiptsTaxDao

    internal abstract fun getReceiptsCompanyDao(): ReceiptsCompanyDao

    companion object {

        fun initialize(): Module {
            return module {
                single { createDatabase(get()) }
                single { createDatabase(get()).getReceiptsCompanyDao() }
                single { createDatabase(get()).getReceiptsTaxDao() }
                single { RemoteDao() }
                single { TaxRemoteDao() }
            }
        }

        @Volatile
        private var instance: DatabaseLibrary? = null
        fun createDatabase(context: Context): DatabaseLibrary {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        // Create and pre-populate the database. See this article for more details:
// https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): DatabaseLibrary {
            return Room.databaseBuilder(context, DatabaseLibrary::class.java, "DatabaseLibrary")
                .addCallback(object : RoomDatabase.Callback() {
                })
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        // private val LOCK = Any()

//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, DatabaseLibrary::class.java, "DatabaseLibrary").build()
//    }
    }
}
