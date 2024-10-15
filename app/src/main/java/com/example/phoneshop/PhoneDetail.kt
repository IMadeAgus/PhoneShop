package com.example.phoneshop

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.phoneshop.databinding.ActivityMainBinding
import com.example.phoneshop.databinding.ActivityPhoneDetailBinding
import java.text.NumberFormat
import java.util.Locale

class PhoneDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPhoneDetailBinding
    companion object {
        const val EXTRA_PHONE = "extra_phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            false
        }

        // Inisialisasi View Binding
        binding = ActivityPhoneDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataPhone = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Phone>(EXTRA_PHONE, Phone::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Phone>(EXTRA_PHONE)
        }

        if (dataPhone != null) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            binding.textTitle.text = dataPhone.name
            binding.textPrice.text = formatRupiah.format(dataPhone.price)
            binding.textDescription.text = dataPhone.description
            binding.imagePhone.setImageResource(dataPhone.photo)

        }
        binding.actionShare.setOnClickListener(this)
        binding.actionBuy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                // Membuat intent share
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain" // Tipe data yang akan dikirim adalah teks
                    putExtra(Intent.EXTRA_SUBJECT, "Check out this phone!")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hey! Ada hp bagus lho: ${binding.textTitle.text}.\n" +
                                "Harga: ${binding.textPrice.text}.\n" +
                                "Deskripsi: ${binding.textDescription.text}."
                    )
                }
                // Menampilkan chooser untuk berbagi
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
            R.id.action_buy -> {
                Toast.makeText(this, "Maaf fitur belum diimplementasikan", Toast.LENGTH_SHORT).show()
            }

        }
    }
}