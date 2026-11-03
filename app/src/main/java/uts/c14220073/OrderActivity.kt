package uts.c14220073

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    private var pricePerItem = 0
    private var subtotal = 0
    private var discount = 0
    private var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = findViewById<TextView>(R.id.tvMenuPrice)
        val etCustomerName = findViewById<EditText>(R.id.etCustomerName)
        val etQty = findViewById<EditText>(R.id.etQty)
        val cbMember = findViewById<CheckBox>(R.id.cbMember)
        val tvSubtotal = findViewById<TextView>(R.id.tvSubtotal)
        val tvDiscount = findViewById<TextView>(R.id.tvDiscount)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        // Menerima data dari halaman sebelumnya
        val menuName = intent.getStringExtra("EXTRA_MENU_NAME") ?: ""

        //Menggunakan getintextra karena di Mainact arraynya Int
        pricePerItem = intent.getIntExtra("EXTRA_MENU_PRICE", 0)

        tvMenuName.text = menuName
        tvMenuPrice.text = "Harga: Rp$pricePerItem"

        // Fungsi Kalkulasi Otomatis
        fun calculateTotal() {
            val qtyStr = etQty.text.toString()
            val qty = if (qtyStr.isNotEmpty()) qtyStr.toInt() else 0

            subtotal = pricePerItem * qty
            discount = if (cbMember.isChecked) (subtotal * 0.1).toInt() else 0
            total = subtotal - discount

            tvSubtotal.text = "Subtotal: Rp$subtotal"
            tvDiscount.text = "Diskon: Rp$discount"
            tvTotal.text = "Total Pembayaran: Rp$total"

        }

        // Pemicu kalkulasi saat Qty diketik
        etQty.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {calculateTotal()}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Pemicu kalkulasi saat Checkbox member diklik
        cbMember.setOnCheckedChangeListener { _, _ -> calculateTotal() }

        // Tombol Konfirmasi
        btnConfirm.setOnClickListener {
            val customerName = etCustomerName.text.toString()
            val qtyStr = etQty.text.toString()

            if (customerName.isEmpty() || qtyStr.isEmpty() || qtyStr == "0") {
                Toast.makeText(this, "Nama dan Qty harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, OrderListActivity::class.java)
            intent.putExtra("EXTRA_CUSTOMER_NAME", customerName)
            intent.putExtra("EXTRA_MENU_NAME", menuName)
            intent.putExtra("EXTRA_PRICE", pricePerItem)
            intent.putExtra("EXTRA_QTY", qtyStr.toInt())
            intent.putExtra("EXTRA_DISCOUNT", discount)
            intent.putExtra("EXTRA_TOTAL", total)
            startActivity(intent)
        }
    }
}