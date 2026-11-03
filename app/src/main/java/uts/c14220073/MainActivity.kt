package uts.c14220073

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Data Menu
    private val menuNames=arrayOf("Caffe Latte", "Matcha Frappe", "Signature Tuna Bowl")
    private val menuPrices= arrayOf("35000", "45000", "60000")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvKatalogMenu = findViewById<ListView>(R.id.lvKatalogMenu)

    // Format tampilan list
    val displayList=menuNames.mapIndexed { index, name -> "$name - Rp${menuPrices[index]}"  }
    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
    lvKatalogMenu.adapter = adapter

        // Navigasi ke Halaman Pesanan saat diklik[span_5](start_span)[span_5](end_span)
        lvKatalogMenu.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("EXTRA_MENU_NAME", menuNames[position])
            intent.putExtra("EXTRA_MENU_PRICE", menuPrices[position])
            startActivity(intent)
        }
    }
}