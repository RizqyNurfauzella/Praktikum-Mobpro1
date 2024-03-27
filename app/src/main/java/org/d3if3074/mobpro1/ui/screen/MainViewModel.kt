package org.d3if3074.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3074.mobpro1.model.Catatan

class MainViewModel : ViewModel() {

    val data = getDataDumbby()

    private fun getDataDumbby(): List<Catatan> {
        val data = mutableListOf<Catatan>()
        for (i in 29 downTo 20) {
            data.add(
                Catatan(
                    i.toLong(),
                    "Kuloah Mobpro $1 Maret",
                    "Yey, hari ini belajar membuat aplikasi Android List dan berhasil, Hehe.. Mudah2an modul selanjutnya juga lancar. Aamiin.",
                    "2024-03-$1 12:11:50"
                )
            )
        }
        return data
    }
}