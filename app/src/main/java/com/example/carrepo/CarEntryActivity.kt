package com.example.carrepo

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.carrepo.databinding.ActivityCarEntryBinding
import com.example.carrepo.model.CarData
import com.example.carrepo.viewmodel.CarViewModel
import java.util.Calendar

class CarEntryActivity : AppCompatActivity() {
    private lateinit var carEntryBinding: ActivityCarEntryBinding

    private lateinit var carViewModel: CarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        carEntryBinding = DataBindingUtil.setContentView(this, R.layout.activity_car_entry)


        //a dropdown menu for available car models
        val carModelArray: Array<String> = resources.getStringArray(R.array.car_models).sortedArray()

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.dropdown_item, carModelArray)

        val autoTv: AutoCompleteTextView = carEntryBinding.model
        autoTv.setAdapter(arrayAdapter)

        //a dropDown menu for car make
        val carMakeArray: Array<String> = resources.getStringArray(R.array.car_make_array).sortedArray()

        val makeAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.dropdown_item, carMakeArray)

        val makeAutoTv: AutoCompleteTextView = carEntryBinding.make
        makeAutoTv.setAdapter(makeAdapter)


        //Adding the datePicker
        //setting up the editText with a click  listener

        carEntryBinding.apply {
            date.setOnClickListener {
                val cal = Calendar.getInstance()

                val myYear = cal.get(Calendar.YEAR)
                val myMonth = cal.get(Calendar.MONTH)
                val myDay = cal.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@CarEntryActivity, { _, year, monthOfYear, dayOfMonth ->
                        val ourDate =
                            (dayOfMonth.toString() + "/" + ((monthOfYear + 1).toString()) + "/" + year.toString())
                        date.setText(ourDate)
                    }, myYear, myMonth, myDay
                )
                datePickerDialog.show()
            }
        }

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        carEntryBinding.apply {
            submitButton.setOnClickListener {
                //store carOne in the database
                insertDataToDatabase()
            }
        }

        carEntryBinding.regNavView.selectedItemId = R.id.mReg

        carEntryBinding.regNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mReg -> true

                R.id.mHome -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mFuel -> {
                    startActivity(Intent(this, FuelActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mList -> {
                    startActivity(Intent(this, CarList::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }


    }


    //insert data to database

    private fun insertDataToDatabase() {

        carEntryBinding.apply {
            val cPlates = numberPlate.text.toString()
            val cMake = make.text.toString()
            val cModel = model.text.toString()
            val cOwner = owner.text.toString()
            val cTare = tare.text.toString()
            val cWeight = weight.text.toString()
            val cDate = date.text.toString()

            if (inputCheck(cPlates, cMake, cModel, cOwner, cTare, cWeight, cDate)) {
                val newCar = CarData(0, cPlates, cMake, cModel, cOwner, cTare, cWeight, cDate)

                //insert newCar to db

                carViewModel.addCar(newCar)
                Toast.makeText(
                    this@CarEntryActivity,
                    "Registration Successful",
                    Toast.LENGTH_LONG
                )
                    .show()
                startActivity(Intent(this@CarEntryActivity, CarList::class.java))
            } else {
                Toast.makeText(
                    this@CarEntryActivity,
                    "Please fill out all the fields in the form",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun inputCheck(
        plates: String,
        make: String,
        model: String,
        owner: String,
        tare: String,
        weight: String,
        date: String
    ): Boolean {
        return !(TextUtils.isEmpty(plates) && TextUtils.isEmpty(make) && TextUtils.isEmpty(model) && TextUtils.isEmpty(
            owner
        ) && TextUtils.isEmpty(tare) && TextUtils.isEmpty(weight) && TextUtils.isEmpty(date))
    }
}