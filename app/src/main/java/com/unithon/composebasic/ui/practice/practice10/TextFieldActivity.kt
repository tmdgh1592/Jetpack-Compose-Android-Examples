package com.unithon.composebasic.ui.practice.practice10

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.R
import com.unithon.composebasic.ui.practice.practice10.ui.theme.ComposeBasicTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TextFieldTest()
                }
            }
        }
    }
}

//fun TextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    label: @Composable (() -> Unit)? = null,
//    placeholder: @Composable (() -> Unit)? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null,
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions(),
//    singleLine: Boolean = false,
//    maxLines: Int = Int.MAX_VALUE,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape =
//        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
//    colors: TextFieldColors = TextFieldDefaults.textFieldColors()

@Composable
fun TextFieldTest() {
    var userInput by remember {
        mutableStateOf(TextFieldValue())
    }
    var phoneNumberInput by remember {
        mutableStateOf(TextFieldValue())
    }
    var emailInput by remember {
        mutableStateOf(TextFieldValue())
    }
    var passwordInput by remember {
        mutableStateOf(TextFieldValue())
    }
    val shouldShowPassword = remember {
        mutableStateOf(false)
    }

    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    val localFocusManager = LocalFocusManager.current

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = false,
            maxLines = 2,
            keyboardActions = KeyboardActions(onDone = {
                localFocusManager.moveFocus(FocusDirection.Next)
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Uri
            ),
            onValueChange = { newValue -> userInput = newValue },
            label = { Text("사용자 입력") },
            placeholder = { Text(text = "작성해 주세요") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            //maxLines = 2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { newValue -> phoneNumberInput = newValue },
            label = { Text("전화번호") },
            placeholder = { Text(text = "010-1234-1234") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { Log.d("TAG", "TextFieldTest: 체크버튼 클릭") }) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                }
            },
            //maxLines = 2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { newValue -> emailInput = newValue },
            label = { Text("이메일 주소") },
            placeholder = { Text(text = "abc@gmail.com") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = {
                    shouldShowPassword.value = !shouldShowPassword.value
                }) {
                    Icon(
                        painter = painterResource(id = passwordResource(shouldShowPassword.value)),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            //maxLines = 2,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            onValueChange = { newValue -> passwordInput = newValue },
            label = { Text("비밀번호") },
            placeholder = { Text(text = "비밀번호를 입력해주세요") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    ComposeBasicTheme {
        TextFieldTest()
    }
}