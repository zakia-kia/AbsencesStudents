package com.zakia.idn.absencesstudents.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.zakia.idn.absencesstudents.R
import com.zakia.idn.absencesstudents.model.Items
import com.zakia.idn.absencesstudents.remote.APIUtils
import com.zakia.idn.absencesstudents.remote.AbsencesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AbsencesActivity : AppCompatActivity() {
    var absencesService : AbsencesService? = null
    var edtName : EditText? = null
    var edtKelas : EditText? = null
    var edtDate : EditText? = null
    var edtSubjects : EditText? = null
    var edtInformation : EditText? = null
    var edtId: EditText? = null
    var ivDelete : ImageView?= null
    var btnAdd : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absences)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        edtId = findViewById(R.id.et_id)
        edtName = findViewById(R.id.et_name)
        edtKelas = findViewById(R.id.et_kelas)
        edtDate = findViewById(R.id.et_date)
        edtSubjects = findViewById(R.id.et_subject)
        edtInformation = findViewById(R.id.et_information)
        ivDelete = findViewById(R.id.iv_delete)
        btnAdd = findViewById(R.id.btn_Add)
        absencesService = APIUtils.getAbsencesService()

        val extras = intent.extras
        val studentId = extras!!.getString("id")
        val studentName = extras.getString("name")
        val studentKelas = extras.getString("kelas")
        val studentDate = extras.getString("date")
        val studentSubjects = extras.getString("subjects")
        val studentInformation = extras.getString("information")

        edtId?.setText(studentId)
        edtName?.setText(studentName)
        edtKelas?.setText(studentKelas)
        edtDate?.setText(studentDate)
        edtSubjects?.setText(studentSubjects)
        edtInformation?.setText(studentInformation)

        if (studentId != null ) {
            edtId?.setFocusable(false)
        } else {
            edtId?.setVisibility(View.INVISIBLE)
            ivDelete?.setVisibility(View.INVISIBLE)
        }

        btnAdd?.setOnClickListener(View.OnClickListener{
            val name = edtName?.getText().toString()
            val date = edtDate?.getText().toString()
            val kelas = edtKelas?.getText().toString()
            val subjects = edtSubjects?.getText().toString()
            val information = edtInformation?.getText().toString()

            if (studentId != null && studentId.trim {it <= ' '}.length > 0 ) {
                updateAbsences(studentId.toInt(),name,date,kelas,subjects,information)
            } else {
                addAbsences(name,date,kelas,subjects,information)
            }
        })
        ivDelete?.setOnClickListener(View.OnClickListener {
            this.deleteAbsences(studentId!!.toInt())
            val intent = Intent(this@AbsencesActivity, MainActivity::class.java)
            startActivity(intent)
        })
    }


    private fun addAbsences(name: String, date: String, kelas: String, subjects: String, information: String) {
        val call: Call<Items?>? = absencesService!!.addAbsences(name,date,kelas,subjects,information)
        call?.enqueue(object : Callback<Items?>{
            override fun onResponse(call: Call<Items?>, response: Response<Items?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(this@AbsencesActivity, "Absences added", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Items?>, t: Throwable) {
                Log.e("ERROR: ", t.message)
            }
        })
    }

    private fun updateAbsences(id: Int, name: String, date: String, kelas: String, subjects: String, information: String) {
        val call: Call<Items?>? = absencesService!!.updateAbsences(id,name,date,kelas,subjects,information)
        call?.enqueue(object : Callback<Items?> {
            override fun onResponse(call: Call<Items?>, response: Response<Items?>) {
                Toast.makeText(this@AbsencesActivity, "Absences Updated", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Items?>, t: Throwable) {
                Log.e("ERROR: ", t.message)
            }

        })
    }

    private fun deleteAbsences(id: Int) {
        val call: Call<Items?>? = absencesService!!.deleteAbsences(id)
        call!!.enqueue(object : Callback<Items?> {
            override fun onResponse(call: Call<Items?>, response: Response<Items?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(this@AbsencesActivity, "Absences Deleted", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Items?>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }
}