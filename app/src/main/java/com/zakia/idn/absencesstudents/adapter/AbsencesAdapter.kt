package com.zakia.idn.absencesstudents.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.zakia.idn.absencesstudents.activity.AbsencesActivity
import com.zakia.idn.absencesstudents.R
import com.zakia.idn.absencesstudents.model.Items
import java.lang.String

class AbsencesAdapter(context: Context, resource: Int, objects: List<Items>) : ArrayAdapter<Items?>(context, resource, objects) {
    private val items : List<Items>


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.list_item, parent, false)

        val tvNameAbsences = v.findViewById<TextView>(R.id.tv_name)
        val tvDateAbsences = v.findViewById<TextView>(R.id.tv_date)
        val tvKelasAbsences = v.findViewById<TextView>(R.id.tv_kelas)
        val tvSubjectsAbsences = v.findViewById<TextView>(R.id.tv_subjects)
        val tvInformationAbsences = v.findViewById<TextView>(R.id.tv_information)

        tvNameAbsences.setText(String.valueOf(items[position].getName()))
        tvDateAbsences.setText(String.valueOf(items[position].getDate()))
        tvKelasAbsences.setText(String.valueOf(items[position].getKelas()))
        tvSubjectsAbsences.setText(String.valueOf(items[position].getSubjects()))
        tvInformationAbsences.setText(String.valueOf(items[position].getInformation()))

        v.setOnClickListener {
            val intent = Intent(context, AbsencesActivity::class.java)
            intent.putExtra("id", String.valueOf(items[position].getId()))
            intent.putExtra("name", items[position].getName())
            intent.putExtra("date", items[position].getDate())
            intent.putExtra("kelas", items[position].getKelas())
            intent.putExtra("subjects", items[position].getSubjects())
            intent.putExtra("information", items[position].getInformation())
            context.startActivity(intent)
        }
        return v
    }
    init {
        items = objects
    }
}