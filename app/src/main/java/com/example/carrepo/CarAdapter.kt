package com.example.carrepo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.carrepo.model.CarData

class CarAdapter(private val context: Activity, private val arrayList: ArrayList<CarData>) :
    ArrayAdapter<CarData>(context, R.layout.list_item, arrayList) {



         //listItemBinding.itemViewGroup

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) : View{



        //val myViewGroup = findViewById<ViewGroup>(R.id.itemViewGroup)

        val inflater: LayoutInflater = LayoutInflater.from(context)

        val view: View = inflater.inflate(R.layout.list_item, null)


        val cModel : TextView = view.findViewById(R.id.modelTv)
        val cMake : TextView = view.findViewById(R.id.makeTv)
        val cPlate : TextView = view.findViewById(R.id.noPlateTv)
        val cDate : TextView = view.findViewById(R.id.dateTv)
        val cOwner: TextView = view.findViewById(R.id.ownerTv)

        cModel.text = arrayList[position].carModel
        cMake.text = arrayList[position].carMake
        cPlate.text = arrayList[position].numberPlate
        cDate.text = arrayList[position].datePurchased
        cOwner.text = arrayList[position].carOwner



        return view
    }

}