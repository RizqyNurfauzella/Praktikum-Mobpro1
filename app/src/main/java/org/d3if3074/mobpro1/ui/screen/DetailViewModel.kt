package org.d3if3074.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3074.mobpro1.model.Mahasiswa

class DetailViewModel : ViewModel() {

    val mainViewModel: MainViewModel = MainViewModel()
    val listMahasiswa: List<Mahasiswa> = mainViewModel.dataMahasiswa
    fun getMahasiswa(id: Long): Mahasiswa {
        return Mahasiswa(
            id,
            listMahasiswa[(id -1.toLong()).toInt()].nama,
            listMahasiswa[(id -1.toLong()).toInt()].nim,
            listMahasiswa[(id -1.toLong()).toInt()].kelas,
        )
    }
}