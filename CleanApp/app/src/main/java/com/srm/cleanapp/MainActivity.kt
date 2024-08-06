package com.srm.cleanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srm.cleanapp.ui.theme.CleanAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen(viewModel: MainViewModel = viewModel()) {
    var a by remember {
        mutableStateOf("")
    }
    var b by remember {
        mutableStateOf("")
    }


    Calculator(
        a = a,
        onAChanged = { a = it },
        b = b,
        onBChanged = { b = it },
        result = viewModel.resultState
    ) {
        viewModel.calculateSum(a, b)
    }
}

@Composable
fun Calculator(
    a: String,
    onAChanged: (String) -> Unit,
    b: String,
    onBChanged: (String) -> Unit,
    result: String,
    onButtonClick: () -> Unit
) {

    Column(Modifier.padding(16.dp)) {
        NumberInputField(
            value = a,
            onValueChange = onAChanged,
            label = { Text(text = "a") }
        )
        NumberInputField(
            value = b,
            onValueChange = onBChanged,
            label = { Text(text = "b") }
        )
        Text(text = result)
        Button(onClick = onButtonClick, content = { Text(text = "Calculate") })
    }
}

@Composable
fun NumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NumberInputTextFieldPreview() {
    NumberInputField("1", {}, { Text(text = "Enter number") })
}
