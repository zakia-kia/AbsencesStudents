package com.zakia.idn.absencesstudents.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.zakia.idn.absencesstudents.R
import com.zakia.idn.absencesstudents.adapter.AbsencesAdapter
import com.zakia.idn.absencesstudents.model.Items
import com.zakia.idn.absencesstudents.remote.APIUtils
import com.zakia.idn.absencesstudents.remote.AbsencesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var btnAddUser : Button? = null
    var btnGetUser : Button? = null
    var rv : ListView? = null
    var absencesService : AbsencesService? = null
    var list : List<Items> = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddUser = findViewById(R.id.btn_addUser)
        btnGetUser = findViewById(R.id.btn_getUserList)
        rv = findViewById(R.id.rv_main)
        absencesService = APIUtils.getAbsencesService()

        btnAddUser?.setOnClickListener (View.OnClickListener{
            val intent = Intent(this@MainActivity, AbsencesActivity::class.java)
            intent.putExtra("name", "")
            intent.putExtra("date", "")
            intent.putExtra("kelas", "")
            intent.putExtra("subjects", "")
            intent.putExtra("information", "")
            startActivity(intent)
        })

        btnGetUser?.setOnClickListener(View.OnClickListener{ listStudents })
    }

    val listStudents: Unit get() {
        val call: Call<List<Items>>? = absencesService?.getAbsences()
        call!!.enqueue(object  : Callback<List<Items>>{
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                if (response.isSuccessful) {
                    list = response.body()!!
                    rv!!.adapter = AbsencesAdapter(this@MainActivity, R.layout.list_item, list)
                }
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                Log.e("ERROR:", t.message)
            }

        })
    }

}