package com.example.greetingcard.activities

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.R
import com.example.greetingcard.Screens
import com.example.greetingcard.designsystem.AppleButton
import com.example.greetingcard.designsystem.ButtonExample
import com.example.greetingcard.designsystem.ElevatedButtonExample
import com.example.greetingcard.designsystem.GoogleButton
import com.example.greetingcard.designsystem.LoginResult
import com.example.greetingcard.designsystem.TransparentButtonExample
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.designsystem.LoginViewModel
import com.example.greetingcard.designsystem.RegisterBottomSheet

@Composable
fun LoginPage(
    navController: NavController,
    modifier: Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    var showRegisterSheet by remember { mutableStateOf(false) }
    val loginResult by viewModel.loginResult.collectAsState()

    // React to login result
    LaunchedEffect(loginResult) {
        when (loginResult) {
            is LoginResult.Success -> {
                viewModel.resetResult()
                navController.navigate(Screens.Home.name)
            }
            else -> {}
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.LightGray) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
        ) {
            Surface(
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                shape = RoundedCornerShape(30.dp)
            ) {

                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Spacer(modifier.height(10.dp))
                    Image(
                        painterResource(id = R.drawable.login_logo),
                        contentDescription = "Login Logo", Modifier.size(100.dp)
                    )

                    Text(text = "Sign in", fontSize = 27.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "To sign in to an account in the application, enter your email and password",
                        textAlign = TextAlign.Center
                    )


                    OutlinedTextField(
                        value = viewModel.email,
                        modifier = Modifier.fillMaxWidth().padding(10.dp, 5.dp)
                            .background(color = Color(248, 245, 250)),
                        onValueChange = {viewModel.onEmailChange(newEmail = it)},
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text("E-mail") }
                    )


                    OutlinedTextField(
                        value = viewModel.password,
                        modifier = Modifier.fillMaxWidth().padding(10.dp, 5.dp)
                            .background(color = Color(248, 245, 250)),
                        onValueChange = {viewModel.onPasswordChange(newPassword = it)},
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )

                    if (loginResult is LoginResult.Error) {
                        Text(
                            text = (loginResult as LoginResult.Error).message,
                            color = Color.Red,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    TransparentButtonExample(onClick = {
                        Log.d(
                            "Forgot password?",
                            "Sorry for that"
                        )
                    }, "Forgot password?")


                    ElevatedButtonExample(
                        onClick = { viewModel.login()},
                        "Continue", navController
                    )

                    Row(
                        modifier = Modifier.width(350.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .width(60.dp)
                                .background(Color.Gray)
                        )
                        Text(text = "Don't have an account yet?",modifier= Modifier.padding(10.dp,0.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .width(60.dp)
                                .background(Color.Gray)
                        )
                    }

                    ButtonExample(
                        onClick = {showRegisterSheet = true},
                        "Create an account"
                    )
                    AppleButton(
                        onClick = { Log.d("Sign in with Apple", "Sorry for that") },
                        "Sign in with Apple"
                    )
                    GoogleButton(
                        onClick = { Log.d("Sign in with Google", "Sorry for that") },
                        "Sign in with Google"
                    )

                }

            }

            Text(
                text = buildAnnotatedString {
                    append("By clicking 'Continue', I have read and agree with the ")

                    pushStringAnnotation(
                        tag = "TERM",
                        annotation = "https://example.com/term"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            color = Color.Blue
                        )
                    ) {
                        append("Term Sheet")
                    }
                    pop()

                    append(", ")

                    pushStringAnnotation(
                        tag = "PRIVACY",
                        annotation = "https://example.com/privacy"
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            color = Color.Blue
                        )
                    ) {
                        append("Privacy Policy")
                    }
                    pop()
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(
                    onClick = {
                        // Optional: handle click by checking the annotation
                    }
                )
            )

        }
        if (showRegisterSheet) {
            RegisterBottomSheet(
                onDismiss = { showRegisterSheet = false }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        LoginPage(navController = rememberNavController(),modifier = Modifier)
    }
}

