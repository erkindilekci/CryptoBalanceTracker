package com.erkindilekci.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.erkindilekci.components.CustomCircularProgress
import com.erkindilekci.components.CustomInputText
import com.erkindilekci.components.CustomPasswordText
import com.erkindilekci.components.Toast
import com.erkindilekci.util.Response

@Composable
fun SignUpScreen(navController: NavHostController) {

    val viewModel: SignUpViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create an account",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Please fill all informations!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(60.dp))

        CustomInputText(labelText = "Name") { viewModel.updateNameState(it) }

        Spacer(modifier = Modifier.height(20.dp))

        CustomInputText(labelText = "Email") { viewModel.updateEmailState(it) }

        Spacer(modifier = Modifier.height(20.dp))

        CustomPasswordText { viewModel.updatePasswordState(it) }

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            modifier = Modifier.size(320.dp, 55.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF0066ff)),
            shape = RoundedCornerShape(15.dp),
            onClick = { viewModel.signUp() }
        ) {
            Text(text = "Create", color = Color.White)
            Spacer(modifier = Modifier.width(5.dp))
            CheckSignUpState(viewModel, navController)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            TextButton(onClick = { navController.navigate("signin_screen") }) {
                Text(
                    text = "Sign In", fontSize = 14.sp,
                    color = Color(0xFF2341e0),
                    fontWeight = FontWeight.Medium
                )

            }
        }
    }
}

@Composable
fun CheckSignUpState(viewModel: SignUpViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }

    if (isLoading.value) CustomCircularProgress()

    LaunchedEffect(key1 = Unit) {

        viewModel.signUpFlow.collect {
            when (it) {
                is Response.Success -> {
                    isLoading.value = false
                    navController.popBackStack()
                    navController.navigate("signin_screen")
                }

                is Response.Loading -> {
                    isLoading.value = true
                }

                is Response.Error -> {
                    isLoading.value = false
                    Toast(context, it.msg)
                }
            }
        }
    }
}
