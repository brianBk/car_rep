package com.example.carrepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrepo.databinding.ActivityCarListBinding
import com.example.carrepo.model.CarData
import com.example.carrepo.viewmodel.CarViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count

class CarList : AppCompatActivity() {

    private lateinit var carListBinding: ActivityCarListBinding
    private lateinit var carViewModel: CarViewModel

    var carListArray: ArrayList<CarData> = arrayListOf()
    private lateinit var car1: CarData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        carListBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_list)


        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        var carListView = carListBinding.carListV


        car1 = CarData(
            1, "KDR 4242H", "Sedan", "Audi A4", "Brian Ruga", "2.5", "3.2", "7/8/2021"
        )

        carListArray.add(car1)

        //Receive data from CarEntryActivity through an intent, store the data in an object and then put the object in the arraylist

        carListView.isClickable = true
        val adapter = CarAdapter(this, carListArray)
        carListView.adapter = adapter

        carViewModel.readAllCars.observe(this) { carList ->
            for (item in carList) {
                 adapter.add(item)
            }

            carListBinding.listNavView.getOrCreateBadge(R.id.mList).apply {
                number = carListArray.size
                isVisible = true
            }

        }





        val pos = 2
        carListView.setOnItemClickListener { parent, view, position, id ->

            val model = carListArray[position].carModel
            val make = carListArray[position].carMake
            val plate = carListArray[position].numberPlate
            val date = carListArray[position].datePurchased
            val owner = carListArray[position].carOwner
            val tare = carListArray[position].carTare
            val weight = carListArray[position].carWeight

            val myIntent = Intent(this, CarInfoActivity::class.java)
            myIntent.putExtra("Model", model)
            myIntent.putExtra("Make", make)
            myIntent.putExtra("Plates", plate)
            myIntent.putExtra("Date", date)
            myIntent.putExtra("Owner", owner)
            myIntent.putExtra("Tare", tare)
            myIntent.putExtra("Weight", weight)
            startActivity(myIntent)

        }

        carListBinding.listNavView.selectedItemId = R.id.mList

        carListBinding.listNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mList -> true

                R.id.mHome -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mReg -> {
                    startActivity(Intent(this, CarEntryActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mFuel -> {
                    startActivity(Intent(this, FuelActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }

        //setting up a badge for the number of cars in the list




    }

    /*    fun submitToList(carObj: CarData) {
            carListArray.add(carObj)
        }*/

    fun injectListItem(carOjb: CarData) {
        carListArray.add(carOjb)

    }
}