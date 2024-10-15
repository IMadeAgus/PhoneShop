package com.example.phoneshop

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Phone>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            false
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPhones.setHasFixedSize(true)
        list.addAll(getListPhones())
        showRecyclerList()
    }
    private fun getListPhones(): ArrayList<Phone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getIntArray(R.array.data_price)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPhone = ArrayList<Phone>()
        for (i in dataName.indices) {
            val phone = Phone(dataName[i], dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPhone.add(phone)
        }
        return listPhone
    }

    private fun showRecyclerList() {
        binding.rvPhones.layoutManager = LinearLayoutManager(this)
        val listPhoneAdapter = ListPhoneAdapter(list)
        binding.rvPhones.adapter = listPhoneAdapter


        listPhoneAdapter.setOnItemClickCallback(object : ListPhoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Phone) {
                showSelectedPhone(data)
            }
        })
    }

    private fun showSelectedPhone(phone: Phone) {

        val moveWithObjectIntent = Intent(this@MainActivity, PhoneDetail::class.java)
        moveWithObjectIntent.putExtra(PhoneDetail.EXTRA_PHONE, phone)
        startActivity(moveWithObjectIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveToProfile = Intent(this@MainActivity, About::class.java)
                startActivity(moveToProfile)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}