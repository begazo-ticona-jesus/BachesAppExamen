package com.romzc.bachesapp.ui.screens

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.romzc.bachesapp.R.drawable
import com.romzc.bachesapp.data.entities.PotholeEntity
import com.romzc.bachesapp.data.entities.UserEntity
import com.romzc.bachesapp.data.entities.UserWithPotholes
import com.romzc.bachesapp.ui.composables.CommonButton
import com.romzc.bachesapp.ui.composables.CustomNavBar
import com.romzc.bachesapp.ui.composables.ImageWithDefault
import com.romzc.bachesapp.ui.composables.getBitmapFromPath
import com.romzc.bachesapp.viewmodel.PotholeViewModel

@Composable
fun ScreenReportDetail (navController: NavController, id: Int){

    lateinit var mPotholeViewModel: PotholeViewModel
    mPotholeViewModel= ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(
        PotholeViewModel::class.java)

    var pothole = remember { mutableStateOf<PotholeEntity?>(null) }

    LaunchedEffect(Unit) {
        val fetchedPothole = mPotholeViewModel.getPothole(id)
        pothole.value = fetchedPothole
        Log.d("IDD", pothole.value?.potDesc ?: "")

    }

    CustomNavBar(navController = navController, title = "Detalle")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap: Bitmap? = getBitmapFromPath(pothole.value?.potImg?: "")
        Box(
            modifier = Modifier.size(250.dp),
        ) {
            ImageWithDefault(imageMap = bitmap, contentDescription = "image")
        }

        Text(
            text = pothole.value?.potDesc ?: "",
            modifier =Modifier.padding(10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = pothole.value?.potDate ?: "",
            modifier =Modifier.padding(10.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.width(20.dp))
        CommonButton(text = "Regresar", onClick = { navController.popBackStack() })
    }

}