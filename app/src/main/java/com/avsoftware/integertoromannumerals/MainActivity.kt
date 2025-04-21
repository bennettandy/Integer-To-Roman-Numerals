package com.avsoftware.integertoromannumerals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.ui.RomanConverter
import com.avsoftware.integertoromannumerals.ui.UpDownButtonRow
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RomanConverterMviViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val uiState: State<RomanUiState> = viewModel.container.stateFlow.collectAsState()

            IntegerToRomanNumeralsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.roman_column_background),
                            contentDescription = null, // Background images typically don't need a description
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds // Adjust scaling as needed
                        )

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            RomanConverter(
                                modifier = Modifier.padding(start = 100.dp, end = 100.dp),
                                uiState = uiState.value,
                                intentHandler = viewModel::handleIntent,
                            )
                            UpDownButtonRow(
                                upClicked = { viewModel.handleIntent(RomanUiIntent.UpClicked) },
                                downClicked = { viewModel.handleIntent(RomanUiIntent.DownClicked) }
                            )
                        }
                    }
                }
            }
        }
    }
}
