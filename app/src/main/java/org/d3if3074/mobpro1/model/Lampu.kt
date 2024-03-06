package org.d3if3074.mobpro1.model

import androidx.annotation.DrawableRes
import java.sql.ClientInfoStatus

data class Lampu(
    val nama: String,
    val status: String,
    @DrawableRes val imageResId: Int
)
