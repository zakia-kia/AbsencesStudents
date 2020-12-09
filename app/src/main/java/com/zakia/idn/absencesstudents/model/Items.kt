package com.zakia.idn.absencesstudents.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

class Items {
    @SerializedName("date")
    @Expose
    private var date: String? = null

    @SerializedName("kelas")
    @Expose
    private var kelas : String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("id")
    @Expose
    private var id = 0

    @SerializedName("subjects")
    @Expose
    private var subjects: String? = null

    @SerializedName("information")
    @Expose
    private var information: String? = null

    fun Items() {}

    fun Items (id: Int, name: String?, date: String?, kelas: String?, subjects: String?, information: String?) {
        this.id = id
        this.name = name
        this.date = date
        this.kelas = kelas
        this.subjects = subjects
        this.information = information
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getDate(): String? {
        return date
    }

    fun setKelas(kelas: String?) {
        this.kelas = kelas
    }

    fun getKelas(): String? {
        return kelas
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setSubjects(subjects: String?) {
        this.subjects = subjects
    }

    fun getSubjects(): String? {
        return subjects
    }

    fun setInformation(information: String?) {
        this.information = information
    }

    fun getInformation(): String? {
        return information
    }
}