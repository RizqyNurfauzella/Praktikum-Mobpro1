package org.d3if3074.mobpro1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3074.mobpro1.ui.theme.Mobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobpro1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Counter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(content: @Composable (Modifier) -> Unit) {
    Scaffold (
      topBar = {
          TopAppBar(
              title = {
                  Text(text = stringResource(id = R.string.app_name))
              },
              colors = TopAppBarDefaults.mediumTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer,
                  titleContentColor = MaterialTheme.colorScheme.primary
              )
          )
        }
    ) { padding ->
        content(Modifier.padding(padding))
    }
}

@Composable
fun Counter() {
    var number by remember { mutableIntStateOf(0) }

    MainScreen {modifier ->
        Row(
            modifier = modifier.fillMaxSize().padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { if (number!=0) number-- },
                modifier = Modifier.fillMaxWidth(0.4f).padding(35.dp),
                contentPadding = PaddingValues(15.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.kurang),
                    style = MaterialTheme.typography.displaySmall
                ) }
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.displayLarge
            )
            Button(
                onClick = { number++},
                modifier = Modifier.fillMaxWidth(0.8f).padding(35.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                Text(text = stringResource(id = R.string.tambah),
                    style = MaterialTheme.typography.displaySmall
                ) }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    Mobpro1Theme {
        Counter()
    }
}