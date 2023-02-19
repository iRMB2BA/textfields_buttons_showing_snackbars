package com.example.textfields_buttons_showing_snackbars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = SnackbarHostState()
            val scope = rememberCoroutineScope()
            var textFieldState by remember {
                mutableStateOf("")
            }
            
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {
                    scaffoldPadding ->
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .padding(scaffoldPadding)
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .padding(top = 250.dp),

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = textFieldState,
                            label = {
                                Text(text = "Enter You Name")
                            },
                            onValueChange = {
                                textFieldState = it
                            },
                            singleLine = true,
                            leadingIcon = { Icon(imageVector = Icons.Default.Check, contentDescription = "emailIcon") },
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Hello $textFieldState",
                                    actionLabel = "HIDE",
                                    duration = SnackbarDuration.Short,
                                    withDismissAction = true
                                )
                            }
                        },
                            colors = ButtonDefaults.buttonColors(Color.Magenta)
                        ) {
                            Text(text = "Push me" )
                        }
                    }

                }
            }
            
        }
    }
}








