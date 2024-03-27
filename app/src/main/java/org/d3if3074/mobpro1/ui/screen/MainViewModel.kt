package org.d3if3074.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3074.mobpro1.model.Mahasiswa

class MainViewModel: ViewModel() {

    val dataMahasiswa = listOf(
        Mahasiswa(1, "Rizza Indah Mega Mandasari", "6706244601", "D3IF-46-01"),
        Mahasiswa(2, "Indra Azimi", "6706244602", "D3IF-46-02"),
        Mahasiswa(2, "Reza Budiawan", "6706244612", "D3IF-46-02"),
        Mahasiswa(2, "Dwiko Indrawansyah", "6706244622", "D3IF-46-02"),
        Mahasiswa(2, "Cahyana", "6706244603", "D3IF-46-03"),
        Mahasiswa(2, "Indra Azemi", "6706244604", "D3IF-46-04"),
        Mahasiswa(2, "Erna Hikmawati", "6706244605", "D3IF-46-05"),
        Mahasiswa(2, "Rizqy Nurfauzella", "6706223074", "D3IF-46-04"),
        Mahasiswa(2, "Ryan Gusman", "6706244637", "D3IF-46-04"),
        Mahasiswa(2, "Muhammad Ihsan F", "6706244686", "D3IF-46-04"),
    )
}
