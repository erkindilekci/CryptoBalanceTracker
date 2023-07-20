package com.erkindilekci.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.erkindilekci.domain.model.user.UserModel
import com.erkindilekci.ui.theme.Orange

@Composable
fun SettingsScreen(navController: NavHostController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val userInfoState = viewModel.userInfoState.value
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Settings",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))
        UserInfoSection(userInfoState)

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Notifications",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        NotificationSettingSection(viewModel)

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sign Out",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        LogoutSection(navController, viewModel)
    }
}

@Composable
fun UserInfoSection(user: UserModel) {
    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(Orange),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = user.name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = user.email,
                color = Color(0xFFEBE7E7),
                fontSize = 14.sp,
            )
        }
    }
}


@Composable
fun NotificationSettingSection(viewModel: SettingsViewModel) {
    val notificationState = viewModel.notificationState.value
    val checkState = remember { mutableStateOf(notificationState) }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .background(Orange),
    ) {
        Text(
            text = "Notifications",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        Switch(
            modifier = Modifier
                .align(
                    Alignment.CenterEnd
                ),
            checked = checkState.value, onCheckedChange = {
                checkState.value = it
                viewModel.saveNotificationPreference(it)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Orange,
                uncheckedThumbColor = Color.White,
                checkedTrackColor = Color.White,
                uncheckedTrackColor = Color(0x80FFFFFF)
            )
        )
    }
}

@Composable
fun LogoutSection(navController: NavHostController, viewModel: SettingsViewModel) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .background(Orange)
            .clickable {
                viewModel.signOut()
                navController.popBackStack()
                navController.navigate("signin_screen")
            }
    ) {
        Text(
            text = "Logout",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        IconButton(
            modifier = Modifier
                .align(
                    Alignment.CenterEnd
                ),
            onClick = {
                viewModel.signOut()
                navController.popBackStack()
                navController.navigate("signin_screen")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                tint = Color.White,
                contentDescription = "logout icon",
            )
        }
    }
}
