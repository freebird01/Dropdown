package com.example.dropdown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropdown.ui.theme.DropdownTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DropdownTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DropdownExample(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownExample(name: String, modifier: Modifier = Modifier) {
    val dropdownExpandedState = remember {
        mutableStateOf(false)
    }

    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
//    val countryList = listOf(
//        "Germany",
//        "Italy",
//        "India",
//        "Belgium",
//        "Finland",
//        "Turkey",
//        "Russia",
//        "Brazil",
//        "Germany",
//        "Italy",
//        "India",
//        "Belgium",
//        "Finland",
//        "Turkey",
//        "Russia",
//        "Brazil",
//        "Germany",
//        "Italy",
//        "India",
//        "Belgium",
//        "Finland",
//        "Turkey",
//        "Russia",
//        "Brazil"
//    )
    val countryList = stringArrayResource(R.array.countries_array)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box()
        {
            Row(
                modifier = Modifier
                    .clickable {
                        dropdownExpandedState.value = true
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = countryList[selectedIndex.value],
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Image(
                    painter = painterResource(R.drawable.drop_down),
                    contentDescription = "",
                )

                DropdownMenu(
                    expanded = dropdownExpandedState.value,
                    onDismissRequest = {
                        dropdownExpandedState.value = false
                    }
                ) {
                    countryList.forEachIndexed { index, country ->
                        DropdownMenuItem(
                            text = { Text(text = country) },
                            onClick = {
                                selectedIndex.value = index
                                dropdownExpandedState.value = false
                            })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropdownTheme {
        DropdownExample("Android")
    }
}