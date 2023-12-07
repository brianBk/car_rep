package com.example.carrepo

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.ViewModelProvider
import com.example.carrepo.databinding.ActivityFuelBinding
import com.example.carrepo.model.FuelData
import com.example.carrepo.viewmodel.CarViewModel
import java.util.Calendar

class FuelActivity : AppCompatActivity() {

    private lateinit var fuelBinding: ActivityFuelBinding
    private lateinit var fuelViewModel: CarViewModel

    private var gasPrice = 213.35
    private var dieselPrice = 202.96


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fuelBinding = DataBindingUtil.setContentView(this, R.layout.activity_fuel)


        fuelBinding.apply {
            fuelingDate.setOnClickListener {
                val cal = Calendar.getInstance()

                val myYear = cal.get(Calendar.YEAR)
                val myMonth = cal.get(Calendar.MONTH)
                val myDay = cal.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@FuelActivity, { _, year, monthOfYear, dayOfMonth ->
                        val ourDate = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                        fuelingDate.setText(ourDate)
                    }, myYear, myMonth, myDay

                )
                datePickerDialog.show()
            }
        }


        //the drop down menu
        val modelArray: Array<String> = resources.getStringArray(R.array.car_models).sortedArray()

        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.dropdown_item, modelArray)

        val autoTv: AutoCompleteTextView = fuelBinding.carTypeTv

        autoTv.setAdapter(arrayAdapter)

        //initialize the viewModel instance

        fuelViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        //onRadioButtonClicked(fuelBinding.petrol)
        //onRadioButtonClicked(fuelBinding.diesel)


        var quantity: Double = 1.0
        var total: Double = gasPrice

        fuelBinding.apply {

            petrol.setOnClickListener {

                var enteredQuantity =
                    Integer.parseInt(fuelBinding.fuelQuantity.text.toString()).toDouble()
                quantity = enteredQuantity


                if (petrol.isChecked) {
                    unitPrice.setText(gasPrice.toString())
                    total = quantity * gasPrice
                    totalAmount.setText(total.toString())

                }

            }

            diesel.setOnClickListener {

                var enteredQuantity = Integer.parseInt(fuelBinding.fuelQuantity.text.toString())
                quantity = enteredQuantity.toDouble()

                if (diesel.isChecked) {
                    unitPrice.setText(dieselPrice.toString())
                    var total = quantity * dieselPrice
                    totalAmount.setText(total.toString())
                }
            }

            submitBtn.setOnClickListener {

                var date = fuelingDate.text.toString()
                var cType = carTypeTv.text.toString()
                var qty = quantity
                var fType = ""
                var uPrice: Double
                if (petrol.isChecked) {
                    fType = "Petrol"
                    //uPrice = gasPrice.toString()
                    uPrice = gasPrice

                } else {
                    if (diesel.isChecked) fType = "Diesel"
                    uPrice = dieselPrice
                }
                var fTotal = total

                //check the data before inserting to db
                if (checkInput(date, cType, qty, fType, uPrice, fTotal)) {
                    //create a fuelData instance and add it to db
                    val fuelInfo = FuelData(0, date, cType, qty, fType, uPrice, fTotal)
                    fuelViewModel.addFuelInfo(fuelInfo)

                    //make a toast
                    Toast.makeText(this@FuelActivity, "Info Successfully Added", Toast.LENGTH_LONG)
                        .show()

                } else {
                    Toast.makeText(
                        this@FuelActivity,
                        "Please fill out all the fields",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }

        fuelBinding.fuelNavView.selectedItemId = R.id.mFuel

        fuelBinding.fuelNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mFuel -> true

                R.id.mList -> {
                    startActivity(Intent(this, CarList::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mReg -> {
                    startActivity(Intent(this, CarEntryActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.mHome -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }


    }

    //checking the input before storing to the database

    private fun checkInput(
        date: String, type: String, quantity: Double, fuelType: String, price: Double, total: Double
    ): Boolean {
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(type) && quantity.equals(null) && TextUtils.isEmpty(
            fuelType
        ) && price.equals(null) && total.equals(null))
    }


}


