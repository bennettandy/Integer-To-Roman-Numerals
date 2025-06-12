package com.avsoftware.integertoromannumerals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.ui.RomanConverter
import com.avsoftware.integertoromannumerals.ui.UpDownButtonRow
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RomanConverterMviViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val uiState: RomanUiState = viewModel.collectAsState().value

            IntegerToRomanNumeralsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.roman_column_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            RomanConverter(
                                modifier = Modifier.padding(start = 100.dp, end = 100.dp),
                                uiState = uiState,
                                intentHandler = viewModel::handleIntent,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            UpDownButtonRow(
                                modifier = Modifier.padding(bottom = 32.dp),
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
