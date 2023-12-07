package com.example.carrepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.carrepo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

/*        binding.regButton.isClickable = true
        binding.fuelButton.isClickable = true

        binding.regButton.setOnClickListener {
            val regIntent = Intent(this, CarEntryActivity::class.java)
            startActivity(regIntent)
        }
        binding.fuelButton.setOnClickListener {
            val fuelIntent = Intent(this, FuelActivity::class.java)
            startActivity(fuelIntent)
        }
        binding.carListButton.setOnClickListener {
            val listIntent = Intent(this, CarList::class.java)
            startActivity(listIntent)
        }*/

        binding.homeNavView.selectedItemId = R.id.mHome

        binding.homeNavView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mHome -> true

                R.id.mReg -> {
                    startActivity(Intent(this, CarEntryActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.mFuel -> {
                    startActivity(Intent(this, FuelActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.mList -> {
                    startActivity(Intent(this, CarList::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                else -> false
            }
        }


    }

/*    private fun openCarEntry() {
        val intent = Intent(this, CarEntryActivity::class.java)
        startActivity(intent)
    }*/
}