package com.example.greetingcard.designsystem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greetingcard.R
import com.example.greetingcard.Screens

@Composable
fun ButtonExample(onClick: () -> Unit, btnName: String) {
    ElevatedButton(
        onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,5.dp)
            .height(55.dp)
    ) {

        Text(
            text = btnName, fontWeight = FontWeight.Bold, fontSize = 18.sp
        )
    }
}

@Composable
fun GoogleButton(onClick: () -> Unit, btnName: String) {
    ElevatedButton(
        onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,5.dp)
            .height(55.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.google_logo), contentDescription = null, tint = Color.Unspecified)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = btnName, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        }

    }
}

@Composable
fun AppleButton(onClick: () -> Unit, btnName: String) {
    ElevatedButton(
        onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,5.dp)
            .height(55.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.apple_logo), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = btnName, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        }

    }
}

@Composable
fun ElevatedButtonExample(onClick: () -> Unit, btnName: String,navController: NavController) {
    ElevatedButton(
        onClick = {onClick() },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color(4,83,90),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,5.dp)
            .height(55.dp)
    ) {
        Text(btnName)

    }
}


@Composable
fun TransparentButtonExample(onClick: () -> Unit, btnName: String) {
    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
        ),
        modifier = Modifier
            .defaultMinSize()
            .height(37.dp),
        border = null

    ) {

        Text(text = btnName, fontWeight = FontWeight.Bold, fontSize = 18.sp)

    }
}