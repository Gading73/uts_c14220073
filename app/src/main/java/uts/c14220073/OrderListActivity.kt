package uts.c14220073

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class OrderListActivity : AppCompatActivity() {

    // Menjaga objek agar riwayat pesanan tidak hilang meski berpindah halaman
    companion object {
        val orderHistory = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val lvOrderList = findViewById<ListView>(R.id.lvOrderList)

        // Menangkap data dari OrderActivity
        val customerName = intent.getStringExtra("EXTRA_CUSTOMER_NAME")
        val menuName = intent.getStringExtra("EXTRA_MENU_NAME")
        val price = intent.getStringExtra("EXTRA_PRICE", 0)
        val qty = intent.getIntExtra("EXTRA_QTY", 0)
        val discount = intent.getIntExtra("EXTRA_DISCOUNT", 0)
        val total = intent.getIntExtra("EXTRA_TOTAL", 0)

        // Menyusun format rincian pesanan dan memasukkannya ke dalam list
        if (customerName != null && menuName != null) {

        }
    }
}