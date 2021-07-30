package com.example.databaseinput

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Character.FORMAT
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.date_picker_actions)
        val cal = Calendar.getInstance()
        val myYear = cal.get(Calendar.YEAR)
        val myMonth = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        tv.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth -> tv.text = "Date:" + dayOfMonth + "/" + (month +1) + "/ " + year

            }, myYear, myMonth, day)
            datePickerDialog.show()
        }
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"

        )
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            "MyKeyAlias",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()

        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()

        val pair = encryptData("Test this encryption")

        val decryptedData = decryptData(pair.first, pair.second)

        val encrypted = pair.second.toString(Charsets.UTF_8)
        println("Encrypted data: $encrypted")
        println("Decrypted data: $decryptedData")

        loadData()
        button_save.setOnClickListener{
            saveData()
        }

    }

    fun getKey(): SecretKey {
        val keystore = KeyStore.getInstance("AndroidKeyStore")
        keystore.load(null)

        val secretKeyEntry = keystore.getEntry("MyKeyAlias", null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }

    fun encryptData(data: String): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")

        var password = data
        while (password.toByteArray().size % 16 != 0)
            password += "\u0020"

        cipher.init(Cipher.ENCRYPT_MODE, getKey())

        val ivBytes = cipher.iv
        val encryptedBytes = cipher.doFinal(password.toByteArray(Charsets.UTF_8))


        return Pair(ivBytes, encryptedBytes)
    }

    fun decryptData(ivBytes: ByteArray, data: ByteArray): String{
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        val spec = IvParameterSpec(ivBytes)

        cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)
        return cipher.doFinal(data).toString(Charsets.UTF_8).trim()
    }

    private fun saveData(){
        val insertedTextView = email_address.text.toString()
        et_text.text = insertedTextView
        val datePickerDialog = date_picker_actions.text.toString()
        date_picker_actions.text = datePickerDialog

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertedTextView)
            putString("STRING_KEY", datePickerDialog)
            putBoolean("BOOLEAN_KEY", sw_switch.isChecked)}.apply()
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()

//for reading

}
    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharePrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)
        et_text.text = savedString
        date_picker_actions.text = savedString
        sw_switch.isChecked = savedBoolean
        //for reading

    }
}
