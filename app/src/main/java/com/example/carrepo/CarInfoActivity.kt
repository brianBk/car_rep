package com.example.carrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.carrepo.databinding.ActivityCarInfoBinding

class CarInfoActivity : AppCompatActivity() {

    private lateinit var infoBinding: ActivityCarInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        infoBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_info)

        infoBinding.apply {
            modelText.text = intent.getStringExtra("Model")
            makeText.text = intent.getStringExtra("Make")
            platesText.text = intent.getStringExtra("Plates")
            dateText.text = intent.getStringExtra("Date")
            ownerText.text = intent.getStringExtra("Owner")
            tareText.text = intent.getStringExtra("Tare")
            weightText.text = intent.getStringExtra("Weight")
        }
    }
}