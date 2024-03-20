package org.d3if3074.mobpro1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3074.mobpro1.R
import org.d3if3074.mobpro1.navigation.Screen
import org.d3if3074.mobpro1.ui.theme.Mobpro1Theme
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
      topBar = {
          TopAppBar(
              title = {
                  Text(text = stringResource(id = R.string.app_name))
              },
              colors = TopAppBarDefaults.mediumTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer,
                  titleContentColor = MaterialTheme.colorScheme.primary
              ),
              actions = {
                  IconButton(onClick = {
                      navController.navigate(Screen.About.route)
                  }) {
                      Icon(
                          imageVector = Icons.Outlined.Info,
                          contentDescription = stringResource(id = R.string.tentang_aplikasi),
                          tint = MaterialTheme.colorScheme.primary
                      )
                  }
              }
          )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var panjang by rememberSaveable { mutableStateOf("") }
    var panjangError by rememberSaveable { mutableStateOf(false) }

    var lebar by rememberSaveable { mutableStateOf("") }
    var lebarError by rememberSaveable { mutableStateOf(false) }

    var luas by rememberSaveable { mutableFloatStateOf(0f) }
    var keliling by rememberSaveable { mutableFloatStateOf(0f) }

    val symbols = DecimalFormatSymbols(Locale("id", "ID")).apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }
    val formatter = DecimalFormat("#,##0.00", symbols)
    val formattedArea = formatter.format(luas)
    val formattedPerimeter = formatter.format(keliling)

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text =  stringResource(id = R.string.hitung_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = panjang,
            onValueChange = {panjang = it },
            label = { Text(text = stringResource(id = R.string.panjang))},
            isError = panjangError,
            trailingIcon = { IconPicker(panjangError, unit = "cm" )},
            supportingText = { ErrorHint(panjangError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = lebar,
            onValueChange = {lebar = it },
            label = { Text(text = stringResource(id = R.string.lebar))},
            isError = lebarError,
            trailingIcon = { IconPicker(lebarError, unit = "cm" )},
            supportingText = { ErrorHint(lebarError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                panjangError = (panjang == "" || panjang == "0")
                lebarError = (lebar == "" || lebar == "0")
                if (panjangError || lebarError) return@Button

                luas = hitungLuas(panjang.toFloat(), lebar.toFloat())
                keliling = hitungKeliling(panjang.toFloat(), lebar.toFloat())
            },
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.hitung))
        }
        if (luas != 0f || keliling != 0f) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp
            )
            Text(
                text = "Luas: $formattedArea",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Keliling: $formattedPerimeter",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Button(
            onClick = {
                panjang = ""
                lebar = ""
                luas = 0.0f
                keliling = 0.0f
                panjangError = false
                lebarError = false
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.reset))
        }
        Button(
            onClick = { shareData(
                context = context,
                message = context.getString(R.string.bagikan_template,panjang,lebar,luas,keliling)
            ) },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
           Text(text = stringResource(id = R.string.bagikan))
        }
    }
}

private fun hitungLuas (panjang: Float, lebar:Float): Float {
    return panjang * lebar
}

private fun hitungKeliling (panjang: Float, lebar: Float): Float {
    return 2 * (panjang + lebar)
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) !=null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    Mobpro1Theme {
        MainScreen(rememberNavController())
    }
}